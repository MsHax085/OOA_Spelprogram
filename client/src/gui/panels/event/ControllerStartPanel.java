package src.gui.panels.event;

import java.awt.event.MouseListener;
import src.Core;
import src.game.Game;
import src.gui.panels.StartPanel;

/**
 *
 * @author Richard
 */
public class ControllerStartPanel implements MouseListener {
    
    private StartPanel panel;

    
    public ControllerStartPanel(StartPanel panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (e.getSource().equals(panel.getStartButton())) {
            //Core.getInstance().setStateObserver(new Game());
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
