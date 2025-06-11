package core;

import java.awt.*;
import java.io.File;
import java.io.IOException;
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
    public static final int EIGHTH = QUARTER / 2;

    public static final int SEED_SIZE = QUARTER;
    public static final int SEED_OFFSET = EIGHTH;

    public static final int STALL_SIZE = TILE_SIZE * 4;
    public static final int STALL_OFFSET = STALL_SIZE;

    public static String[] MUTATION_NAMES = {
            "moist", "cold", "brown", "lunar", "toxic",
            "fertilized", "bloodlunar", "laser", "mapleglazed", "charred", "godly",
            "ice", "candy", "shiny", "infected",
            "colorful", "lucky", "explosive",
            "energized", "galactic", "party",
            "abyssal", "ashen", "magical"
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

    public static String[] SEED_NAMES = {
            "Apple", "Bamboo", "Beanstalk", "Blueberry", "Cacao",
            "Cactus", "Carrot", "Coconut", "Corn", "Daffodil",
            "DragonFruit", "EmberLily", "Grape", "Mango", "Mushroom",
            "OrangeTulip", "Pepper", "Pumpkin", "Strawberry", "Tomato",
            "Watermelon"
    };

    public static final Color SHOCKED_RED_1 = new Color(255, 0, 0, 255);
    public static final Color SHOCKED_RED_2 = new Color(255, 35, 0, 255);
    public static final Color SHOCKED_RED_3 = new Color(136, 1, 0, 255);
    public static final Color SHOCKED_ORANGE_1 = new Color(255, 111, 0, 255);
    public static final Color SHOCKED_ORANGE_2 = new Color(255, 90, 0, 255);
    public static final Color SHOCKED_YELLOW_1 = new Color(247, 255, 0, 255);
    public static final Color SHOCKED_YELLOW_2 = new Color(255, 174, 0, 255);
    public static final Color SHOCKED_GREEN_1 = new Color(6, 255, 0, 255);
    public static final Color SHOCKED_GREEN_2 = new Color(0, 213, 25, 255);
    public static final Color SHOCKED_CYAN_1 = new Color(0, 255, 123, 255);
    public static final Color SHOCKED_BLUE_1 = new Color(0, 112, 255, 255);
    public static final Color SHOCKED_INDIGO_1 = new Color(56, 75, 255, 255);
    public static final Color SHOCKED_PURPLE_1 = new Color(90, 0, 255, 255);
    public static final Color SHOCKED_PINK_1 = new Color(255, 0, 189, 255);
    public static final Color SHOCKED_PINK_2 = new Color(255, 141, 244, 255);
    public static final Color SHOCKED_BROWN_1 = new Color(169, 119, 74, 255);
    public static final Color SHOCKED_GRAY_1 = new Color(141, 141, 141, 255);
    public static final Color SHOCKED_BLACK_1 = new Color(0, 0, 0, 255);
    public static final Color SHOCKED_WHITE_1 = new Color(255, 255, 255, 255);

    public static final Color APPLE_1 = new Color(182, 26, 1, 255);
    public static final Color APPLE_2 = new Color(5, 223, 1, 255);
    public static final Color BAMBOO = new Color(24, 176, 22, 255);
    public static final Color BEANSTALK_1 = new Color(1, 144, 2, 255);
    public static final Color BEANSTALK_2 = new Color(3, 59, 13, 255);
    public static final Color BLUEBERRY = new Color(11, 35, 68, 255);
    public static final Color CACAO_1 = new Color(78, 40, 33, 255);
    public static final Color CACAO_2 = new Color(146, 100, 48, 255);
    public static final Color CACTUS = new Color(21, 117, 51, 255);
    public static final Color CARROT = new Color(198, 109, 40, 255);
    public static final Color COCONUT = new Color(221, 221, 219, 255);
    public static final Color CORN = new Color(211, 217, 18, 255);
    public static final Color DAFFODIL_1 = new Color(160, 187, 14, 255);
    public static final Color DAFFODIL_2 = new Color(57, 179, 0, 255);
    public static final Color DRAGONFRUIT_1 = new Color(205, 21, 157, 255);
    public static final Color DRAGONFRUIT_2 = new Color(48, 194, 40, 255);
    public static final Color EMBERLILY_1 = new Color(221, 86, 10, 255);
    public static final Color EMBERLILY_2 = new Color(34, 207, 117, 255);
    public static final Color GRAPE = new Color(85, 20, 196, 255);
    public static final Color MANGO = new Color(211, 148, 15, 255);
    public static final Color MUSHROOM_1 = new Color(183, 18, 10, 255);
    public static final Color MUSHROOM_2 = new Color(255, 226, 189, 255);
    public static final Color ORANGETULIP = new Color(187, 127, 60, 255);
    public static final Color PEPPER_1 = new Color(180, 23, 6, 255);
    public static final Color PEPPER_2 = new Color(72, 3, 2, 255);
    public static final Color PUMPKIN = new Color(217, 163, 23, 255);
    public static final Color STRAWBERRY = new Color(193, 24, 24, 255);
    public static final Color TOMATO = new Color(175, 21, 12, 255);
    public static final Color WATERMELON = new Color(76, 148, 63, 255);

    public static final Color MOIST = new Color(131, 210, 255, 255);
    public static final Color COLD_1 = new Color(160, 243, 255, 64);
    public static final Color COLD_2 = new Color(160, 243, 255, 255);
    public static final Color BROWN = new Color(87, 58, 30, 255);
    public static final Color LUNAR = new Color(68, 78, 169, 255);
    public static final Color TOXIC = new Color(23, 194, 18, 255);
    public static final Color FERTILIZED = new Color(255, 199, 53, 255);
    public static final Color BLOODLUNAR = new Color(164, 0, 0, 255);
    public static final Color LASER = new Color(255, 117, 242, 255);
    public static final Color MAPLEGLAZED = new Color(255, 154, 53, 255);
    public static final Color CHARRED = new Color(40, 40, 40, 255);
    public static final Color GODLY = new Color(248, 255, 179, 255);
    public static final Color ICE_1 = new Color(160, 243, 255, 174);
    public static final Color ICE_2 = new Color(160, 201, 255, 255);
    public static final Color SHINY = new Color(208, 160, 0, 255);
    public static final Color INFECTED = new Color(23, 80, 21, 255);
    public static final Color LUCKY = new Color(0, 122, 51, 255);
    public static final Color EXPLOSIVE = new Color(211, 63, 0, 255);
    public static final Color ENERGIZED = new Color(155, 255, 0, 255);
    public static final Color ABYSSAL = new Color(7, 0, 19, 255);
    public static final Color ASHEN = new Color(220, 220, 220, 255);

    public static final Color CANDY_1 = new Color(240, 0, 0, 255);
    public static final Color CANDY_2 = new Color(255, 255, 255, 255);
    public static final Color GALACTIC_1 = new Color(7, 0, 87, 255);
    public static final Color GALACTIC_2 = new Color(150, 31, 183, 255);
    public static final Color MAGICAL_1 = new Color(6, 118, 255, 255);
    public static final Color MAGICAL_2 = new Color(255, 255, 255, 255);

    public static final Color DIALOGUE_BOX = new Color(20, 20, 20, 191);
    public static final Color HOVER_COLOR = new Color(255, 203, 87, 255);

    public static Color[] TOOLTIP_COLORS = {
            MOIST, COLD_2, BROWN, LUNAR, TOXIC,
            FERTILIZED, BLOODLUNAR, LASER, MAPLEGLAZED, CHARRED, GODLY,
            ICE_2, CANDY_1, SHINY, INFECTED,
            SHOCKED_PINK_1, LUCKY, EXPLOSIVE,
            ENERGIZED, GALACTIC_1, SHOCKED_CYAN_1,
            ABYSSAL, ASHEN, MAGICAL_1
    };

    public static Color[] PARTY_COLORS = {
            SHOCKED_RED_1, SHOCKED_ORANGE_1, SHOCKED_YELLOW_1, SHOCKED_GREEN_1,
            Color.CYAN, Color.BLUE, SHOCKED_PURPLE_1, SHOCKED_PINK_1
    };

    private static String[] COLORED_MUTATIONS = {
            "cold", "brown", "lunar", "fertilized", "bloodlunar", "laser",
            "charred", "shiny", "abyssal", "ashen"
    };

    private static Color[] COLORS = {
            COLD_1, BROWN, LUNAR, FERTILIZED, BLOODLUNAR, LASER,
            CHARRED, SHINY, ABYSSAL, ASHEN
    };

    private static Color[] SHOCKED_COLORS = {
            SHOCKED_BLUE_1, SHOCKED_BROWN_1, SHOCKED_INDIGO_1, SHOCKED_YELLOW_2, SHOCKED_RED_3,
            SHOCKED_PINK_2,SHOCKED_GRAY_1, SHOCKED_YELLOW_1,SHOCKED_BLACK_1, SHOCKED_WHITE_1
    };

    public static String[] SPARKLE_MUTATIONS = {
            "lunar", "bloodlunar", "laser", "shiny", "galactic",
            "ashen", "magical"
    };

    public static String[] DRIPPING_MUTATIONS = {
            "moist", "infected", "mapleglazed"
    };

    public static String[] FUME_MUTATION = {
            "fertilized", "toxic", "abyssal"
    };

    public static Map<String, Color> COLORS_MAP = new HashMap<>();
    public static Map<String, Color> SHOCKED_COLORS_MAP = new HashMap<>();
    public static Map<String, Color> TOOLTIP_COLORS_MAP = new HashMap<>();

    public static Font COMIC_SANS = null;
    public static Font COMIC_SANS_HAIRLINE = null;
    public static Font COMIC_SANS_BOLD = null;
    public static Font COMIC_SANS_LIGHT = null;
    private static int FONT_SIZE = 20;

    private static void loadFonts(){

        try {

            COMIC_SANS = Font.createFont(Font.TRUETYPE_FONT, new File(
                            "res/fonts/Ldfcomicsans-jj7l.ttf"
                    )
            );

            COMIC_SANS_HAIRLINE = Font.createFont(Font.TRUETYPE_FONT, new File(
                            "res/fonts/Ldfcomicsanshairline-5PmL.ttf"
                    )
            );

            COMIC_SANS_BOLD = Font.createFont(Font.TRUETYPE_FONT, new File(
                            "res/fonts/Ldfcomicsansbold-zgma.ttf"
                    )
            );

            COMIC_SANS_LIGHT = Font.createFont(Font.TRUETYPE_FONT, new File(
                            "res/fonts/Ldfcomicsanslight-6dZo.ttf"
                    )
            );

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(COMIC_SANS);
            ge.registerFont(COMIC_SANS_HAIRLINE);
            ge.registerFont(COMIC_SANS_BOLD);
            ge.registerFont(COMIC_SANS_LIGHT);

            COMIC_SANS = COMIC_SANS.deriveFont(Font.PLAIN, FONT_SIZE);
            COMIC_SANS_HAIRLINE = COMIC_SANS_HAIRLINE.deriveFont(Font.PLAIN, FONT_SIZE);
            COMIC_SANS_BOLD = COMIC_SANS_BOLD.deriveFont(Font.PLAIN, FONT_SIZE);
            COMIC_SANS_LIGHT = COMIC_SANS_LIGHT.deriveFont(Font.PLAIN, FONT_SIZE);

        } catch (FontFormatException | IOException e) {
            System.out.println("Error loading font");
        }

    }

    public static void init(){
        for(int i = 0; i < MUTATION_NAMES.length; i++){
            MULTIPLIERS_MAP.put(MUTATION_NAMES[i], MULTIPLIERS[i]);
        }

        for(int i = 0; i < COLORED_MUTATIONS.length; i++){
            COLORS_MAP.put(COLORED_MUTATIONS[i], COLORS[i]);
        }

        for(int i = 0; i < SHOCKED_COLORS.length; i++){
            SHOCKED_COLORS_MAP.put(COLORED_MUTATIONS[i], SHOCKED_COLORS[i]);
        }

        for(int i = 0; i < TOOLTIP_COLORS.length; i++){
            TOOLTIP_COLORS_MAP.put(MUTATION_NAMES[i], TOOLTIP_COLORS[i]);
        }

        loadFonts();

    }

}
