package net.shortninja.staffplus.core.domain.staff.joinmessages;

import be.garagepoort.mcioc.IocBean;
import be.garagepoort.mcioc.configuration.ConfigObjectList;
import be.garagepoort.mcioc.configuration.ConfigProperty;

import java.util.List;

@IocBean
public class JoinMessagesConfiguration {

    @ConfigProperty("joinmessages-module.enabled")
    public boolean enabled;

    @ConfigProperty("joinmessages-module.messages")
    @ConfigObjectList(JoinMessageGroup.class)
    public List<JoinMessageGroup> joinMessageGroups;
}
