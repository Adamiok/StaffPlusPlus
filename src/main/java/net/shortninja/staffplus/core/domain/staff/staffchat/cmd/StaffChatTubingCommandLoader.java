package net.shortninja.staffplus.core.domain.staff.staffchat.cmd;

import be.garagepoort.mcioc.IocMultiProvider;
import be.garagepoort.mcioc.TubingConfiguration;
import net.shortninja.staffplus.core.common.cmd.CommandService;
import net.shortninja.staffplus.core.common.cmd.SppCommand;
import net.shortninja.staffplus.core.application.config.Messages;
import net.shortninja.staffplus.core.application.config.Options;
import net.shortninja.staffplus.core.common.permissions.PermissionHandler;
import net.shortninja.staffplus.core.domain.chat.ChatInterceptor;
import net.shortninja.staffplus.core.domain.staff.staffchat.StaffChatChannelConfiguration;
import net.shortninja.staffplus.core.domain.staff.staffchat.StaffChatChatInterceptor;
import net.shortninja.staffplus.core.domain.staff.staffchat.StaffChatServiceImpl;
import net.shortninja.staffplus.core.domain.staff.staffchat.config.StaffChatConfiguration;
import net.shortninja.staffplus.core.application.session.SessionManagerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@TubingConfiguration
public class StaffChatTubingCommandLoader {

    @IocMultiProvider(SppCommand.class)
    public static List<SppCommand> loadCommands(Messages messages, Options options, StaffChatConfiguration staffChatConfiguration, SessionManagerImpl sessionManager, StaffChatServiceImpl staffChatService, CommandService commandService, PermissionHandler permissionHandler) {
        List<SppCommand> commands = new ArrayList<>();
        List<StaffChatChannelConfiguration> channelConfigurations = staffChatConfiguration.getChannelConfigurations();
        for (StaffChatChannelConfiguration channelConfiguration : channelConfigurations) {
            commands.add(new StaffChatChannelCmd(messages, options, sessionManager, staffChatService, commandService, channelConfiguration, permissionHandler));
            commands.add(new StaffChatMuteChannelCmd(messages, options, sessionManager, commandService, channelConfiguration, permissionHandler));
        }
        return commands;
    }

    @IocMultiProvider(ChatInterceptor.class)
    public static List<ChatInterceptor> loadChatInterceptors(StaffChatConfiguration staffChatConfiguration, SessionManagerImpl sessionManager, PermissionHandler permissionHandler, StaffChatServiceImpl staffChatService) {
        return staffChatConfiguration.getChannelConfigurations().stream()
            .map(c -> new StaffChatChatInterceptor(staffChatService, permissionHandler, sessionManager, c, staffChatConfiguration))
            .collect(Collectors.toList());
    }
}