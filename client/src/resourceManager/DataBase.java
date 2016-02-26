package src.resourceManager;




/**
 * 
 * @author Oscar Andersson
 *
 * 
 */
public class DataBase{
	

    private static DataBase instance = null;

    /* Constructs a Score Object assigning its filepath. 
     */
    private DataBase() {

    }

    public static DataBase getInstance(){
        if (instance == null) instance = new DataBase();
        return instance;
    }

}
