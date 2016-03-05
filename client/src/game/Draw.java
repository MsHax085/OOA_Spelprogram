package src.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.game.entities.Entity;
import src.resourceManager.Material;
import src.resourceManager.Resources;
import src.resourceManager.config.ConfigHandler;

/**
 *	Draws is a JPanel that displays a game playing field
 *	the field could belong to anyone, the only thing draw 
 *	does is to draw the passed list of entities
 *  
 * 	@author ludwigfriborg
 * 	@vesion 2016-02-29
 */

public class Draw extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Entity> list;
    private String name;
    private int blockSize, gameWidth, gameHeight;
    private int time;
    private boolean hasFinished, isGameWinner;

    public Draw(ArrayList<Entity> list, String name, int blockSize){
        this.list = list; 
        this.blockSize = blockSize;
        this.name = name;
        hasFinished = false;
        isGameWinner = false;

        try{
            this.gameWidth = ConfigHandler.getInstance().getGameWidth();
            this.gameHeight = ConfigHandler.getInstance().getGameHeight();
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("Draw - Couldn't read from config.");

            this.gameWidth = 16;
            this.gameHeight = 10;
        }

        Resources.getInstance().loadImages();
        this.setPreferredSize(new Dimension(gameWidth*this.blockSize, gameHeight*this.blockSize));
    }

    /**
     * Draws the desired entitylist to the panel
     * @param list - desired entitylist 
     */
    public void drawList(ArrayList<Entity> list){
        this.list = list;
        repaint();
    }
    
    /**
     * Draws the desired entitylist to the panel aswell as updating the timestamp
     * @param list - desired entitylist 
     * @param time - new time
     */
    public void drawList(ArrayList<Entity> list, int time){
        this.list = list;
        setTime(time);
        repaint();
    }

    public void setHasFinished(){
        hasFinished = true;
    }

    public void setIsWinner(){
        isGameWinner = true;
    }

    public void setTime(int time){
        this.time = time;
    }

    public String getName(){
    	return name;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public ArrayList<Entity> getList(){
        return list;
    }

    public boolean getHasFinished(){
        return hasFinished;
    }

    @Override
    public void paint(Graphics g) {
        for (int x = 0; x < gameWidth * blockSize; x += blockSize) {
            for (int y = 0; y < gameHeight * blockSize; y += blockSize) {
                g.drawImage(Resources.getInstance().getImage(Material.SAND.getValue()), x, y, blockSize, blockSize, null);
            }
        }

        for (Entity ent : list){
            ent.draw(g, blockSize);
        }

        if (hasFinished) {
            g.setColor(new Color(0,0,0,0.8f));
            g.fillRect(0, 0, gameWidth * blockSize, gameHeight * blockSize);
            if (isGameWinner) {
                g.setColor(Color.magenta);
                g.drawString("The winner!", 10, 50);
            }
        }

        if (time != 0) {
            g.setColor(Color.white);
            g.drawString("time: " + time, 10, 20);
        }
    }
}
