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
    /**
     * Returns the current instance of ConfigHandler.
     * If there is none, one will be created.
     * 
     * @return The instance of ConfigHandler.
     */
    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
    }
    
    /**
     * Reads setting values from a file and saves them in variables.
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
    
    /**
     * Checks variables
     * If they are not assigned, assign default values to them
     */
    private void checkVars(){
    	if(portNumber == 0){portNumber=8989;}
    	if(maxClientsLobby == 0){maxClientsLobby=5;}
    	if(numberOfMaps == 0){numberOfMaps=2;}
    }

    /**
     * Gets the portnumber setting values
     * 
     * @return portNumber
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Sets the portnumber setting values
     * 
     * @param portNumber to be set
     */
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * Gets the MaxClientsLobby setting values
     * 
     * @return maxClientsLobby
     */
    public int getMaxClientsLobby() {
        return maxClientsLobby;
    }

    /**
     * Sets the MaxClientsLobby setting values
     * 
     * @param maxClientsLobby to be set
     */
    public void setMaxClientsLobby(int maxClientsLobby) {
        this.maxClientsLobby = maxClientsLobby;
    }
    
    /**
     * Gets the NumberOfMaps setting values
     * 
     * @return numberOfMaps
     */
    public int getNumberOfMaps() {
        return numberOfMaps;
    }

    /**
     * Sets the NumberOfMaps setting values
     * 
     * @param numberOfMaps to be set 
     */
    public void setNumberOfMaps(int numberOfMaps) {
        this.numberOfMaps = numberOfMaps;
    }
    
}
