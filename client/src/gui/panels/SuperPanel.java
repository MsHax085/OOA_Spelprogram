package src.gui.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import src.Core;

/**
 *
 * @author Richard
 */
public class SuperPanel extends JPanel {
    
    protected EventListener controller;
    
    public SuperPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    public void paintComponent(Graphics g) {
        try {
            Image backgroundImage = ImageIO.read(new File(Core.class.getResource("resources/background.jpg").getPath()));
            g.drawImage(backgroundImage, 0, 0, 500, 500, null);
        } catch (IOException ex) {
            Logger.getLogger(SuperPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
