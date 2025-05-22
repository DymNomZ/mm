import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable{

    private Thread gameThread;

    public KeyHandler keyHandler;

    public Game(){
        this.setPreferredSize(new Dimension(Configs.SCREEN_WIDTH, Configs.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);

        this.setFocusable(true);

        gameThread = new Thread(this);
    }

    public void start(){
        gameThread.start();
    }

    private void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    @Override
    public void run(){
        long drawInterval = 1000000000/Configs.FPS;
        long lastTime = System.nanoTime();
        long currentTime;
        long delta = 0;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);

            lastTime = currentTime;

            if(delta > 0){
                update();
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
