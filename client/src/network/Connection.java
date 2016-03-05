package src.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.resourceManager.config.ConfigHandler;

/**
 * Starts a socket, sends and retrieves packets to and from the server.
 * @author Richard, Erik Thorsson Högfeldt
 * @version 2016-03-05
 */
public class Connection {
    
    private static Connection connection;
    
    private final int bufferSize = 1024;

    private DatagramSocket clientSocket = null;
    private int serverPort;
    private InetAddress serverIpAddress;
    
    /**
     * Constructor: starts the socket. loads the address and port from the config.
     */
    private Connection() {
	try {
	    this.serverPort = ConfigHandler.getInstance().getServerPort();
	    this.serverIpAddress = InetAddress.getByName(ConfigHandler.getInstance().getServerIp());
	    this.clientSocket = new DatagramSocket();
	} catch (SocketException | UnknownHostException ex) {
	    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public static synchronized Connection getInstance() {
        if (connection == null) connection = new Connection();
        return connection;
    }
    
    /**
     * Waits till a packet is received on the socket and return its data.
     * @return: The packet data as an ByteArrayInputStream.
     */
    public DataInputStream receivePacket() throws IOException {
        final byte[] bytes = new byte[bufferSize];
        final DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        clientSocket.receive(packet);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new DataInputStream(byteArrayInputStream);
    }
    
    /**
     * Sends a packet to the server.
     * @param: The message as an byte[] (ByteArrayStream).
     */
    public void sendPacket(byte[] packetData) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, serverIpAddress, serverPort);
        clientSocket.send(packet);
    }
    
    /**
     * closes the socket and interrupts the receiving of packets.
     */
    public void closeSocket() {
    	clientSocket.close();
    }
}
