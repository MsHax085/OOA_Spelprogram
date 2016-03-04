package src.gui;

import java.util.Observable;
import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.Changes;
import src.Core;
import src.frame.DefaultFrameState;
import src.gui.panels.HighscorePanel;
import src.gui.panels.MenuPanel;
import src.gui.panels.OptionsPanel;
import src.gui.panels.LobbyCreationPanel;
import src.gui.panels.LobbyPanel;
import src.gui.panels.LobbySelectionPanel;
import src.gui.panels.StartPanel;
import src.network.Connection;
import src.network.NetworkBuffer;
import src.network.PacketBuilder;
import src.resourceManager.config.ConfigHandler;

/**
 *
 * @author Richard,++
 * 
 * UserInterface
 * Initiates the JFrame for the GUI. changes between the panels in the cardlayout.
 * 
 * @param Which panel you want to open, 1 for start, 2 for menu, 3 for serverselection,
 * 4 for servercreation, 5 for serverlobby, 6 for highscore, 7 for optionspanel
 */
public class UserInterface implements WindowListener, DefaultFrameState, Observer {

    private JFrame frame;
    private static JPanel panelContainer;
    private static CardLayout cl;
    private StartPanel startPanel;
    private MenuPanel menuPanel;
    private LobbySelectionPanel serverSelectionPanel;
    private LobbyCreationPanel serverCreationPanel;
    private LobbyPanel serverLobbyPanel;
    private HighscorePanel highscorePanel;
    private OptionsPanel optionsPanel;
    private int panel;
    private String panelName;
    
    public UserInterface(int panel){
    	this.panel = panel;
    }
    
    @Override
    public void setup() {
        NetworkBuffer.getInstance().addNewObserver(this);
        
        frame = new JFrame();
        frame.setTitle("Spelprogram");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(this);
        
        panelContainer = new JPanel();
        cl = new CardLayout();
        panelContainer.setLayout(cl);

        this.startPanel = new StartPanel(500,500);
        this.menuPanel = new MenuPanel(500,500);
        this.serverSelectionPanel = new LobbySelectionPanel(500,500);
        this.serverCreationPanel = new LobbyCreationPanel(500,500);
        this.serverLobbyPanel = new LobbyPanel(500,500);
        this.highscorePanel = new HighscorePanel(500,500);
        this.optionsPanel = new OptionsPanel(500,500);

        panelContainer.add(startPanel, "startpanel");
        panelContainer.add(menuPanel, "menupanel");
        panelContainer.add(serverSelectionPanel, "lobbyselectionpanel");
        panelContainer.add(serverCreationPanel, "lobbycreationpanel");
        panelContainer.add(serverLobbyPanel, "lobbypanel");
        panelContainer.add(highscorePanel, "highscorepanel");
        panelContainer.add(optionsPanel, "optionspanel");
        
        if(panel == 1){
        	changeCard("startpanel");
        }else if(panel == 2){
        	changeCard("menupanel");
        }else if(panel == 3){
        	changeCard("lobbyselectionpanel");
        }else if(panel == 4){
        	changeCard("lobbycreationpanel");
        }else if(panel == 5){
        	changeCard("lobbypanel");
        }else if(panel == 6){
        	changeCard("highscorepanel");
        }else if(panel == 7){
        	changeCard("optionspanel");
        }
        frame.add(panelContainer);
    }
    
    /**
     * Changes the panel shown in the cardlayout
     * @param panelName
     */
    public static void changeCard(String panelName) {
        cl.show(panelContainer, panelName);
    }

    @Override
    public void view() {
        frame.setVisible(true);
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }

    @Override
    public void dispose() {
        // Clear all
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UserInterface notified!");
        final int val = (int) arg;
        if (val == Changes.PACKET_RECEIVED.getValue()) {
            System.out.println("Packet received!");
            if (NetworkBuffer.getInstance().hasNext()) {
                NetworkBuffer.getInstance().getNext().handlePacket();
            }
        } else
        if (val == Changes.LOBBYLIST_CHANGE.getValue()) {
            System.out.println("Lobby updated!");
            serverSelectionPanel.updateServerList();
        } else if (val == Changes.CLIENTLIST_CHANGE.getValue()) {
            System.out.println("Client list updated!");
            serverLobbyPanel.updateLobbyList();
        } else if (val == Changes.HIGHSCORE_UPDATE.getValue()) {
            System.out.println("Update Highscore!");
            highscorePanel.updateHighscoreList();
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
	try {
	    Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLogoutPacket());
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	ConfigHandler.getInstance().writeConfig();
        NetworkBuffer.getInstance().removeOldObserver(this);
        Core.getInstance().stop();
        // TODO: CLOSE EVERYTHING BEFORE EXIT
        System.exit(0);
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
}
