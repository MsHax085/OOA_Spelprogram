package src.network;

/**
 *
 * The ID of the packets that can be received.
 *
 * @author Richard
 * 
 */

/*
 * Add new packets ID here.
 */
public enum RecvPacketOpcodes {
    PACKET00(0x00),
    TESTPACKET(0x91),
    PACKET08(0x08);
    
    private final int value;
    
    private RecvPacketOpcodes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
