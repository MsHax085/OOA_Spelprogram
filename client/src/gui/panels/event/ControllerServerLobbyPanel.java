package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import src.gui.UserInterface;
import src.gui.panels.ServerLobbyPanel;

public class ControllerServerLobbyPanel implements MouseListener {
	
    private ServerLobbyPanel panel;
    
    public ControllerServerLobbyPanel(ServerLobbyPanel panel) {
        this.panel = panel;
    }
	
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	if (e.getSource().equals(panel.getQuitButton())) {
            UserInterface.changeCard("menupanel");
        } else if (e.getSource().equals(panel.getStartButton())) {
            //Core.getInstance().setStateObserver(new Game());
        }
    }
}
