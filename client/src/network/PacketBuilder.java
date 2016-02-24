package src.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 
 * @author Richard, BögErik
 *
 */

/*
 * collection of the packet building methods. 
 * 
 * IMPLEMENTATION: packetDataAsByteArray = PacketBuilder.getInstance().create'packet name'(arguments);
 */
public class PacketBuilder {
	
	private static PacketBuilder packetBuilder;
	
	public static PacketBuilder getInstance() {
        if (packetBuilder == null) packetBuilder = new PacketBuilder();
        return packetBuilder;
    }

    public byte[] createHelloPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET1.getValue());
        dataOutputStream.writeByte(1);// Ok byte
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    public byte[] createTestPacket() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeShort(SendPacketOpcodes.PACKET2.getValue());
        dataOutputStream.writeInt(1337);
        dataOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
