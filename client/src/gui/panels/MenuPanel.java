package src.gui.panels;

import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import src.Core;
import src.gui.panels.event.ControllerMenuPanel;

/**
 *
 * @author Richard, Gustav
 */
public class MenuPanel extends SuperPanel {

    private JButton join;
    private JButton host;
    private JButton highscore;
    private JButton options;
    private JLabel icon;
    private JPanel container;
	
    public MenuPanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerMenuPanel(this);
        container = new JPanel();
        join = new JButton();
        icon = new JLabel(new ImageIcon(Core.class.getResource("resources/logo.png").getPath()));
        host = new JButton();
        highscore = new JButton();
        options = new JButton();
        
        container.setBackground(new Color(0, 0, 0, 0));

        join.setFocusable(false);
        join.addMouseListener((MouseListener) controller);
        join.setText("Join a lobby");

        host.setFocusable(false);
        host.addMouseListener((MouseListener) controller);
        host.setText("Start a new lobby");

        highscore.setFocusable(false);
        highscore.addMouseListener((MouseListener) controller);
        highscore.setText("Highscore");

        options.setFocusable(false);
        options.addMouseListener((MouseListener) controller);
        options.setText("Options");

        GroupLayout panel1Layout = new GroupLayout(container);
        container.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createParallelGroup()
                .addComponent(icon))
                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(join, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(host, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(highscore, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(options, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)// Add gap before
                .addComponent(icon)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 164, 164)
                .addComponent(join, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                .addComponent(host, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                .addComponent(highscore, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                .addComponent(options, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))// Add gap after
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    
    public JButton getJoinButton() {
        return join;
    }
    
    public JButton getHostButton() {
        return host;
    }
    
    public JButton getHighscoreButton() {
        return highscore;
    }
    
    public JButton getOptionsButton() {
        return options;
    }
}
