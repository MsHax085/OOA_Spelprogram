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
 * 
 * @author Richard, Erik Thorsson Högfeldt
 *
 */
public class Connection {
    
    private static Connection connection;

    private final int bufferSize = 1024;
    
    private DatagramSocket serverSocket = null;
    private int serverPort;
    
    /**
     * Constructor: starts the socket.
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
     * Saves the IP and port of the packet in local variables.
     * @return: The packet data as an ByteArrayInputStream.
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
     * This is called when a packet should be sent. Sends a packet to a specific node.
     * @param: The message as an byte[] (ByteArrayStream), the destination IP, the destination port number.
     */
    public void sendPacket(byte[] packetData, ClientSession cs) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, cs.getAddress(), cs.getPort());
        serverSocket.send(packet);
    }
    
    public void closeSocket() {
    	serverSocket.close();
    }
}
