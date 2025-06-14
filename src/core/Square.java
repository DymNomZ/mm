package core;

import java.awt.*;

public class Square extends Rectangle {

    public String label;
    public int sx, sy;

    public Square(int sx, int sy, int width, int height, String label){
        super(sx, sy, width, height);
        //actually this is redundant as sx and sy are passed as x and y for the Rectangle.
        //but I'll do this for readability
        this.sx = sx;
        this.sy = sy;
        this.label = label;
    }

    public void render(Graphics2D g2){

        //temporary until hotbar slot texture is made
        g2.setColor(Configs.TRANSPARENT);

        if(Tools.isHoveredOnScreen(sx, sy, width, height)){
            g2.setColor(Configs.SHOCKED_WHITE_1);
        }
        else{
            g2.setColor(Configs.TRANSPARENT);
        }

        g2.fillRect(sx, sy, width, height);
    }
}
