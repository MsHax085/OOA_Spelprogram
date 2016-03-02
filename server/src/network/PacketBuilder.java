package src.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import src.client.ClientLoggedIn;
import src.client.Lobby;
import src.resourceManager.Score;
import src.resourceManager.config.ConfigHandler;

 /**
 * collection of the packet building methods. 
 * 
 * @author Erik Thorsson Högfeldt
 *
 */

public class PacketBuilder {
    
    private static PacketBuilder packetBuilder;
    
    public static synchronized PacketBuilder getInstance() {
	if (packetBuilder == null) packetBuilder = new PacketBuilder();
	return packetBuilder;
    }
    
    /**
     * A packet made for testing. if send to client some debugging stuff is written in the console.
     */
    public byte[] createTestPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.TESTPACKET.getValue());
        dataOutputStream.writeInt(1337);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with basic information about all lobbies.
     * @return
     * @throws IOException
     */
    public byte[] create00LobbyListResponsePacket(int numberOfLobbies, Iterator lobbies) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET00.getValue());
        dataOutputStream.writeInt(ConfigHandler.getInstance().getMaxClientsLobby());
        dataOutputStream.writeInt(numberOfLobbies);
        while (lobbies.hasNext()) {
            Lobby lobby = (Lobby) lobbies.next();
            dataOutputStream.writeUTF(lobby.getLobbyName());
            dataOutputStream.writeBoolean(!lobby.getLobbyPassword().equals(""));
            dataOutputStream.writeInt(lobby.getLobbyCurrentMap());
            dataOutputStream.writeInt(lobby.getNumberOfClients());
        }
        
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with the status of the lobby; joined:0, incorrect password:1, lobby is full:2.
     * @param	joinLobbyStatus	as an int
     * @return
     * @throws IOException
     */
    public byte[] create01JoinLobbyResponcePacket(int joinLobbyStatus) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET01.getValue());
        dataOutputStream.writeByte(joinLobbyStatus);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with basic information(name, ready to start) about all clients in the current lobby.
     * @return
     * @throws IOException
     */
    public byte[] create02UpdateClientLobbyPacket(int numberOfClientsInLobby, Iterator listOfClientsInLobby) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET02.getValue());
        
        dataOutputStream.writeInt(numberOfClientsInLobby);
        while (listOfClientsInLobby.hasNext()) {
            ClientLoggedIn cli = (ClientLoggedIn) listOfClientsInLobby.next();
            dataOutputStream.writeInt(cli.getId());
            dataOutputStream.writeUTF(cli.getUsername());
            dataOutputStream.writeBoolean(cli.isReadyToStart());
        }
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with the status of the lobby creation; created:0, lobby with same name:1.
     * @param	createLobbyStatus	as an int.
     * @return
     * @throws IOException
     */
    public byte[] create03CreateLobbyStatusPacket(int createLobbyStatus) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET03.getValue());
        dataOutputStream.writeByte(createLobbyStatus);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with the map number for the game.
     * @return
     * @throws IOException
     */
    public byte[] create04StartGamePacket(int mapId) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET04.getValue());
        dataOutputStream.writeInt(mapId);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with the high score list of a specific map. The list only contains the first 20 players.
     * @return
     * @throws IOException
     */
    public byte[] create05HighscoreResponcePacket(int mapId) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET05.getValue());
        
        TreeMap<Integer, String> scoreList = Score.getInstance().getScore(mapId);
        int indexOfPlayer = 0;
        int sizeOfSendingList = 20;
        int sizeOfScoreList = scoreList.size();
        if (sizeOfScoreList < sizeOfSendingList) sizeOfSendingList = sizeOfScoreList;
        dataOutputStream.writeInt(sizeOfSendingList);
        for (Map.Entry<Integer, String> entry : scoreList.entrySet()) {
            if (indexOfPlayer >= sizeOfSendingList) break;
            dataOutputStream.writeInt(entry.getKey());
            dataOutputStream.writeUTF(entry.getValue());
            indexOfPlayer++;
        }
        
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with positions of client player/movable-block.
     * @param boxPositionY 
     * @param boxPositionX 
     * @param playerPositionY 
     * @param playerPositionX 
     * @return
     * @throws IOException
     */
    public byte[] create06MoveGameEntetiesPacket(int clientId, int playerPositionX, int playerPositionY, int boxPositionX, int boxPositionY) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET06.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.writeByte(playerPositionX);
        dataOutputStream.writeByte(playerPositionY);
        dataOutputStream.writeByte(boxPositionX);
        dataOutputStream.writeByte(boxPositionY);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data containing 
     * @param	clientId	the id of the client who completed the map as an int.
     * 		TimeOfCompletion	as an int.
     * @return
     * @throws IOException
     */
    public byte[] create07PlayerWonPacket(int clientId, int timeOfCompletion) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET07.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.writeInt(timeOfCompletion);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data to inform other clients that a client has restarted the map.
     * @param	clientId	the id of the client who restarted the map as an int.
     * @return
     * @throws IOException
     */
    public byte[] create08ClientMapResetPacket(int clientId) throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET08.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with the clients ID; -1 if the login failed
     * @param   clientLoginStatus   as an int.
     * @return
     * @throws IOException
     */
    public byte[] create09ClientLoginResponsePacket(int clientId) throws IOException {
    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET09.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * creates a packet with status about the client logout.
     * @param clientLogoutStatus    0:successful, 1:client is not loggedIn, 2:other problem.
     * @return
     * @throws IOException
     */
    public byte[] create0AClientLogoutResponsePacket(int clientLogoutStatus) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(SendPacketOpcodes.PACKET0A.getValue());
            dataOutputStream.writeByte(clientLogoutStatus);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
    /**
     * creates a packet with status of leaving a lobby.
     * @param clientLeaveLobbyStatus    0:successful, 1:lobby didn't exist. 2:other problem
     * @return
     * @throws IOException
     */
    public byte[] create0BLeaveLobbyResponsePacket(int clientLeaveLobbyStatus) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeShort(SendPacketOpcodes.PACKET0B.getValue());
            dataOutputStream.writeByte(clientLeaveLobbyStatus);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        }
}
