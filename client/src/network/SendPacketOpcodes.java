package src.network;

/**
 *
 * @author Richard
 * 
 * The ID of the packets that can be sent.
 * 
 * Add new packets ID here.
 */
public enum SendPacketOpcodes {
    TESTPACKET(0x91),
    PACKET00(0x00),
    PACKET01(0x01),
    PACKET02(0x02),
	PACKET03(0x03),
	PACKET04(0x04),
	PACKET05(0x05),
	PACKET06(0x06),
	PACKET07(0x07);
    
    private final int value;
    
    private SendPacketOpcodes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
