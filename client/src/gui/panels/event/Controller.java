package src.gui.panels.event;

import java.awt.event.MouseListener;
import src.Core;

/**
 *
 * @author Richard
 */
public class Controller implements MouseListener {
    
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        Core.getInstance().changeUsername("NEWNAME");
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
