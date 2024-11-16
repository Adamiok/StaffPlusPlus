package net.shortninja.staffplus.core.common.utils;

import be.garagepoort.mcioc.IocBean;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.NBTFileHandle;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBTCompoundList;
import net.shortninja.staffplus.core.application.config.Options;
import net.shortninja.staffplusplus.session.SppPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

@IocBean
public final class InventoryFactory {
    private final Options options;
    
    public InventoryFactory(Options options) {
        this.options = options;
    }

    public Inventory loadEnderchestOffline(Player player, SppPlayer target) {
        try {
            String filename = Bukkit.getWorldContainer() + File.separator + options.mainWorld + File.separator + "playerdata" + File.separator + target.getId() + ".dat";
            Inventory inventory = Bukkit.createInventory(player, InventoryType.ENDER_CHEST);
            NBTFileHandle file = NBT.getFileHandle(new File(filename));
            ReadWriteNBTCompoundList enderItems = file.getCompoundList("EnderItems");
            
            for (ReadWriteNBT enderItem : enderItems) {
                ItemStack itemStack = NBT.itemStackFromNBT(enderItem);
                inventory.setItem(Byte.toUnsignedInt(enderItem.getByte("Slot")), itemStack);
            }
            
            return inventory;
        } catch (IOException e) {
            throw new RuntimeException("Player data file could not be loaded", e);
        }
    }

    public void saveEnderchestOffline(SppPlayer target, Inventory inventory) {
        try {
            String filename = Bukkit.getWorldContainer() + File.separator + options.mainWorld + File.separator + "playerdata" + File.separator + target.getId() + ".dat";
            NBTFileHandle file = NBT.getFileHandle(new File(filename));
            ReadWriteNBTCompoundList enderItems = file.getCompoundList("EnderItems");
            enderItems.clear();

            for (int i = 0; i < inventory.getContents().length; i++) {
                ItemStack item = inventory.getContents()[i];
                if (item == null || item.getType() == Material.AIR) continue;

                ReadWriteNBT itemNbt = NBT.itemStackToNBT(item);
                itemNbt.setByte("Slot", Integer.valueOf(i).byteValue());
                enderItems.addCompound(itemNbt);
            }
            file.save();
        } catch (IOException e) {
            throw new RuntimeException("Player data file could not be saved", e);
        }
    }

    public Inventory loadInventoryOffline(Player player, SppPlayer target) {
        try {
            String filename = Bukkit.getWorldContainer() + File.separator + options.mainWorld + File.separator + "playerdata" + File.separator + target.getId() + ".dat";
            Inventory inventory = Bukkit.createInventory(player, InventoryType.PLAYER);
            NBTFileHandle file = NBT.getFileHandle(new File(filename));
            ReadWriteNBTCompoundList inventoryItems = file.getCompoundList("Inventory");
            
            for (ReadWriteNBT item : inventoryItems) {
                ItemStack itemStack = NBT.itemStackFromNBT(item);
                int slot = Byte.toUnsignedInt(item.getByte("Slot"));
                
                if (slot <= 35 && slot >= 0) {
                    inventory.setItem(slot, itemStack);
                } else {
                    switch (slot) {
                        case 100:
                            inventory.setItem(36, itemStack);
                            break;
                        case 101:
                            inventory.setItem(37, itemStack);
                            break;
                        case 102:
                            inventory.setItem(38, itemStack);
                            break;
                        case 103:
                            inventory.setItem(39, itemStack);
                            break;
                        case -106:
                            inventory.setItem(40, itemStack);
                            break;
                        default:
                            throw new RuntimeException("Player data file could not be loaded - invalid item slot in inventory");
                    }
                }
            }
            return inventory;

        } catch (IOException e) {
            throw new RuntimeException("Player data file could not be loaded", e);
        }
    }

    public void saveInventoryOffline(SppPlayer target, Inventory inventory) {
        try {
            String filename = Bukkit.getWorldContainer() + File.separator + options.mainWorld + File.separator + "playerdata" + File.separator + target.getId() + ".dat";
            NBTFileHandle file = NBT.getFileHandle(new File(filename));
            ReadWriteNBTCompoundList inventoryNbt = file.getCompoundList("Inventory");
            inventoryNbt.clear();

            for (int i = 0; i < inventory.getContents().length; i++) {
                ItemStack item = inventory.getContents()[i];
                if (item == null || item.getType() == Material.AIR) continue;

                ReadWriteNBT itemNbt = NBT.itemStackToNBT(item);
                int convertedSlot;
                
                if (i <= 35) {
                    convertedSlot = i;
                } else {
                    switch (i) {
                        case 36:
                            convertedSlot = 100;
                            break;
                        case 37:
                            convertedSlot = 101;
                            break;
                        case 38:
                            convertedSlot = 102;
                            break;
                        case 39:
                            convertedSlot = 103;
                            break;
                        case 40:
                            convertedSlot = -106;
                            break;
                        default:
                            throw new RuntimeException("Player data file could not be saved - inventory index out of range");
                    }
                }
                
                itemNbt.setByte("Slot", Integer.valueOf(convertedSlot).byteValue());
                inventoryNbt.addCompound(itemNbt);
            }
            file.save();
        } catch (IOException e) {
            throw new RuntimeException("Player data file could not be saved", e);
        }
    }
}
