package src.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * Collection of all packets building methods.
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-05
 */

public class PacketBuilder {
	
    private static PacketBuilder packetBuilder;

    public static synchronized PacketBuilder getInstance() {
        if (packetBuilder == null) packetBuilder = new PacketBuilder();
        return packetBuilder;
    }
    /**
     * Builds a packet made for testing purposes. If this is sent to the server a similar packet is returned.
     * Some debugging stuff is written in the console of both server and this client.
     * this is not used in the final code.
     */
    public byte[] createTestPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.TESTPACKET.getValue());
        dataOutputStream.writeInt(1234);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     *  Builds a packet for requesting the list of lobbies on the server.
     * @return The packet message as an byte array.
     */
    public byte[] create00RequestLobbyListPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET00.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Builds a packet for joining a lobby with the given lobby name and password.
     * @param lobbyName Lobby name as a string.
     * @param password Lobby password as a string.
     * @return The packet message as an byte array.
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
     * Builds a packet that tells the server that this client is ready to start a game.
     * @return The packet message as an byte array.
     */
    public byte[] create02ReadyRequest() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET02.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Builds a packet for creating a lobby with the given lobby name and password.
     * @param lobbyName Lobby name as a string.
     * @param password Lobby password as a string.
     * @return The packet message as an byte array.
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
     * Builds a packet for requesting the top 14 highscores of a given map.
     * @param mapId The Id of the map.
     * @return The packet message as an byte array.
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
     * Builds a packet with the position of the player and the movable block.
     * @param playerPositionX
     * @param playerPositionY
     * @param boxPositionX
     * @param boxPositionY
     * @return The packet message as an byte array.
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
     * Builds a packet containing the time of completion.
     * @param time In 10 milliseconds as an int.
     * @return The packet message as an byte array.
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
     * Builds a packet that tells the server that this client has restarted its map.
     * @return The packet message as an byte array.
     */
    public byte[] create07PlayerResetPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET07.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Builds a packet that tells the server that this client would like to login.
     * @param username This clients username as a string.
     * @return The packet message as an byte array.
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
     * Builds a packet that tells the server that this client would like to logout.
     * @return The packet message as an byte array.
     */
    public byte[] create09ClientLogoutPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET09.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    /**
     * Builds a packet that tells the server to remove this client from its  lobby.
     * @return The packet message as an byte array.
     */
    public byte[] create0ALeaveLobbyPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET0A.getValue());
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
