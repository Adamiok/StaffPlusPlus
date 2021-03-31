package net.shortninja.staffplus.core.domain.staff.investigate.config;

public class InvestigationConfiguration {

    private final boolean enabled;
    private final String investigatePermission;
    private final String startInvestigationCmd;
    private final String pauseInvestigationCmd;
    private final String concludeInvestigationCmd;
    private final boolean titleMessageEnabled;

    public InvestigationConfiguration(boolean enabled,
                                      String investigatePermission,
                                      String startInvestigationCmd,
                                      String pauseInvestigationCmd,
                                      String concludeInvestigationCmd, boolean titleMessageEnabled) {
        this.enabled = enabled;
        this.investigatePermission = investigatePermission;
        this.startInvestigationCmd = startInvestigationCmd;
        this.pauseInvestigationCmd = pauseInvestigationCmd;
        this.concludeInvestigationCmd = concludeInvestigationCmd;
        this.titleMessageEnabled = titleMessageEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getInvestigatePermission() {
        return investigatePermission;
    }

    public String getStartInvestigationCmd() {
        return startInvestigationCmd;
    }

    public String getPauseInvestigationCmd() {
        return pauseInvestigationCmd;
    }

    public String getConcludeInvestigationCmd() {
        return concludeInvestigationCmd;
    }

    public boolean isTitleMessageEnabled() {
        return titleMessageEnabled;
    }
}