package src.gui.panels;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Richard
 */
public class ServerSelectionPanel extends SuperPanel {
    
	private JLabel text;
	private JTable serverList;
	private JButton join;
	private JButton prev;
	
    public ServerSelectionPanel(int width, int height) {
        super(width, height);
        
        this.setLayout(null);
		 
        JLabel text = new JLabel("Choose a server");
        text.setSize(300,50);
        text.setLocation(50,50);
        this.add(text);

        JTable serverList = new JTable(14,1);
        serverList.setSize(400,225);
        serverList.setLocation(50, 100);
        this.add(serverList);

        JButton prev = new JButton("Prev");
        prev.setSize(125,50);
        prev.setLocation(175,350);
        this.add(prev);

        JButton join = new JButton("Join");
        join.setSize(125,50);
        join.setLocation(325,350);
        this.add(join);
    }
}
