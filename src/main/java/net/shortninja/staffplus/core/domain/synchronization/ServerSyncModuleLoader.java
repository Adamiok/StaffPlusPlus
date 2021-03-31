package net.shortninja.staffplus.core.domain.synchronization;

import be.garagepoort.mcioc.IocBean;
import net.shortninja.staffplus.core.common.config.AbstractConfigLoader;
import org.bukkit.configuration.file.FileConfiguration;

@IocBean
public class ServerSyncModuleLoader extends AbstractConfigLoader<ServerSyncConfiguration> {
    public static final String SERVER_SYNC_MODULE = "server-sync-module.";

    @Override
    protected ServerSyncConfiguration load(FileConfiguration config) {
        return new ServerSyncConfiguration(
            config.getBoolean(SERVER_SYNC_MODULE + "vanish-sync"),
            config.getBoolean(SERVER_SYNC_MODULE + "staffmode-sync"),
            config.getBoolean(SERVER_SYNC_MODULE + "ban-sync"),
            config.getBoolean(SERVER_SYNC_MODULE + "report-sync"),
            config.getBoolean(SERVER_SYNC_MODULE + "warning-sync"),
            config.getBoolean(SERVER_SYNC_MODULE + "mute-sync"),
            config.getBoolean(SERVER_SYNC_MODULE + "kick-sync"),
            config.getBoolean(SERVER_SYNC_MODULE + "investigation-sync")
        );
    }
}