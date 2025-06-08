package core;

import entities.Seed;
import entities.normal_seeds.*;
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

    public static BufferedImage gradientImage(BufferedImage image, Color color1, Color color2) {

        BufferedImage gradientImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = gradientImage.createGraphics();

        g2d.drawImage(image, 0, 0, null);

        GradientPaint gp = new GradientPaint(
                0, 0, color1,
                image.getWidth(), image.getHeight(), color2);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

        g2d.dispose();
        return gradientImage;
    }

    public static Seed giveRandomSeed(int x, int y){

        int idx = rand.nextInt(0, Configs.SEED_NAMES.length);
        String chosenSeed = Configs.SEED_NAMES[idx];

        switch(chosenSeed){
            case "Apple" -> {
                return new Apple(x, y);
            }
            case "Bamboo" -> {
                return new Bamboo(x, y);
            }
            case "Beanstalk" -> {
                return new Beanstalk(x, y);
            }
            case "Blueberry" -> {
                return new Blueberry(x,y);
            }
            case "Cacao" -> {
                return new Cacao(x, y);
            }
            case "Cactus" -> {
                return new Cactus(x, y);
            }
            case "Coconut" -> {
                return new Coconut(x, y);
            }
            case "Corn" -> {
                return new Corn(x, y);
            }
            case "Daffodil" -> {
                return new Daffodil(x, y);
            }
            case "DragonFruit" -> {
                return new DragonFruit(x, y);
            }
            case "EmberLily" -> {
                return new EmberLily(x, y);
            }
            case "Grape" -> {
                return new Grape(x, y);
            }
            case "Mango" -> {
                return new Mango(x, y);
            }
            case "Mushroom" -> {
                return new Mushroom(x, y);
            }
            case "OrangeTulip" -> {
                return new OrangeTulip(x, y);
            }
            case "Pepper" -> {
                return new Pepper(x, y);
            }
            case "Pumpkin" -> {
                return new Pumpkin(x, y);
            }
            case "Strawberry" -> {
                return new Strawberry(x, y);
            }
            case "Tomato" -> {
                return new Tomato(x, y);
            }
            case "Watermelon" -> {
                return new Watermelon(x, y);
            }
            default -> {
                return new Carrot(x, y);
            }
        }
    }

}