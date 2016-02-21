package src.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 
 * @author Richard
 *
 */
public class PacketBuilder {

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
