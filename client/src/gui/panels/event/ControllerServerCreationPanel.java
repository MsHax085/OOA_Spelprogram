package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import src.gui.panels.ServerCreationPanel;

public class ControllerServerCreationPanel implements MouseListener {
	
	private ServerCreationPanel panel;
    
    public ControllerServerCreationPanel(ServerCreationPanel panel) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

