package src.gui;

import java.util.Observable;
import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.frame.DefaultFrameState;
import src.gui.panels.HighscorePanel;
import src.gui.panels.MenuPanel;
import src.gui.panels.OptionsPanel;
import src.gui.panels.ServerCreationPanel;
import src.gui.panels.ServerLobbyPanel;
import src.gui.panels.ServerSelectionPanel;
import src.gui.panels.StartPanel;
import src.network.NetworkBuffer;

/**
 *
 * @author Richard
 */
public class UserInterface implements WindowListener, DefaultFrameState, Observer {

    private JFrame frame;
    private static JPanel panels;
    private static CardLayout cl;
    private JPanel startPanel;
    private JPanel menuPanel;
    private JPanel serverSelectionPanel;
    private JPanel serverCreationPanel;
    private JPanel serverLobbyPanel;
    private JPanel highscorePanel;
    private JPanel optionsPanel;
    
    @Override
    public void setup() {
        frame = new JFrame();
        frame.setTitle("Spelprogram");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.addWindowListener(this);
        
        panels = new JPanel();
        cl = new CardLayout();
        panels.setLayout(cl);

        this.startPanel = new StartPanel(500,500);
        this.menuPanel = new MenuPanel(500,500);
        this.serverSelectionPanel = new ServerSelectionPanel(500,500);
        this.serverCreationPanel = new ServerCreationPanel(500,500);
        this.serverLobbyPanel = new ServerLobbyPanel(500,500);
        this.highscorePanel = new HighscorePanel(500,500);
        this.optionsPanel = new OptionsPanel(500,500);

        panels.add(startPanel, "startpanel");
        panels.add(menuPanel, "menupanel");
        panels.add(serverSelectionPanel, "serverselectionpanel");
        panels.add(serverCreationPanel, "servercreationpanel");
        panels.add(serverLobbyPanel, "serverlobbypanel");
        panels.add(highscorePanel, "highscorepanel");
        panels.add(optionsPanel, "optionspanel");

        changeCard("startpanel");
        frame.add(panels);
        
        NetworkBuffer.getInstance().addNewObserver(this);
    }
    
    public static void changeCard(String panelName) {
        cl.show(panels, panelName);
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
        switch (val) {
            case 0:
                System.out.println("NETWORK EVENT");
                if (NetworkBuffer.getInstance().hasNext()) {
                    NetworkBuffer.getInstance().getNext().handlePacket();
                }
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        NetworkBuffer.getInstance().removeOldObserver(this);
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
