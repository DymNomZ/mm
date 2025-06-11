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

    private static String[] DIRT_TILES = {
            "dirt-00.png", "dirt-01.png", "dirt-02.png",
            "dirt-03.png", "dirt-04.png", "dirt-05.png",
            "dirt-06.png", "dirt-07.png", "dirt-08.png",
            "dirt-09.png", "dirt-10.png", "dirt-11.png",
    };

    public static boolean isDirt(Tile tile){

        if(tile == null) return false;

        for(String dirt : DIRT_TILES){
            if(tile.name.equals(dirt)) return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return name;
    }

}
