package net.shortninja.staffplus.core.domain.staff.ban.playerbans.gui;

import be.garagepoort.mcioc.IocBean;
import be.garagepoort.mcioc.IocMultiProvider;
import net.shortninja.staffplus.core.common.IProtocolService;
import net.shortninja.staffplus.core.common.Items;
import net.shortninja.staffplus.core.application.config.Options;
import net.shortninja.staffplus.core.domain.staff.ban.playerbans.Ban;
import net.shortninja.staffplus.core.domain.staff.infractions.InfractionType;
import net.shortninja.staffplus.core.domain.staff.infractions.gui.InfractionGuiProvider;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static net.shortninja.staffplus.core.common.JavaUtils.formatLines;

@IocBean
@IocMultiProvider(InfractionGuiProvider.class)
public class BannedPlayerItemBuilder implements InfractionGuiProvider<Ban> {

    private final IProtocolService protocolService;
    private final Options options;

    public BannedPlayerItemBuilder(IProtocolService protocolService, Options options) {
        this.protocolService = protocolService;
        this.options = options;
    }


    public ItemStack build(Ban ban) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(ban.getCreationDate().toInstant(), ZoneOffset.UTC);
        String time = localDateTime.truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ofPattern(options.timestampFormat));

        List<String> lore = new ArrayList<>();

        lore.add("&bId: " + ban.getId());
        if(options.serverSyncConfiguration.isBanSyncEnabled()) {
            lore.add("&bServer: " + ban.getServerName());
        }
        lore.add("&bBanned player: " + ban.getTargetName());
        lore.add("&bIssuer: " + ban.getIssuerName());
        lore.add("&bIssued on: " + time);
        lore.add("&bReason:");
        for (String line : formatLines(ban.getReason(), 30)) {
            lore.add("  &b" + line);
        }

        if(ban.getEndTimestamp() != null) {
            lore.add("&bTime left:");
            lore.add("  &b" + ban.getHumanReadableDuration());
        }else {
            lore.add("&bPermanent ban");
        }
        ItemStack item = Items.builder()
            .setMaterial(Material.PLAYER_HEAD)
            .setName("&cBan")
            .addLore(lore)
            .build();

        return protocolService.getVersionProtocol().addNbtString(item, String.valueOf(ban.getId()));
    }

    @Override
    public InfractionType getType() {
        return InfractionType.BAN;
    }

    @Override
    public ItemStack getMenuItem(Ban ban) {
        ItemStack itemStack = build(ban);
        itemStack.setType(options.infractionsConfiguration.getBansGuiItem());
        return itemStack;
    }
}