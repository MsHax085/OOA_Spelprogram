package src.gui;

import java.util.Observable;
import java.awt.CardLayout;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.Core;
import src.frame.DefaultFrameState;
import src.gui.panels.HighscorePanel;
import src.gui.panels.MenuPanel;
import src.gui.panels.OptionsPanel;
import src.gui.panels.Panels;
import src.gui.panels.ServerCreationPanel;
import src.gui.panels.ServerLobbyPanel;
import src.gui.panels.ServerSelectionPanel;
import src.gui.panels.StartPanel;
import src.gui.panels.event.ControllerStartPanel;

/**
 *
 * @author Richard
 */
public class UserInterface implements DefaultFrameState, Observer {

    private JFrame frame;
    //private JPanel visiblePanel;
    private JPanel panels;
    private CardLayout cl;
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
        
        panels = new JPanel();
        panels.setLayout(cl);
        /*
        this.visiblePanel = new StartPanel(500, 500);
        frame.add(this.visiblePanel);
        */
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
		 
		cl.show(panels, "startpanel");
        frame.add(panels);
        
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
                // Do something, ex. update username?
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
