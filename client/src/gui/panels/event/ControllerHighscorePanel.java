package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import src.gui.UserInterface;
import src.gui.panels.HighscorePanel;
import src.network.Connection;
import src.network.PacketBuilder;

/**
 * Controlls all the actions allowed in the highscorepanel.
 * 
 * @author Gustav Ländström
 * @version 2016-03-04
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
        } else if (e.getSource().equals(panel.getRefreshButton())) {
            try {
		Connection.getInstance().sendPacket(PacketBuilder.getInstance().create04RequestHighscorePacket((Integer) panel.getSpinner().getValue()));
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
        }
    }
}
