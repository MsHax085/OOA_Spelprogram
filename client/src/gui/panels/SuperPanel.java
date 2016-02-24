package src.gui.panels;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Richard
 */
public class SuperPanel extends JPanel {
    
    protected MouseListener controller;
    
    public SuperPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }
}
