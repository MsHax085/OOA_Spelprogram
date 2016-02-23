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

    private DatagramSocket clientSocket = null;
    private final int serverPort = 8989;
    private InetAddress serverIpAddress;
    
    private final int bufferSize = 1024;
    
    /*
     * Constructor: starts the socket.
     */
    public Connection() {
        try {
            this.clientSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getInstance() {
        if (connection == null) connection = new Connection();
        return connection;
    }
    
    /*
     * Waits till a packet is received on the serverSocket. Saves the IP and port of the packet in local variables.
     * @return: The packet data as an ByteArrayInputStream.
     */
    public DataInputStream receivePacket() throws IOException {
        final byte[] bytes = new byte[bufferSize];
        final DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        clientSocket.receive(packet);
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new DataInputStream(byteArrayInputStream);
    }
    
    /*
     * Sends a packet to a specific node.
     * @param: The message as an byte[] (ByteArrayStream), the destination IP, the destination port number.
     */
    public void sendPacket(byte[] packetData) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, serverIpAddress, serverPort);
        clientSocket.send(packet);
    }
    
    public void closeSocket() {
    	clientSocket.close();
    }
}
