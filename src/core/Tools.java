package core;

import world.Door;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tools {

    static Random rand = new Random();
    static int maxX = MapConstructor.mapWidth;
    static int maxY = MapConstructor.mapHeight;

    //world tiles
    public static Door openDoor = new Door();

    public static int getRandomX(){
        return rand.nextInt(0, maxX);
    }

    public static int getRandomY(){
        return rand.nextInt(0, maxY);
    }

    public static BufferedImage tintImage(BufferedImage image, Color tintColor) {

        BufferedImage tintedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tintedImage.createGraphics();

        g2d.drawImage(image, 0, 0, null);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));

        g2d.setColor(tintColor);

        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

        g2d.dispose();

        return tintedImage;
    }

}