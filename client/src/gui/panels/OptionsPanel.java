package src.gui.panels;

import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.JPanel;
import java.awt.Color;

import src.gui.panels.event.ControllerOptionsPanel;
import src.resourceManager.config.ConfigHandler;

/**
 *
 * @author Richard, Gustav
 */
public class OptionsPanel extends SuperPanel {

    private JLabel text;
    private JLabel username;
    private JLabel serverIp;
    private JTextField usernameField;
    private JTextField serverIpField;
    private JButton save;
    private JButton prev;
    private JPanel container;
	
    public OptionsPanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerOptionsPanel(this);
        container = new JPanel();
        text = new JLabel("Options");
        username = new JLabel("Username");
        usernameField = new JTextField(ConfigHandler.getInstance().getUsername(), 20);
        serverIp = new JLabel("Current server IP-number");
        serverIpField = new JTextField(ConfigHandler.getInstance().getServerIp(), 20);
        serverIpField.setEditable(false); 
        prev = new JButton("Previous");
        save = new JButton("Save");

        container.setBackground(new Color(0, 0, 0, 0));

        prev.setFocusable(false);
        prev.addMouseListener((MouseListener) controller);

        save.setFocusable(false);
        save.addMouseListener((MouseListener) controller);

        GroupLayout panel1Layout = new GroupLayout(container);
        container.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverIp, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
                	.addComponent(serverIpField, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                	.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                	.addComponent(prev, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                	.addComponent(save, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
        ;
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)// Add gap before
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(username, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(serverIp, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(serverIpField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, 25)
                    .addGroup(panel1Layout.createParallelGroup()
                    .addComponent(prev, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(save, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addGap(98, 98, 98))// Add gap after
            ));
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    public JButton getPrevButton() {
        return prev;
    }
    
    public JButton getSaveButton() {
        return save;
    }
    
    public JTextField getUsernameInput() {
        return usernameField;
    }
    
    public JTextField getIpAdressInput() {
        return serverIpField;
    }
}
