package core;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Configs {

    private static final int BASE_TILE_SIZE = 16;
    private static final int SCALE = 4;

    //fps cap
    public static final int FPS = 60;

    //game
    public static final int TILE_SIZE = BASE_TILE_SIZE * SCALE;
    public static final int SCREEN_COLUMNS = 16;
    public static final int SCREEN_ROWS = 12;
    public static final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COLUMNS;
    public static final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROWS;
    public static final int CENTER_X = (SCREEN_WIDTH / 2) - (TILE_SIZE / 2);
    public static final int CENTER_Y = (SCREEN_HEIGHT / 2) - (TILE_SIZE / 2);
    public static final int HALF = TILE_SIZE / 2;
    public static final int QUARTER = TILE_SIZE / 4;
    public static final int DOUBLE = TILE_SIZE * 2;
    public static final int THIRD = TILE_SIZE + HALF;

    public static String[] MUTATION_NAMES = {
            "moist", "cold", "brown", "lunar", "toxic",
            "fertilized", "bloodlunar", "laser", "mapleglazed", "charcoal", "godly",
            "ice", "candy", "shiny", "infected",
            "colorful", "lucky", "explosive",
            "energized", "galatic", "party",
            "black", "white", "magical"
    };

    public static int MULTIPLIERS[] = {
            2, 2, 2, 2, 2,
            3, 4, 5, 5, 5, 5,
            10, 15, 20, 25,
            50, 69, 70,
            100, 120, 125,
            135, 150, 200
    };

    public static Map<String, Integer> MULTIPLIERS_MAP = new HashMap<>();

    public static final Color COLD = new Color(160, 243, 255, 174);
    public static final Color BROWN = new Color(49, 28, 0, 255);
    public static final Color LUNAR = new Color(22, 26, 87, 255);
    public static final Color FERTILIZED = new Color(255, 199, 53, 255);
    public static final Color BLOODLUNAR = new Color(164, 0, 0, 255);
    public static final Color LASER = new Color(255, 117, 242, 255);
    public static final Color CHARCOAL = new Color(40, 40, 40, 255);
    public static final Color SHINY = new Color(208, 160, 0, 255);
    public static final Color BLACK = new Color(0, 0, 0, 255);
    public static final Color WHITE = new Color(255, 255, 255, 255);

    private static Color[] COLORS = {
            COLD, BROWN, LUNAR, FERTILIZED, BLOODLUNAR, LASER,
            CHARCOAL, SHINY, BLACK, WHITE
    };

    private static String[] COLOR_NAMES = {
            "cold", "brown", "lunar", "fertilized", "bloodlunar", "laser",
            "charcoal", "shiny", "black", "white"
    };

    public static Map<String, Color> COLORS_MAP = new HashMap<>();

    public static void init(){
        for(int i = 0; i < MUTATION_NAMES.length; i++){
            MULTIPLIERS_MAP.put(MUTATION_NAMES[i], MULTIPLIERS[i]);
        }

        for(int i = 0; i < COLOR_NAMES.length; i++){
            COLORS_MAP.put(COLOR_NAMES[i], COLORS[i]);
        }

    }

    //GRADIENT
    //CANDY, RAINBOW, GALACTIC, MAGICAL

    //NEON
    //ENERGIZED
    //PARTY - ALTERNATING

    //DRIPPING
    //MOIST - WATER
    //INFECTED - GREEN LIQUID
    //MAPLEGLAZED - MAPLE SYRUP

    //SPARKLES
    //LUNAR, BLOODLUNAR, LASER, SHINY, GALACTIC, WHITE, MAGIC

    //FROST
    //COLD

    //SHAMROCK
    //LUCKY

    //COMIC EXPLOSIVE
    //EXPLOSIVE

    //LIGHT RAY
    //GODLY

    //FUMES
    //FERTILIZED, TOXIC, BLACK

    //ICE BLOCK - ICE

}
