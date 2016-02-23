package src.network;

/**
 *
 * @author Richard
 * 
 * The ID of the packets that can be received.
 */

/*
 * Add new packets ID here.
 */
public enum RecvPacketOpcodes {
    PACKET1(0x10),
    PACKET2(0x91);
    
    private final int value;
    
    private RecvPacketOpcodes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}