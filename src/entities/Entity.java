package entities;

import core.Configs;

import java.awt.image.BufferedImage;

public class Entity {

    public double x, y;
    public int sx, sy;
    public int speed;
    public int ewidth, eheight;
    public BufferedImage sprite;

    public int ts = Configs.TILE_SIZE;
    public int h = Configs.HALF;
    public int q = Configs.QUARTER;
    public int trd = Configs.THIRD;
    public int et = Configs.EIGHTH;

}
