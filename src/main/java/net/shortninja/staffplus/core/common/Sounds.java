package net.shortninja.staffplus.core.common;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.lang.RuntimeException;

/**
 * @author MrBlobman, Shortninja
 */

public class Sounds {

    private Sound resolvedSound = null;

    public Sounds(String sound) {
        NamespacedKey key = NamespacedKey.fromString(sound.toLowerCase());
        if (key != null) {
            resolvedSound = Registry.SOUNDS.get(key);
            return;
        }
        
        throw new RuntimeException("Unable to find sound '" + sound + "', perhaps you forgot a namespace?");
    }

    public Sounds(Sound sound) {
        resolvedSound = sound;
    }

    /**
     * Plays the sound with player#playSound.
     *
     * @param player The player to play the sound for.
     */
    public void play(Player player) {
        if (player == null) {
            return;
        }

        player.playSound(player.getLocation(), resolvedSound, 1, 0);
    }

    /**
     * Plays the sound for players with the permission.
     *
     * @param permission The permission a player must have to hear the sound.
     */
    public void playForGroup(String permission) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            try {
                if (player.hasPermission(permission)) {
                    play(player);
                }
            } catch (UnsupportedOperationException e) {
            }
        }
    }
}