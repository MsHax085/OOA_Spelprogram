package src.network;

/**
 *
 * @author Richard
 * 
 * The ID of the packets that can be sent.
 */

/*
 * Add new packets ID here.
 */
public enum SendPacketOpcodes {
    PACKET1(0x10),
    PACKET2(0x91);
    
    private final int value;
    
    private SendPacketOpcodes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
