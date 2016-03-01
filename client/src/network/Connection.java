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
 * 
 * @author Richard, BögErik
 *
 */
public class Connection {
    
    private static Connection connection;
    private static String DEFAULTIP = "localhost";
    private static int DEFAULTPORT = 8989;
    
    private final int bufferSize = 1024;

    private DatagramSocket clientSocket = null;
    private int serverPort;
    private InetAddress serverIpAddress;
    
    /**
     * Constructor: starts the socket. loads the address.
     * 
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
     * This is used by the Network Thread. Waits till a packet is received on the serverSocket. 
     * Saves the IP and port of the packet in local variables.
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
     * This is called when a packet should be sent. Sends a packet to the server.
     * @param: The message as an byte[] (ByteArrayStream), the destination IP, the destination port number.
     */
    public void sendPacket(byte[] packetData) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, serverIpAddress, serverPort);
        clientSocket.send(packet);
    }
    
    public void closeSocket() {
    	clientSocket.close();
    }
    
    public void changeIpAdress(String serverIpAdress) {
    	try {
    	this.serverIpAddress = InetAddress.getByName(serverIpAdress);
    	}	catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
}
