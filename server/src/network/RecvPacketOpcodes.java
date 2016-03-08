package src.network;

/**
 *
 * The ID of the packets that can be received.
 *
 * @author Richard
 * @version 2016-02-28
 */

public enum RecvPacketOpcodes {
    TESTPACKET(0x91),
    PACKET00(0x00),
    PACKET01(0x01),
    PACKET02(0x02),
    PACKET03(0x03),
    PACKET04(0x04),
    PACKET05(0x05),
    PACKET06(0x06),
    PACKET07(0x07),
    PACKET08(0x08),
    PACKET09(0x09),
    PACKET0A(0x0A);
    
    private final int value;
    
    private RecvPacketOpcodes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
