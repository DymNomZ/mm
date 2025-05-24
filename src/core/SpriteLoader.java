package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {

    public static BufferedImage PLAYER;

    static {
        try {
            PLAYER = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/player/debug_player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage DEBUG_ITEM;

    static {
        try{
            DEBUG_ITEM = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/items/debug_item.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
