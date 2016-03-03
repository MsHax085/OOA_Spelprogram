package src.gui.panels;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.JPanel;

import src.gui.panels.event.ControllerServerCreationPanel;

/**
 *
 * @author Richard, Gustav
 */
public class ServerCreationPanel extends SuperPanel {

    private JLabel text;
    private JLabel serverName;
    private JLabel serverPassword;
    private JTextField serverNameField;
    private JPasswordField serverPasswordField;
    private JButton create;
    private JButton prev;
    private JPanel container;
	
    public ServerCreationPanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerServerCreationPanel(this);
        container = new JPanel();
        text = new JLabel("Options");
        serverName = new JLabel("Lobby name");
        serverNameField = new JTextField(20);
        serverPassword = new JLabel("Password (Optional)");
        serverPasswordField = new JPasswordField(20);
        prev = new JButton("Previous");
        create = new JButton("Create");

        container.setBackground(new Color(0, 0, 0, 0));

        prev.setFocusable(false);
        prev.addMouseListener((MouseListener) controller);

        create.setFocusable(false);
        create.addMouseListener((MouseListener) controller);

        GroupLayout panel1Layout = new GroupLayout(container);
        container.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverName, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverNameField, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverPassword, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE))
                	.addComponent(serverPasswordField, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                	.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                	.addComponent(prev, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                	.addComponent(create, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
        ;
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)// Add gap before
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(serverName, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(serverNameField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(serverPassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(serverPasswordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, 25)
                    .addGroup(panel1Layout.createParallelGroup()
                    .addComponent(prev, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(create, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
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
    
    public JButton getCreateButton() {
        return create;
    }
    
    public JTextField getServerNameInput() {
        return serverNameField;
    }
    
    public JTextField getServerPasswordInput() {
        return serverPasswordField;
    }
}

	
