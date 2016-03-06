package src.resourceManager;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import src.Core;

/**
 * This class loads images and makes them available for other classes.
 * 
 * @author Richard
 * @version 
 *
 */
public class Resources {
    
    private static Resources instance = null;
    private ArrayList<Image> images = new ArrayList<>();

    public static Resources getInstance() {
        if (instance == null) instance = new Resources();
        return instance;
    }
    
    public void loadImages() {
        try {
            images.add(ImageIO.read(new File(Core.class.getResource("resources/grass.png").getPath())));
            images.add(ImageIO.read(new File(Core.class.getResource("resources/stones.png").getPath())));
            images.add(ImageIO.read(new File(Core.class.getResource("resources/slab.png").getPath())));
            images.add(ImageIO.read(new File(Core.class.getResource("resources/tnt.png").getPath())));
            images.add(ImageIO.read(new File(Core.class.getResource("resources/diamond.png").getPath())));
            images.add(ImageIO.read(new File(Core.class.getResource("resources/sand.png").getPath())));
            images.add(ImageIO.read(new File(Core.class.getResource("resources/box.png").getPath())));
        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns an image of a specific materialId
     * 
     * @param materialId - The materialId of the image that is going to be returned
     * @return image of the materialId
     */
    public Image getImage(int materialId) {
        return images.get(materialId);
    }
}
