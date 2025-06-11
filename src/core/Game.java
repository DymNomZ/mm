package core;

import entities.Player;
import entities.Seed;
import entities.Stall;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable{

    private Thread gameThread;

    public static MapConstructor mapConstructor;

    public static KeyHandler keyHandler;
    public static MouseHandler mouseHandler;

    public int ts = Configs.TILE_SIZE;
    public int h = Configs.HALF;
    public int q = Configs.QUARTER;
    public int trd = Configs.THIRD;
    public int et = Configs.EIGHTH;

    public static Point mousePosition = new Point(0, 0);

    private static Seed hoveredSeed = null;

    //entities
    public static Player player;
    public static Stall shop, sell;

    //lists
    public static ArrayList<Stall> stalls;

    public Game(){
        this.setPreferredSize(new Dimension(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();

        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);

        mapConstructor = new MapConstructor("res/maps/debug_gag_2.zip");

        player = new Player();

        shop = new Stall.Shop(1, 0);
        sell = new Stall.Sell(3, 0);

        stalls = new ArrayList<>();
        stalls.add(shop);
        stalls.add(sell);

        this.setFocusable(true);

        gameThread = new Thread(this);
    }

    public void start(){
        Configs.init();
        gameThread.start();
    }

    public void updateHoveredSeed() {

        int px = (int) player.x;
        int py = (int) player.y;
        int psx = player.sx;
        int psy = player.sy;

        int mx = px - psx + mousePosition.x;
        int my = py - psy + mousePosition.y;

        Seed currentlyFoundSeed = null;

        for (int i = player.plantedSeeds.size() - 1; i >= 0; i--) {
            Seed seed = player.plantedSeeds.get(i);
            if (seed.hide) continue; // Skip hidden seeds

            int seedWorldX = (int) seed.x - et;
            int seedWorldY = (int) seed.y - et;
            int seedWidth = seed.ewidth;
            int seedHeight = seed.eheight;

            Rectangle seedBounds = new Rectangle(seedWorldX, seedWorldY, seedWidth, seedHeight);

            if (seedBounds.contains(mx, my)) {
                currentlyFoundSeed = seed;
                break; // Found the topmost seed, no need to check others underneath
            }
        }

        // Update the hoveredSeed field. This will trigger a repaint if it changes,
        // or you can force repaint if you always want tooltip to update position.
        if (hoveredSeed != currentlyFoundSeed) {
            hoveredSeed = currentlyFoundSeed;
            //repaint(); // Optionally repaint if the hovered seed changes
        }
        // If you want the tooltip to always follow the mouse even if the hoveredSeed doesn't change,
        // you might always call repaint() from mouseMoved/Dragged, but that can be less efficient.
        // It's often better to only repaint if the state (hoveredSeed) changes.
        // The tooltip drawing will use the latest mousePosition anyway.
    }

    private void checkCollisions(){

        //logic
    }

    private void update(){

        player.move();
        player.handleInteractions();
        player.dropItem();

        for(Stall st : stalls){
            st.handleChecks();
        }

        updateHoveredSeed();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        mapConstructor.renderMap(g2);

        //render stalls
        for(Stall st : stalls){
            st.render(g2);
        }

        player.render(g2);

        Tools.renderSeedTooltip(g2, hoveredSeed);

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
