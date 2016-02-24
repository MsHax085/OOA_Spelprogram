package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import src.Core;
import src.game.Game;

import src.gui.panels.MenuPanel;

public class ControllerMenuPanel implements MouseListener {
	
	private MenuPanel panel;
    
    public ControllerMenuPanel(MenuPanel panel) {
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
        if (e.getSource().equals(panel.getJoinButton())) {
            Core.getInstance().setStateObserver(new Game());
        }
    }
}
