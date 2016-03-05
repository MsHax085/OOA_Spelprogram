package src.gui.panels;

import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import src.Core;

import src.gui.panels.event.ControllerStartPanel;
import src.resourceManager.config.ConfigHandler;

/**
 * Makes a startpanel
 * 
 * @author Richard
 * @version 2016-03-04
 */
public class StartPanel extends SuperPanel {
    
    private JButton continueButton;
    private JLabel icon;
    private JTextField usernameField;
    private JPanel container;
    
    public StartPanel(int width, int height) {
        super(width, height);
        controller = new ControllerStartPanel(this);
        container = new JPanel();
        icon = new JLabel(new ImageIcon(Core.class.getResource("resources/logo.png").getPath()));
        usernameField = new JTextField();
        continueButton = new JButton();

        container.setBackground(new Color(0, 0, 0, 0));

        usernameField.addMouseListener((MouseListener) controller);
        usernameField.setText(ConfigHandler.getInstance().getUsername());

        continueButton.setFocusable(false);
        continueButton.addMouseListener((MouseListener) controller);
        continueButton.setText("Continue");
        
        GroupLayout panel1Layout = new GroupLayout(container);
        container.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createParallelGroup()
                .addComponent(icon))
                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)// Add gap before
                .addComponent(icon)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 164, 164)
                .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(continueButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
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
    
    public JButton getContinueButton() {
        return continueButton;
    }
    
    public JTextField getUsernameInput() {
        return usernameField;
    }
}
