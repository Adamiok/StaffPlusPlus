package net.shortninja.staffplus.core.domain.staff.mode.config;

import net.shortninja.staffplus.core.domain.actions.ConfiguredAction;
import net.shortninja.staffplus.core.domain.staff.mode.config.gui.GuiConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.compass.CompassModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.counter.CounterModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.cps.CpsModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.examine.ExamineModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.follow.FollowModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.freeze.FreezeModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.gui.GuiModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.randomteleport.RandomTeleportModeConfiguration;
import net.shortninja.staffplus.core.domain.staff.mode.config.modeitems.vanish.VanishModeConfiguration;
import net.shortninja.staffplusplus.vanish.VanishType;

import java.util.List;

public class GeneralModeConfiguration {

    private VanishType modeVanish;
    private boolean modeItemPickup;
    private boolean modeItemDrop;
    private boolean modeDamage;
    private boolean modeHungerLoss;
    private final List<ConfiguredAction> modeEnableCommands;
    private final List<ConfiguredAction> modeDisableCommands;
    private boolean worldChange;
    private boolean modeBlockManipulation;
    private boolean modeInventoryInteraction;
    private boolean modeSilentChestInteraction;
    private boolean modeInvincible;
    private boolean modeFlight;
    private boolean modeCreative;
    private boolean modeOriginalLocation;
    private boolean modeEnableOnLogin;
    private boolean modeDisableOnLogout;

    private final List<GuiConfiguration> guiConfigurations;
    private final CompassModeConfiguration compassModeConfiguration;
    private final CounterModeConfiguration counterModeConfiguration;
    private final CpsModeConfiguration cpsModeConfiguration;
    private final ExamineModeConfiguration examineModeConfiguration;
    private final FollowModeConfiguration followModeConfiguration;
    private final FreezeModeConfiguration freezeModeConfiguration;
    private final GuiModeConfiguration guiModeConfiguration;
    private final RandomTeleportModeConfiguration randomTeleportModeConfiguration;
    private final VanishModeConfiguration vanishModeConfiguration;

    public GeneralModeConfiguration(VanishType modeVanish,
                                    boolean modeItemPickup,
                                    boolean modeItemDrop, boolean modeDamage,
                                    boolean modeHungerLoss,
                                    List<ConfiguredAction> modeEnableCommands,
                                    List<ConfiguredAction> modeDisableCommands,
                                    boolean worldChange,
                                    boolean modeBlockManipulation,
                                    boolean modeInventoryInteraction,
                                    boolean modeSilentChestInteraction,
                                    boolean modeInvincible,
                                    boolean modeFlight,
                                    boolean modeCreative,
                                    boolean modeOriginalLocation,
                                    boolean modeEnableOnLogin,
                                    boolean modeDisableOnLogout,
                                    List<GuiConfiguration> guiConfigurations,
                                    CompassModeConfiguration compassModeConfiguration,
                                    CounterModeConfiguration counterModeConfiguration,
                                    CpsModeConfiguration cpsModeConfiguration,
                                    ExamineModeConfiguration examineModeConfiguration,
                                    FollowModeConfiguration followModeConfiguration,
                                    FreezeModeConfiguration freezeModeConfiguration,
                                    GuiModeConfiguration guiModeConfiguration,
                                    RandomTeleportModeConfiguration randomTeleportModeConfiguration,
                                    VanishModeConfiguration vanishModeConfiguration) {
        this.modeVanish = modeVanish;
        this.modeItemPickup = modeItemPickup;
        this.modeItemDrop = modeItemDrop;
        this.modeDamage = modeDamage;
        this.modeHungerLoss = modeHungerLoss;
        this.modeEnableCommands = modeEnableCommands;
        this.modeDisableCommands = modeDisableCommands;
        this.worldChange = worldChange;
        this.modeBlockManipulation = modeBlockManipulation;
        this.modeInventoryInteraction = modeInventoryInteraction;
        this.modeSilentChestInteraction = modeSilentChestInteraction;
        this.modeInvincible = modeInvincible;
        this.modeFlight = modeFlight;
        this.modeCreative = modeCreative;
        this.modeOriginalLocation = modeOriginalLocation;
        this.modeEnableOnLogin = modeEnableOnLogin;
        this.modeDisableOnLogout = modeDisableOnLogout;

        this.guiConfigurations = guiConfigurations;
        this.compassModeConfiguration = compassModeConfiguration;
        this.counterModeConfiguration = counterModeConfiguration;
        this.cpsModeConfiguration = cpsModeConfiguration;
        this.examineModeConfiguration = examineModeConfiguration;
        this.followModeConfiguration = followModeConfiguration;
        this.freezeModeConfiguration = freezeModeConfiguration;
        this.guiModeConfiguration = guiModeConfiguration;
        this.randomTeleportModeConfiguration = randomTeleportModeConfiguration;
        this.vanishModeConfiguration = vanishModeConfiguration;
    }

    public boolean isModeItemPickup() {
        return modeItemPickup;
    }

    public boolean isModeItemDrop() {
        return modeItemDrop;
    }

    public List<GuiConfiguration> getStaffGuiConfigurations() {
        return guiConfigurations;
    }

    public CompassModeConfiguration getCompassModeConfiguration() {
        return compassModeConfiguration;
    }

    public VanishModeConfiguration getVanishModeConfiguration() {
        return vanishModeConfiguration;
    }

    public RandomTeleportModeConfiguration getRandomTeleportModeConfiguration() {
        return randomTeleportModeConfiguration;
    }

    public CounterModeConfiguration getCounterModeConfiguration() {
        return counterModeConfiguration;
    }

    public CpsModeConfiguration getCpsModeConfiguration() {
        return cpsModeConfiguration;
    }

    public ExamineModeConfiguration getExamineModeConfiguration() {
        return examineModeConfiguration;
    }

    public FollowModeConfiguration getFollowModeConfiguration() {
        return followModeConfiguration;
    }

    public FreezeModeConfiguration getFreezeModeConfiguration() {
        return freezeModeConfiguration;
    }

    public GuiModeConfiguration getGuiModeConfiguration() {
        return guiModeConfiguration;
    }

    public VanishType getModeVanish() {
        return modeVanish;
    }

    public boolean isModeDamage() {
        return modeDamage;
    }

    public boolean isModeHungerLoss() {
        return modeHungerLoss;
    }

    public List<ConfiguredAction> getModeEnableCommands() {
        return modeEnableCommands;
    }

    public List<ConfiguredAction> getModeDisableCommands() {
        return modeDisableCommands;
    }

    public boolean isWorldChange() {
        return worldChange;
    }

    public boolean isModeBlockManipulation() {
        return modeBlockManipulation;
    }

    public boolean isModeInventoryInteraction() {
        return modeInventoryInteraction;
    }

    public boolean isModeSilentChestInteraction() {
        return modeSilentChestInteraction;
    }

    public boolean isModeInvincible() {
        return modeInvincible;
    }

    public boolean isModeFlight() {
        return modeFlight;
    }

    public boolean isModeCreative() {
        return modeCreative;
    }

    public boolean isModeOriginalLocation() {
        return modeOriginalLocation;
    }

    public boolean isModeEnableOnLogin() {
        return modeEnableOnLogin;
    }

    public boolean isModeDisableOnLogout() {
        return modeDisableOnLogout;
    }

}