package src;

/**
 * Delivers codes describing changes in the program.
 * @author Richard
 * @version 2016-03-02
 */
public enum Changes {
    PACKET_RECEIVED(0),
    USERNAME_CHANGE(1),
    LOBBYLIST_CHANGE(2),
    CLIENTLIST_CHANGE(3),
    HIGHSCORE_UPDATE(4);
    
    private final int value;
    
    private Changes(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
