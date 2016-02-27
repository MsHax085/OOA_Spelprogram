package src.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * Collection of the packets building methods.
 * IMPLEMENTATION: packetDataAsByteArray = PacketBuilder.getInstance().create'packet name'(arguments);
 * @author BögErik
 *
 */

 /* 
 * List of packets that can be sent:
 * 		create00RequestLobbyListPacket()
 * 		create01JoinLobbyPacket(String username, String password, int lobbyId)
 * 		create02ReadyRequest(int clientId)
 * 		create03CreateLobbyPacket(String username, String lobbyName, String password)
 * 		create04RequestHighscorePacket()	//Not completed
 * 		create05MoveGameEntetiesPacket()	//Not completed
 * 		create06PlayerWonPacket(int clientId, int time)
 * 		create07PlayerResetPacket(int clientId)
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
    public byte[] create01JoinLobbyPacket(int clientId, String password, int lobbyId) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET01.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.writeUTF(password);
        dataOutputStream.writeInt(lobbyId);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Tells the server that this client is ready to start a game.
     * @Param	clientId	The ID of this client.
     * @Return			The packet message as an byte array.
     */
    public byte[] create02ReadyRequest(int clientId) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET02.getValue());
        dataOutputStream.writeInt(clientId);
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
    public byte[] create03CreateLobbyPacket(int clientId, String lobbyName, String password) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET03.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.writeUTF(lobbyName);
        dataOutputStream.writeUTF(password);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /** TODO: Split the high score list so it doesn't exceed the packet buffer size. maybe 10 users per packet.
     * 
     * Asks for parts of the High score list.
     * @Param 
     * @Return	The packet message as an byte array.
     */
    public byte[] create04RequestHighscorePacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET04.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * TODO: Yell at Ludwig until he gives me a good explanation about how he want this to work.
     */
    public byte[] create05MoveGameEntetiesPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET05.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Tells the server that this client has completed the map and the time of completion.
     * @Param	clientId	This clients ID.
     * 		time		This clients time of completion
     * @Return			The packet message as an byte array.
     */
    public byte[] create06PlayerWonPacket(int clientId, int time) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET06.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.writeInt(time);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Asks for all lobbies on the server.
     * @Param	clientId	This clients ID.
     * @Return			The packet message as an byte array.
     */
    public byte[] create07PlayerResetPacket(int clientId) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET07.getValue());
        dataOutputStream.writeInt(clientId);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Logs in to the server with this client username
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
}
