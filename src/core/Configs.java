package core;

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
}
