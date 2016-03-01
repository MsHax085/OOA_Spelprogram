package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import src.gui.UserInterface;
import src.gui.panels.MenuPanel;
import src.network.Connection;
import src.network.PacketBuilder;

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
            //Core.getInstance().setStateObserver(new Game());
            try {
		Connection.getInstance().sendPacket(PacketBuilder.getInstance().create00RequestLobbyListPacket());
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
            UserInterface.changeCard("serverselectionpanel");
        } else if (e.getSource().equals(panel.getHostButton())) {
            UserInterface.changeCard("servercreationpanel");
        } else if (e.getSource().equals(panel.getHighscoreButton())) {
            UserInterface.changeCard("highscorepanel");
        } else if (e.getSource().equals(panel.getOptionsButton())) {
            UserInterface.changeCard("optionspanel");
        }
    }
}
    
    
