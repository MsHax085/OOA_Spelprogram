package src.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * Collection of the packets building methods.
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
	 * A packet made for testing purposes. If this is sent to the server a similar packet is returned.
	 * Some debugging stuff is written in the console of both server and this client.
	 * WONT BE USED IN THE FINAL CODE.
	 */
    public byte[] createTestPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.TESTPACKET.getValue());
        dataOutputStream.writeInt(666);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Asks for all lobbies on the server.
     * @Param 
     * @Return	The packet message as an byte array.
     */
    public byte[] create00RequestLobbyListPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET00.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /** 
     * Tells the server that this client would like to enter a lobby.
     * @Param	clientId	ID of this client.
     * 		password	Password as an unencrypted string, just to make life easier for the NSA.
     * 		lobbyId		The ID of the chosen lobby.
     * @Return			The packet message as an byte array.
     */
    public byte[] create01JoinLobbyPacket(String lobbyName, String password) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET01.getValue());
        dataOutputStream.writeUTF(password);
        dataOutputStream.writeUTF(lobbyName);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Tells the server that this client is ready to start a game.
     * @Param	clientId	The ID of this client.
     * @Return			The packet message as an byte array.
     */
    public byte[] create02ReadyRequest() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET02.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Tells the server to create a new lobby with specific settings. The server joins this client to the lobby.
     * @Param	clientId	ID of this client.
     * 		lobbyName	The name of the lobby that should be created.
     * 		password	Password as an unencrypted string, just to make life easier for the NSA.
     * @Return			The packet message as an byte array.
     */
    public byte[] create03CreateLobbyPacket(String lobbyName, String password) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET03.getValue());
        dataOutputStream.writeUTF(lobbyName);
        dataOutputStream.writeUTF(password);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Asks for top 20 highscore of the specified map.
     * @Param mapId
     * @Return	The packet message as an byte array.
     */
    public byte[] create04RequestHighscorePacket(int mapId) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET04.getValue());
        dataOutputStream.writeInt(mapId);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates a packet with the position of the player and the movable block.
     * @param	
     */
    public byte[] create05MoveGameEntetiesPacket(int playerPositionX, int playerPositionY, int boxPositionX, int boxPositionY) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET05.getValue());
        dataOutputStream.writeByte(playerPositionX);
        dataOutputStream.writeByte(playerPositionY);
        dataOutputStream.writeByte(boxPositionX);
        dataOutputStream.writeByte(boxPositionY);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Tells the server that this client has completed the map and the time of completion.
     * @Param	clientId	This clients ID.
     * 		time		This clients time of completion
     * @Return			The packet message as an byte array.
     */
    public byte[] create06PlayerWonPacket(int time) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET06.getValue());
        dataOutputStream.writeInt(time);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Asks for all lobbies on the server.
     * @Param	clientId	This clients ID.
     * @Return			The packet message as an byte array.
     */
    public byte[] create07PlayerResetPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET07.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Logs in to the server with this client username. This is needed for teh server to
     * Hhandle most of the packets.
     * @Param   username    This clients username
     * @Return          The packet message as an byte array.
     */
    public byte[] create08ClientLoginPacket(String username) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET08.getValue());
        dataOutputStream.writeUTF(username);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * creates a packet that logs this client out of the server. If this client is in a lobby he is 
     * automatically removed from it.
     * @param username  This clients username.
     * @return
     * @throws IOException
     */
    public byte[] create09ClientLogoutPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET09.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * creates a packet that removes this client from the lobby it is in.
     * @param 
     * @return
     * @throws IOException
     */
    public byte[] create0ALeaveLobbyPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET0A.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
