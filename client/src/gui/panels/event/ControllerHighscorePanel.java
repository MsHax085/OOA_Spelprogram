package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import src.gui.UserInterface;
import src.gui.panels.HighscorePanel;

/**
 * 
 * @author Gustav
 * 
 * ControllerHighscorePanel
 * Controlls all the actions allowed in the highscorepanel
 */
public class ControllerHighscorePanel implements MouseListener {
	
    private HighscorePanel panel;
    
    public ControllerHighscorePanel(HighscorePanel panel) {
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
    	if (e.getSource().equals(panel.getPrevButton())) {
            UserInterface.changeCard("menupanel");
        } 
    }
}
