package world;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    public BufferedImage image;
    public String name;
    public int index = 0;
    public boolean isSolid = false;
    public boolean isAnimated = false;

    public Tile(String path, String name, int index, boolean isSolid, boolean isAnimated) throws IOException {

        this.image = ImageIO.read(getClass().getResourceAsStream(path));
        this.name = name;
        this.index = index;
        this.isSolid = isSolid;
        this.isAnimated = isAnimated;
    }

    //for loading
    public Tile(BufferedImage image, String name, int index, boolean isSolid, boolean isAnimated){
        this.image = image;
        this.name = name;
        this.index = index;
        this.isSolid = isSolid;
        this.isAnimated = isAnimated;
    }

    public String getName(){
        return name;
    }

    public BufferedImage getSprite() {
        return image;
    }

    @Override
    public String toString(){
        return name;
    }

}
