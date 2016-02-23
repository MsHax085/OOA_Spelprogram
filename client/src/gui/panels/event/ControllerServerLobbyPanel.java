package src.gui.panels.event;

import java.awt.event.MouseListener;

import src.gui.panels.ServerLobbyPanel;

public class Controller implements MouseListener {
	
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
}
