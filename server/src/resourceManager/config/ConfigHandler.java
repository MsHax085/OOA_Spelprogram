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
    private static ConfigHandler instance = null;
    private int portNumber;
    private int maxClientsLobby;
    private int numberOfMaps;
    
    public ConfigHandler() {
    	configPath = "config.txt";
        fh = FileHandler.getInstance();
        
        readConfig();
    }
    public static ConfigHandler getInstance() {
        if (instance == null) instance = new ConfigHandler();
        return instance;
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

        return null; //Marks that readConfig could not find the setting.
    }

    /* Overwrites a value related to a specific setting. 
     * 
    */
    @Override
    public Boolean writeConfig() {
        final StringBuilder tempArr = new StringBuilder(); 
        tempArr.append("portNumber,").append(portNumber).append("\n");
        tempArr.append("maxClientsLobby,").append(maxClientsLobby).append("\n");
        tempArr.append("numberOfMaps,").append(numberOfMaps).append("\n");
        return fh.writeFile(configPath, tempArr.toString());
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
