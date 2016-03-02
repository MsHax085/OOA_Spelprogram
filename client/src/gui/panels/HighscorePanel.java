package src.gui.panels;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import src.gui.panels.event.ControllerHighscorePanel;

import src.resourceManager.Database;

/**
 *
 * @author Richard, Gustav
 */
public class HighscorePanel extends SuperPanel {

    private JPanel container;
    private JLabel text;
    private JTable highScoreList;
    private JButton prev;
	
    public HighscorePanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerHighscorePanel(this);
        container = new JPanel();
        text = new JLabel("Highscore");
        highScoreList = new JTable(20,1);
        prev = new JButton("Prev");
        
        container.setBackground(new Color(0, 0, 0, 0));
        
        highScoreList.setFocusable(false);
        updateHighscoreList();
        
        prev.setFocusable(false);
        prev.addMouseListener((MouseListener) controller);
        
        GroupLayout panel1Layout = new GroupLayout(container);
        container.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    	.addComponent(text, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(highScoreList, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    	.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                    		.addComponent(prev, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
        );
        
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)// Add gap before
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(highScoreList, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                    .addComponent(prev, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addGap(98, 98, 98))// Add gap after
            );
        
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
    
    public void updateHighscoreList() {
    	clearRows();
    	
    	final Iterator itr = Database.getInstance().getHighscore();
        while (itr.hasNext()) {
            addRow((String) itr.next());
        }
    }
    
    private void clearRows() {
    	DefaultTableModel model = (DefaultTableModel) highScoreList.getModel();
    	model.setRowCount(0);
    }
    
    private void addRow(String str) {
        DefaultTableModel model = (DefaultTableModel) highScoreList.getModel();
        model.addRow(new Object[]{str});
    }
    
    public JButton getPrevButton() {
        return prev;
    }
}
