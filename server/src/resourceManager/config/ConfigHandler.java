package src.resourceManager.config;

import src.resourceManager.file.FileHandler;

/**
 * 
 * @author Oscar Andersson
 * TODO Config based on java.util.prefs
 */


public class ConfigHandler implements ImplConfigHandler {

    private final FileHandler fh;
    private final String configPath;
    
    private String username;
    private int gameWidth;
    private int gameHeight;

    public ConfigHandler() {
    	configPath = "config.txt";
        fh = FileHandler.getInstance();
        
        readConfig();
    }
    
    /* Reads a value related to a specific setting.
     *
    */
    @Override
    public String readConfig() {
        final String tempString = fh.readFile(configPath);
        final String[] splitRows = tempString.split("\n");
        String[] splitCol;

        for (int i = 0; i < splitRows.length; i++) {
            splitCol = splitRows[i].split(",");
            if (splitCol[0].equals("username")){
                username = splitCol[1];
            }
            else if (splitCol[0].equals("gameWidth")){
                gameWidth = Integer.parseInt(splitCol[1]);
            }
            else if (splitCol[0].equals("gameHeight")){
                gameHeight = Integer.parseInt(splitCol[1]);
            }
        }

        return null; //Marks that readConfig could not find the setting.
    }

    /* Overwrites a value related to a specific setting. 
     * 
    */
    @Override
    public Boolean writeConfig() {
        final StringBuilder tempArr = new StringBuilder(); 
        tempArr.append("username,"+username+"\n");
        tempArr.append("gameWidth,"+gameWidth+"\n");
        tempArr.append("gameHeight,"+gameHeight+"\n");
	    if (fh.writeFile("config.txt", tempArr.toString())){
	    	return true;
	    }
	    else {
	    	return false;
	    }
   }

    /**
     * @return the gameWidth
     */
    public int getGameWidth() {
        return gameWidth;
    }

    /**
     * @param gameWidth the gameWidth to set
     */
    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    /**
     * @return the gameHeight
     */
    public int getGameHeight() {
        return gameHeight;
    }

    /**
     * @param gameHeight the gameHeight to set
     */
    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }
}
