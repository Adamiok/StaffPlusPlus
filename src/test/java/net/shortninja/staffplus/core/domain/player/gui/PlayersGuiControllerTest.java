package net.shortninja.staffplus.core.domain.player.gui;

import net.shortninja.staffplus.core.common.gui.AbstractGuiTemplateTest;
import net.shortninja.staffplus.core.common.gui.GuiUtils;
import net.shortninja.staffplus.core.domain.player.PlayerManager;
import net.shortninja.staffplus.core.domain.player.ip.database.PlayerIpRepository;
import net.shortninja.staffplus.core.domain.staff.ban.ipbans.IpBanConfiguration;
import net.shortninja.staffplus.core.domain.staff.ban.ipbans.IpBanService;
import net.shortninja.staffplus.core.domain.staff.ban.playerbans.BanService;
import net.shortninja.staffplus.core.domain.staff.ban.playerbans.config.BanConfiguration;
import net.shortninja.staffplus.core.domain.staff.mute.MuteService;
import net.shortninja.staffplus.core.domain.staff.mute.config.MuteConfiguration;
import net.shortninja.staffplus.core.domain.staff.reporting.ReportService;
import net.shortninja.staffplus.core.domain.staff.warn.warnings.config.WarningConfiguration;
import net.shortninja.staffplusplus.session.SppPlayer;
import net.shortninja.staffplusplus.warnings.WarningService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayersGuiControllerTest extends AbstractGuiTemplateTest {

    @Mock
    private PlayerManager playerManager;
    @Mock
    private BanService banService;
    @Mock
    private IpBanService ipBanService;
    @Mock
    private BanConfiguration banConfiguration;
    @Mock
    private IpBanConfiguration ipBanConfiguration;
    @Mock
    private MuteService muteService;
    @Mock
    private MuteConfiguration muteConfiguration;
    @Mock
    private PlayerIpRepository playerIpRepository;
    @Mock
    private ReportService reportService;
    @Mock
    private WarningService warningService;
    @Mock
    private WarningConfiguration warningConfiguration;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private SppPlayer sppPlayer1;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private SppPlayer sppPlayer2;


    @Captor
    private ArgumentCaptor<String> xmlCaptor;
    private static MockedStatic<GuiUtils> guiUtilsMockedStatic;

    @BeforeAll
    public static void beforeAll() {
        guiUtilsMockedStatic = mockStatic(GuiUtils.class);
        guiUtilsMockedStatic.when(() -> GuiUtils.getNextPage(anyString(), anyInt())).thenReturn("goNext");
        guiUtilsMockedStatic.when(() -> GuiUtils.getPreviousPage(anyString(), anyInt())).thenReturn("goPrevious");
    }

    @Override
    public Object getGuiController() {
        return new PlayersGuiController(playerManager,
            banService,
            ipBanService,
            banConfiguration,
            ipBanConfiguration,
            muteService,
            muteConfiguration,
            playerIpRepository,
            reportService,
            warningService,
            warningConfiguration);
    }

    @Test
    public void selectOverviewType() {
        guiActionService.executeAction(player, "players/view/select-overview-type");

        verify(tubingGuiXmlParser).parseHtml(eq(player), xmlCaptor.capture());
        validateMaterials(xmlCaptor.getValue());
    }

    @Test
    public void onlineOverview() {
        when(playerManager.getOnlineSppPlayers()).thenReturn(Arrays.asList(sppPlayer1, sppPlayer2));
        when(sppPlayer1.getUsername()).thenReturn("player1");
        when(sppPlayer2.getUsername()).thenReturn("player2");
        guiUtilsMockedStatic.when(() -> GuiUtils.getSession(sppPlayer1)).thenReturn(Optional.empty());
        guiUtilsMockedStatic.when(() -> GuiUtils.getSession(sppPlayer2)).thenReturn(Optional.empty());

        guiActionService.executeAction(player, "players/view/overview/online?backAction=goBack/view");

        verify(tubingGuiXmlParser).parseHtml(eq(player), xmlCaptor.capture());
        validateMaterials(xmlCaptor.getValue());
    }

    @Test
    public void offlineOverview() {
        when(playerManager.getOfflinePlayers()).thenReturn(Arrays.asList(sppPlayer1, sppPlayer2));
        when(sppPlayer1.getUsername()).thenReturn("player1");
        when(sppPlayer2.getUsername()).thenReturn("player2");
        guiUtilsMockedStatic.when(() -> GuiUtils.getSession(sppPlayer1)).thenReturn(Optional.empty());
        guiUtilsMockedStatic.when(() -> GuiUtils.getSession(sppPlayer2)).thenReturn(Optional.empty());

        guiActionService.executeAction(player, "players/view/overview/offline?backAction=goBack/view");

        verify(tubingGuiXmlParser).parseHtml(eq(player), xmlCaptor.capture());
        validateMaterials(xmlCaptor.getValue());
    }

    @Test
    public void playerDetail() {
        when(playerManager.getOnOrOfflinePlayer("garagepoort")).thenReturn(Optional.of(sppPlayer1));
        when(sppPlayer1.getUsername()).thenReturn("garagepoort");
        guiUtilsMockedStatic.when(() -> GuiUtils.getSession(sppPlayer1)).thenReturn(Optional.empty());

        guiActionService.executeAction(player, "players/view/detail?targetPlayerName=garagepoort&backAction=goBack/view");

        verify(tubingGuiXmlParser).parseHtml(eq(player), xmlCaptor.capture());
        validateMaterials(xmlCaptor.getValue());
    }
}