package net.shortninja.staffplus.core.domain.staff.warn.warnings.config;

import be.garagepoort.mcioc.IocBean;
import be.garagepoort.mcioc.IocMultiProvider;
import net.shortninja.staffplus.core.common.config.AbstractConfigLoader;

import org.bukkit.configuration.file.FileConfiguration;

@IocBean
public class ManageWarningsModuleLoader extends AbstractConfigLoader<ManageWarningsConfiguration> {

    @Override
    protected ManageWarningsConfiguration load(FileConfiguration config) {
        String commandManageWarningsGui = config.getString("commands.warnings.manage.gui");
        String commandManageAppealedWarningsGui = config.getString("commands.warnings.manage.appealed-gui");
        String permissionManageWarningsView = config.getString("permissions.warnings.manage.view");
        String permissionManageWarningsDelete = config.getString("permissions.warnings.manage.delete");
        String permissionManageWarningsExpire = config.getString("permissions.warnings.manage.expire");

        return new ManageWarningsConfiguration(
            commandManageWarningsGui,
            commandManageAppealedWarningsGui, permissionManageWarningsView,
            permissionManageWarningsDelete,
            permissionManageWarningsExpire);
    }
}
