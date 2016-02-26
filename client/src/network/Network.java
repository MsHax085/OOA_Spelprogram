package src.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Richard, BögErik
 *
 */

public class Network implements Runnable{

    private boolean running;
    
    public Network() {
    	PacketProcessor.getInstance().loadRecvHandlers();
    }
    
    
    /**
     * Starts the Network thread.
     */
    public synchronized void start() {
        System.out.println("Network Thread started");
        running = true;
        new Thread(this).start();
    }

    /**
    * Stops the tread after the run loop ends.
    * Closes the socket to force an interrupt in the .receive(). This might be fucking insane.
    */
    public synchronized void stop() {
        running = false;
        Connection.getInstance().closeSocket();
    }

    /**
    * Receives a packet, checks the ID of the packet and sends the packet information to the corresponding packet class.
    */
    @Override
    public void run() {
        while (running) {
            try {
                // TODO: Check for array out of bounds
                final DataInputStream dis = Connection.getInstance().receivePacket();
                final short opCode = dis.readShort();
                final ImplPacketHandler handler = PacketProcessor.getInstance().getHandler(opCode);
                if (handler != null) {
                    handler.setDataInputStream(dis);
                    NetworkBuffer.getInstance().addHandler(handler);
                }
            } catch (IOException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
