package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {

    public static BufferedImage PLAYER;

    static {
        try {
            PLAYER = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/debug_player.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
