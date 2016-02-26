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
 * 
 * @author Richard
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
    
    public Image getImage(int materialId) {
        return images.get(materialId);
    }
}
