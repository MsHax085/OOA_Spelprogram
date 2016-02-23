package src.gui.panels;

import java.awt.CardLayout;

import javax.swing.JPanel;
import src.gui.panels.StartPanel;
import src.gui.panels.HighscorePanel;
import src.gui.panels.MenuPanel;
import src.gui.panels.OptionsPanel;
import src.gui.panels.ServerCreationPanel;
import src.gui.panels.ServerLobbyPanel;
import src.gui.panels.ServerSelectionPanel;

public class Panels {
	
	 private JPanel panels;
	 private CardLayout cl;
	 private JPanel startPanel;
	 private JPanel menuPanel;
	 private JPanel serverSelectionPanel;
	 private JPanel serverCreationPanel;
	 private JPanel serverLobbyPanel;
	 private JPanel highscorePanel;
	 private JPanel optionsPanel;
	 
	 public Panels() {
		 JPanel panels = new JPanel();
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
		 
		 cl.show(panels, "startpanel");
	 }

}
