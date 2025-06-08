package core;

import world.Door;

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

}