package src.gui.panels.event;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.io.IOException;

import src.gui.UserInterface;
import src.gui.panels.StartPanel;
import src.network.Connection;
import src.network.PacketBuilder;
import src.resourceManager.config.ConfigHandler;
import javax.swing.JTextField;

/**
 *
 * @author Richard
 */
public class ControllerStartPanel implements MouseListener {
    
    private final StartPanel panel;
    private final JTextField usernameField;
    
    public ControllerStartPanel(StartPanel panel) {
        this.panel = panel;
        this.usernameField = panel.getUsernameInput();
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getSource().equals(panel.getContinueButton())) {
        	if(panel.getUsernameInput().getText().equals("Choose a username") || panel.getUsernameInput().getText().equals("")) {
        		//usernameField.setBackground(Color.RED);
        	}
        	else {
        		ConfigHandler.getInstance().setUsername(panel.getUsernameInput().getText());
        		try {
					Connection.getInstance().sendPacket(
							PacketBuilder.getInstance().create08ClientLoginPacket(panel.getUsernameInput().getText()));
	        		
				} catch (IOException e1) {
					System.out.println("Couldn't successfully send the packet");
					e1.printStackTrace();
				}
        		
        		
        	}}
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
