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
    private String test;
    private int gameWidth;
    private int gameHeight;

    public ConfigHandler(String configPath) {
        this.configPath = configPath;
        fh = FileHandler.getInstance();
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
            if (splitCol[0].equals(setting)){
                return splitCol[1];
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
        final String tempString = fh.readFile(configPath);
        final String[] splitRows = tempString.split("\n");
        String[] splitCol;
        for(int i = 0; i < splitRows.length; i++) {
            splitCol = splitRows[i].split(",");

            if (splitCol[0].equals(setting)) {
                splitCol[1] = value;
                int p;
                for(p = 0; p < i; p++){
                    tempArr.append(splitRows[p]);
                    tempArr.append("\n");
                }
                tempArr.append(splitCol[0]).append(",").append(value);
                tempArr.append("\n");	
                p++; 
                while(p < splitRows.length) {
                    tempArr.append(splitRows[p]);
                    tempArr.append("\n");
                    p++;
                }
                fh.writeFile("config.txt", tempArr.toString());
                return true;
            }
        }
        return false;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the test
     */
    public String getTest() {
        return test;
    }

    /**
     * @param test the test to set
     */
    public void setTest(String test) {
        this.test = test;
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
