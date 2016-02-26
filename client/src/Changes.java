package src;

/**
 *
 * @author Richard
 */
public enum Changes {
    PACKET_RECEIVED(0),
    USERNAME_CHANGE(1),
    LOBBYLIST_CHANGE(2);
    
    private final int value;
    
    private Changes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
