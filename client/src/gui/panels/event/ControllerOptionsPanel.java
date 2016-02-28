package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import src.gui.UserInterface;
import src.gui.panels.OptionsPanel;
import src.resourceManager.config.ConfigHandler;
import src.network.Connection;

public class ControllerOptionsPanel implements MouseListener {
	
    private OptionsPanel panel;
    
    public ControllerOptionsPanel(OptionsPanel panel) {
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
    	else if (e.getSource().equals(panel.getSaveButton())) {
    		if (!panel.getUsernameInput().getText().equals("")) {
    			ConfigHandler.getInstance().setUsername(panel.getUsernameInput().getText());
    		}
    		else if (!panel.getIpAdressInput().getText().equals("")) {
    			Connection.getInstance().changeIpAdress(panel.getIpAdressInput().getText());
    			System.out.println("HEJ");
    		}
        }
    }
}
