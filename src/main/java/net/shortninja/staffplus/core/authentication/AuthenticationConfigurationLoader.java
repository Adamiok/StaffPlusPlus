package net.shortninja.staffplus.core.authentication;

import be.garagepoort.mcioc.IocBean;
import net.shortninja.staffplus.core.application.config.AbstractConfigLoader;
import net.shortninja.staffplus.core.common.exceptions.BusinessException;

import static net.shortninja.staffplus.core.authentication.AuthenticationProvider.AUTHME;
import static org.bukkit.Bukkit.getServer;

@IocBean
public class AuthenticationConfigurationLoader extends AbstractConfigLoader<AuthenticationConfiguration> {

    @Override
    protected AuthenticationConfiguration load() {
        String authenticationProviderName = defaultConfig.getString("authentication.provider", "NOOP");
        AuthenticationProvider authenticationProvider = AuthenticationProvider.valueOf(authenticationProviderName.toUpperCase());
        if (authenticationProvider == AUTHME && getServer().getPluginManager().getPlugin("AuthMe") == null) {
            throw new BusinessException("&CIncorrect authentication configuration. AuthMe configured but plugin is not loaded");
        }
        return new AuthenticationConfiguration(authenticationProvider);
    }
}
