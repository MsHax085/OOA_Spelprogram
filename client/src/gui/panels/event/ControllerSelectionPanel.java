package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import src.Core;
import src.game.Game;
import src.gui.UserInterface;
import src.gui.panels.ServerSelectionPanel;

public class ControllerSelectionPanel implements MouseListener {
	
	private ServerSelectionPanel panel;
    
    public ControllerSelectionPanel(ServerSelectionPanel panel) {
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
    		UserInterface.changeCard("serverlobbypanel");
    		//Core.getInstance().setStateObserver(new Game());
    	}
    	if (e.getSource().equals(panel.getPrevButton())) {
            UserInterface.changeCard("menupanel");
    	}
    }
}
