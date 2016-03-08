package src.resourceManager.config;

import src.resourceManager.file.FileHandler;

/**
 * This class manages the handling of the config-settings.
 * The class have the ability to load settings from files and also
 * handle the parametres of the running config and storing it to a config-file.
 * 
 * @author Oscar Andersson
 * @version 2016-3-01
 */


public class ConfigHandler {

    private final FileHandler fh;
    private final String configPath;
    private static ConfigHandler instance = null;
    private int portNumber;
    private int maxClientsLobby;
    private int numberOfMaps;
    
    private ConfigHandler() {
    	configPath = "config.txt";
        fh = FileHandler.getInstance();
        portNumber=0;
        maxClientsLobby=0;
        numberOfMaps=0;
        readConfig();
        checkVars();
    }
    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }
    
    /*
     * Reads a value related to a specific setting.
    */
    public void readConfig() {
        final String tempString = fh.readFile(configPath);
        final String[] splitRows = tempString.split("\n");
        String[] splitCol;

        for (int i = 0; i < splitRows.length; i++) {
            splitCol = splitRows[i].split(",");
            switch (splitCol[0]) {
                case "portNumber":
                    portNumber = Integer.parseInt(splitCol[1]);
                    break;
                case "maxClientsLobby":
                	maxClientsLobby = Integer.parseInt(splitCol[1]);
                    break;
                case "numberOfMaps":
                    numberOfMaps = Integer.parseInt(splitCol[1]);
                    break;
            }
        }
    }

    /**
     * Overwrites a value related to a specific setting. 
     * @return true if successful config write, else false.
    */
    public Boolean writeConfig() {
        final StringBuilder tempArr = new StringBuilder(); 
        tempArr.append("portNumber,").append(portNumber).append("\n");
        tempArr.append("maxClientsLobby,").append(maxClientsLobby).append("\n");
        tempArr.append("numberOfMaps,").append(numberOfMaps).append("\n");
        return fh.writeFile(configPath, tempArr.toString());
   }
    
    /* Checks variables
     * If they are not assigned, assign default values to them
     */
    private void checkVars(){
    	if(portNumber == 0){portNumber=8989;}
    	if(maxClientsLobby == 0){maxClientsLobby=5;}
    	if(numberOfMaps == 0){numberOfMaps=2;}
    }

    /**
     * @return portNumber
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * @param portNumber to be set
     */
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * @return maxClientsLobby
     */
    public int getMaxClientsLobby() {
        return maxClientsLobby;
    }

    /**
     * @param maxClientsLobby to be set
     */
    public void setMaxClientsLobby(int maxClientsLobby) {
        this.maxClientsLobby = maxClientsLobby;
    }
    
    /**
     * @return numberOfMaps
     */
    public int getNumberOfMaps() {
        return numberOfMaps;
    }

    /**
     * @param numberOfMaps to be set 
     */
    public void setNumberOfMaps(int numberOfMaps) {
        this.numberOfMaps = numberOfMaps;
    }
    
}
