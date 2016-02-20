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
 * @author Richard
 *
 */
public class Connection {
    
    private static Connection connection;

    private DatagramSocket receiveSocket = null;
    private DatagramSocket sendSocket = null;
    private final int receivePort = 8989;
    private final int sendPort = 8989;
    private final int bufferSize = 1024;
    
    public Connection() {
        try {
            this.receiveSocket = new DatagramSocket(receivePort);
            this.sendSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getInstance() {
        if (connection == null) connection = new Connection();
        return connection;
    }
    
    public DataInputStream receivePacket() throws IOException {
        final byte[] bytes = new byte[bufferSize];
        final DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        receiveSocket.receive(packet);
        
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return new DataInputStream(byteArrayInputStream);
    }
    
    public void sendPacket(byte[] packetData, InetAddress addr) throws IOException {
        DatagramPacket packet = new DatagramPacket(packetData, packetData.length, addr, sendPort);
        sendSocket.send(packet);
    }
}
