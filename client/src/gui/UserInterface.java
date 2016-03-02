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
import src.gui.panels.ServerCreationPanel;
import src.gui.panels.ServerLobbyPanel;
import src.gui.panels.ServerSelectionPanel;
import src.gui.panels.StartPanel;
import src.network.Connection;
import src.network.NetworkBuffer;
import src.network.PacketBuilder;

/**
 *
 * @author Richard,++
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
    private ServerSelectionPanel serverSelectionPanel;
    private ServerCreationPanel serverCreationPanel;
    private ServerLobbyPanel serverLobbyPanel;
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
        this.serverSelectionPanel = new ServerSelectionPanel(500,500);
        this.serverCreationPanel = new ServerCreationPanel(500,500);
        this.serverLobbyPanel = new ServerLobbyPanel(500,500);
        this.highscorePanel = new HighscorePanel(500,500);
        this.optionsPanel = new OptionsPanel(500,500);

        panelContainer.add(startPanel, "startpanel");
        panelContainer.add(menuPanel, "menupanel");
        panelContainer.add(serverSelectionPanel, "serverselectionpanel");
        panelContainer.add(serverCreationPanel, "servercreationpanel");
        panelContainer.add(serverLobbyPanel, "serverlobbypanel");
        panelContainer.add(highscorePanel, "highscorepanel");
        panelContainer.add(optionsPanel, "optionspanel");
        
        if(panel == 1){
        	changeCard("startpanel");
        }else if(panel == 2){
        	changeCard("menupanel");
        }else if(panel == 3){
        	changeCard("serverselectionpanel");
        }else if(panel == 4){
        	changeCard("servercreationpanel");
        }else if(panel == 5){
        	changeCard("serverlobbypanel");
        }else if(panel == 6){
        	changeCard("highscorepanel");
        }else if(panel == 7){
        	changeCard("optionspanel");
        }
        frame.add(panelContainer);
    }
    
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
