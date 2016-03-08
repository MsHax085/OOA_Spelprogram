package src.frame;

/**
 * Interface that defines the structure classes using the pattern "state".
 * @author Richard
 * @vesion 2016-02-20
 */
public interface DefaultFrameState {
    
    public void setup();
    public void view();
    public void hide();
    public void dispose();
}
