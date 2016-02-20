package src.gui.panels;

import java.awt.Dimension;
import javax.swing.JPanel;
import src.gui.panels.event.ControllerStartPanel;

/**
 *
 * @author Richard
 */
public class SuperPanel extends JPanel {
    
    protected ControllerStartPanel controller;
    
    public SuperPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }
}
