package core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteLoader {

    public static int IDLE_LIMIT = 3;
    public static int WALKING_LIMIT = 4;

    public static BufferedImage[] PLAYER_UP_IDLE_FRAMES = new BufferedImage[IDLE_LIMIT];
    public static BufferedImage[] PLAYER_LEFT_IDLE_FRAMES = new BufferedImage[IDLE_LIMIT];
    public static BufferedImage[] PLAYER_DOWN_IDLE_FRAMES = new BufferedImage[IDLE_LIMIT];
    public static BufferedImage[] PLAYER_RIGHT_IDLE_FRAMES = new BufferedImage[IDLE_LIMIT];
    public static BufferedImage[] PLAYER_UP_WALKING_FRAMES = new BufferedImage[WALKING_LIMIT];
    public static BufferedImage[] PLAYER_LEFT_WALKING_FRAMES = new BufferedImage[WALKING_LIMIT];
    public static BufferedImage[] PLAYER_DOWN_WALKING_FRAMES = new BufferedImage[WALKING_LIMIT];
    public static BufferedImage[] PLAYER_RIGHT_WALKING_FRAMES = new BufferedImage[WALKING_LIMIT];

    private static String PUI_PATH = "/textures/player/bunnyBackIdle_";
    private static String PLI_PATH = "/textures/player/bunnyLeftIdle_";
    private static String PDI_PATH = "/textures/player/bunnyFrontIdle_";
    private static String PRI_PATH = "/textures/player/bunnyRightIdle_";
    private static String PUW_PATH = "/textures/player/bunnyBackWalk_";
    private static String PLW_PATH = "/textures/player/bunnyLeftWalk_";
    private static String PDW_PATH = "/textures/player/bunnyFrontWalk_";
    private static String PRW_PATH = "/textures/player/bunnyRightWalk_";

    static{
        //fill idles
        for(int i = 0; i < IDLE_LIMIT; i++){
            try {

                PLAYER_UP_IDLE_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PUI_PATH + i + ".png"));
                PLAYER_LEFT_IDLE_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PLI_PATH + i + ".png"));
                PLAYER_DOWN_IDLE_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PDI_PATH + i + ".png"));
                PLAYER_RIGHT_IDLE_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PRI_PATH + i + ".png"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //fill walking
        for(int i = 0; i < WALKING_LIMIT; i++){
            try {

                PLAYER_UP_WALKING_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PUW_PATH + i + ".png"));
                PLAYER_LEFT_WALKING_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PLW_PATH + i + ".png"));
                PLAYER_DOWN_WALKING_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PDW_PATH + i + ".png"));
                PLAYER_RIGHT_WALKING_FRAMES[i] = ImageIO.read(SpriteLoader.class.getResourceAsStream(PRW_PATH + i + ".png"));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static BufferedImage DEBUG_PLAYER;

    static {
        try {
            DEBUG_PLAYER = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/player/debug_player.png"));
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

    public static BufferedImage STALL_SHOP;
    public static BufferedImage STALL_SELL;

    static{
        try{
            STALL_SHOP = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/stalls/shop-1.png"));
            STALL_SELL = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/stalls/sell-1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BufferedImage DEBUG_DOOR;

    static{
        try{
            DEBUG_DOOR = ImageIO.read(SpriteLoader.class.getResourceAsStream("/textures/tiles/debug_door_open.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
