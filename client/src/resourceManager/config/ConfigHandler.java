package src.resourceManager.config;

import src.resourceManager.file.FileHandler;

/**
 * 
 * @author Oscar Andersson
 * 
 * This class manages the handling of the config-settings.
 * The class have the ability to load settings from files and also
 * handle the parametres of the running config and storing it to a config-file.
 */


public class ConfigHandler {

    private final FileHandler fh;
    private final String configPath;
    private static ConfigHandler instance = null;
    private String username;
    private int gameWidth;
    private int gameHeight;
    private String serverIp;
    private int serverPort;

    private ConfigHandler() {
    	configPath = "config.txt";
        fh = FileHandler.getInstance();
        gameWidth = 0;
        gameHeight = 0;
        serverPort = 0;
        readConfig();
        checkVars();
    }
    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }
    
    /* Reads a value related to a specific setting.
     *
    */
    public String readConfig() {
        final String tempString = fh.readFile(configPath);
        final String[] splitRows = tempString.split("\n");
        String[] splitCol;

        for (int i = 0; i < splitRows.length; i++) {
            splitCol = splitRows[i].split(",");
            switch (splitCol[0]) {
                case "username":
                    username = splitCol[1];
                    break;
                case "gameWidth":
                    gameWidth = Integer.parseInt(splitCol[1]);
                    break;
                case "gameHeight":
                    gameHeight = Integer.parseInt(splitCol[1]);
                    break;
                case "serverIp":
                	serverIp = splitCol[1];
                    break;
                case "serverPort":
                	serverPort = Integer.parseInt(splitCol[1]);
                    break;
            }
        }

        return null; //Marks that readConfig could not find the setting.
    }

    /* Overwrites a value related to a specific setting. 
     * 
    */
    public Boolean writeConfig() {
        final StringBuilder tempArr = new StringBuilder(); 
        tempArr.append("username,").append(username).append("\n");
        tempArr.append("gameWidth,").append(gameWidth).append("\n");
        tempArr.append("gameHeight,").append(gameHeight).append("\n");
        tempArr.append("serverIp,").append(serverIp).append("\n");
        tempArr.append("serverPort,").append(serverPort).append("\n");
        return fh.writeFile("config.txt", tempArr.toString());
   }
    
    /* Checks variables
     * If they are not assigned, assign default values to them
     */
    private void checkVars(){
    	if(serverIp == null){serverIp="127.0.0.1";}
    	if(serverPort == 0){serverPort=8989;}
    	if(gameHeight == 0){gameHeight=10;}
    	if(gameWidth == 0){gameWidth=16;}
    	if(username == null){username="Choose a username";}
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username, the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * @return the gameWidth
     */
    public int getGameWidth() {
        return gameWidth;
    }

    /**
     * @param gameWidth, the gameWidth to set
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
     * @param gameHeight, the gameHeight to set
     */
    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }
    
    /**
     * @return the serverIp
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * @param serverIp, the serverIp to set
     */
    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    /**
     * @return the serverPort
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * @param serverPort, the serverPort to set
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
