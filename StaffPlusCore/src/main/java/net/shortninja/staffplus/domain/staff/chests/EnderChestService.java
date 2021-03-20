package net.shortninja.staffplus.domain.staff.chests;

import net.shortninja.staffplus.common.exceptions.BusinessException;
import net.shortninja.staffplus.domain.player.SppPlayer;
import net.shortninja.staffplus.common.config.Options;
import net.shortninja.staffplus.common.utils.PermissionHandler;
import net.shortninja.staffplus.common.utils.InventoryFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class EnderChestService {

    private final PermissionHandler permissionHandler;
    private final Options options;

    public EnderChestService(PermissionHandler permissionHandler, Options options) {
        this.permissionHandler = permissionHandler;
        this.options = options;
    }

    public void openEnderChest(Player staff, SppPlayer target) {
        if (target.isOnline()) {
            if (!permissionHandler.has(staff, options.enderchestsConfiguration.getPermissionViewOnline())) {
                throw new BusinessException("&CYou are not allowed to view the enderchest of an online player");
            }
            new ChestGUI(target,
                target.getPlayer().getEnderChest(),
                InventoryType.ENDER_CHEST,
                ChestGuiType.ENDER_CHEST_EXAMINE,
                permissionHandler.has(staff, options.enderchestsConfiguration.getPermissionInteract())).show(staff);
        } else {
            if (!permissionHandler.has(staff, options.enderchestsConfiguration.getPermissionViewOffline())) {
                throw new BusinessException("&CYou are not allowed to view the enderchest of an offline player");
            }

            Inventory offlineEnderchest = InventoryFactory.loadEnderchestOffline(staff, target);
            new ChestGUI(target, offlineEnderchest, InventoryType.ENDER_CHEST, ChestGuiType.ENDER_CHEST_EXAMINE, permissionHandler.has(staff, options.enderchestsConfiguration.getPermissionInteract()))
                .show(staff);
        }
    }
}