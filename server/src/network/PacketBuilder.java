package src.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import src.lobbyManager.ManagerItem;

 /**
 * collection of the packet building methods. 
 * 
 * IMPLEMENTATION: packetDataAsByteArray = PacketBuilder.getInstance().create'packet name'(arguments);
 * @author Richard, BögErik
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
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET2.getValue());
        dataOutputStream.writeInt(1337);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * NOT COMPLETED
     * Creates packet data with basic information about all lobbies.
     * @return
     * @throws IOException
     */
    public byte[] create00LobbyListResponcePacket() throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET00.getValue());
        
        // TODO: Fix the lobby manager so it can be used here.
        
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with the status of the lobby; joined:0, incorrect password:1, lobby is full:2, client name in use:3.
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
     * NOT COMPLETED
     * Creates packet data with basic information(name, ready to start) about all clients in the current lobby.
     * @return
     * @throws IOException
     */
    public byte[] create02UpdateClientLobbyPacket() throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET02.getValue());
        
        // TODO: Fix the lobby manager so it can be used here.
        
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Creates packet data with the status of the lobby creation; created:0, lobby with same name:1, client name in use:2.
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
     * NOT COMPLETED
     * Creates packet data with the high score list.
     * @return
     * @throws IOException
     */
    public byte[] create05HighscoreResponcePacket() throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET05.getValue());
        
        // TODO: Figure out how score should be sent to clients
        
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * NOT COMPLETED
     * Creates packet data with a clients entities position. 
     * TOTO: Yell at ludwig.
     * @return
     * @throws IOException
     */
    public byte[] create06MoveGameEntetiesPacket() throws IOException {
	final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET06.getValue());
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
}
