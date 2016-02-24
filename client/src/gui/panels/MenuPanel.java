package src.gui.panels;

import javax.swing.JButton;
import src.gui.panels.event.ControllerMenuPanel;

/**
 *
 * @author Richard
 */
public class MenuPanel extends SuperPanel {

	private  JButton join;
	private  JButton host;
	private  JButton highscore;
	private  JButton options;
	
    public MenuPanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerMenuPanel(this);
        this.setLayout(null);
        
        join = new JButton("Join");
        join.setSize(300,50);
        join.setLocation(100,50);
        join.addMouseListener(controller);
        this.add(join);

        host = new JButton("Host");
        host.setSize(300,50);
        host.setLocation(100,150);
        this.add(host);

        highscore = new JButton("Highscore");
        highscore.setSize(300,50);
        highscore.setLocation(100,250);
        this.add(highscore);

        options = new JButton("Options");
        options.setSize(300,50);
        options.setLocation(100,350);
        this.add(options);
    }
    
    public JButton getJoinButton() {
        return join;
    }
}
