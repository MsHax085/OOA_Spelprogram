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
public class HighscorePanel extends SuperPanel {


	private JLabel text;
	private JTable highScoreList;
	private JButton prev;
	
    public HighscorePanel(int width, int height) {
        super(width, height);
        
        this.setLayout(null);
         
        JLabel text = new JLabel("Highscore");
		text.setSize(300,50);
		text.setLocation(50,50);
		this.add(text);
			 
		JTable highScoreList = new JTable(14,1);
		highScoreList.setSize(400,225);
		highScoreList.setLocation(50, 100);
		this.add(highScoreList);	
			
		JButton prev = new JButton("Prev");
		prev.setSize(125,50);
		prev.setLocation(325,350);
		this.add(prev);
		 
    }
}
