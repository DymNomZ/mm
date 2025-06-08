package core;

import entities.Player;
import entities.Seed;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable{

    private Thread gameThread;

    public static MapConstructor mapConstructor;

    public static KeyHandler keyHandler;
    public static MouseHandler mouseHandler;

    //entities
    public static Player player;

    public Game(){
        this.setPreferredSize(new Dimension(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();

        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);

        mapConstructor = new MapConstructor("res/maps/debug_gag_2.zip");

        player = new Player();

        this.setFocusable(true);

        gameThread = new Thread(this);
    }

    public void start(){
        gameThread.start();
    }

    private void update(){

        player.move();
        player.handleInteractions();
        player.dropItem();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        mapConstructor.renderMap(g2);

        player.render(g2);
    }

    private void checkCollisions(){

        //logic
    }

    @Override
    public void run(){
        double drawInterval = (double) 1000000000 /Configs.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta >= 1){
                update();
                checkCollisions();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                //display FPS here for future implementations noice
                drawCount = 0;
                timer = 0;
            }
        }
    }
}
