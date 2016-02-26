package src.gui.panels.event;

import java.awt.event.MouseListener;
import src.gui.UserInterface;
import src.gui.panels.StartPanel;

/**
 *
 * @author Richard
 */
public class ControllerStartPanel implements MouseListener {
    
    private final StartPanel panel;
    
    public ControllerStartPanel(StartPanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getSource().equals(panel.getContinueButton())) {
            UserInterface.changeCard("menupanel");
        } else if (e.getSource().equals(panel.getUsernameInput())) {
            if (panel.getUsernameInput().getText().equals("Choose a username")) {
                panel.getUsernameInput().setText("");
            }
        }
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
