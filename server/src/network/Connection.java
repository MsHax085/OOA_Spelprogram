package src.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Richard, BögErik
 *
 */
public class Connection {
    
    private static Connection connection;
    private static  int DEFAULTPORT = 8989;

    private final int bufferSize = 1024;
    
    private DatagramSocket serverSocket = null;
    private int serverPort;
    private InetAddress fromIpAddress;
    int fromPort;
    
    /*
     * Constructor: starts the socket.
     */
    private Connection() {
    	this.serverPort = DEFAULTPORT;
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
    
    /*
     * This is used by the Network Thread. Waits till a packet is received on the serverSocket.
     * Saves the IP and port of the packet in local variables.
     * @return: The packet data as an ByteArrayInputStream.
     */
    public DataInputStream receivePacket() throws IOException {
        final byte[] bytes = new byte[bufferSize];
        final DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        serverSocket.receive(packet);
        fromIpAddress = packet.getAddress();
        fromPort = packet.getPort();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new DataInputStream(byteArrayInputStream);
    }
    
    /*
     * This is called when a packet should be sent. Sends a packet to a specific node.
     * @param: The message as an byte[] (ByteArrayStream), the destination IP, the destination port number.
     */
    public void sendPacket(byte[] packetData, InetAddress addr, int sendPort) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, addr, sendPort);
        serverSocket.send(packet);
    }
    
    public InetAddress getFromIpAddress() {
		return fromIpAddress;
    }
    
    public int getFromPort() {
		return fromPort;
    }
    
    public void closeSocket() {
    	serverSocket.close();
    }
}
