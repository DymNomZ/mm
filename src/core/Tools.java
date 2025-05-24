package core;

import java.util.Random;

public class Tools {

    static Random rand = new Random();
    static int maxX = MapConstructor.mapWidth;
    static int maxY = MapConstructor.mapHeight;

    public static int getRandomX(){
        return rand.nextInt(0, maxX);
    }

    public static int getRandomY(){
        return rand.nextInt(0, maxY);
    }
}
