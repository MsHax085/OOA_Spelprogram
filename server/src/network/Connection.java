package src.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.client.ClientManager;
import src.client.ClientSession;
import src.resourceManager.config.ConfigHandler;

/**
 * Starts a socket, sends and retrieves packets to and from clients.
 * @author Richard, Erik Thorsson Högfeldt
 * @version 2016-03-02
 *
 */
public class Connection {
    
    private static Connection connection;

    private final int bufferSize = 1024;
    
    private DatagramSocket serverSocket = null;
    private int serverPort;
    
    /**
     * Constructor: starts the socket. loads the port from config.txt.
     */
    private Connection() {
    	this.serverPort = ConfigHandler.getInstance().getPortNumber();
        try {
            this.serverSocket = new DatagramSocket(serverPort);
        } catch (SocketException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static synchronized Connection getInstance() {
        if (connection == null) connection = new Connection();
        return connection;
    }
    
    /**
     * This is used by the Network Thread. Waits till a packet is received on the serverSocket.
     * Saves the IP and port as a client session
     * @return: The packet as a Packet containing the client session and the packet data as a ByteArrayInputStream.
     */
    public Packet receivePacket() throws IOException {
        final byte[] bytes = new byte[bufferSize];
        final DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        serverSocket.receive(packet);
        final ClientSession cs = ClientManager.getInstance().getRegistredClientSession(packet.getAddress(), packet.getPort());
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        final DataInputStream stream = new DataInputStream(byteArrayInputStream);
        return new Packet(cs, stream);
    }
    
    /**
     * Sends a packet to a specific client.
     * @param packetData Bytearray, is built by the PacketBuilder.
     * @param cs A client session
     * @throws IOException
     */
    public void sendPacket(byte[] packetData, ClientSession cs) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, cs.getAddress(), cs.getPort());
        serverSocket.send(packet);
    }
    
    /**
     * closes the socket and interrupts the receiving of packets.
     */
    public void closeSocket() {
    	serverSocket.close();
    }
}
