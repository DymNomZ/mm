package core;

import java.awt.*;
import java.util.ArrayList;

public class GUI {

    private static int ts = Configs.TILE_SIZE;
    private static int h = Configs.HALF;
    private static int q = Configs.QUARTER;
    private int trd = Configs.THIRD;
    private static int et = Configs.EIGHTH;

    private static KeyHandler keyHandler;
    private static MouseHandler mouseHandler;

    public static ArrayList<Rectangle> buttons = new ArrayList<>();
    private static boolean finishedLoading = false;

    private static void renderElement(
            Graphics2D g2, int orientation,
            int number, int sx, int sy, int width, int height, int spacing
    ){
        //temporary until hotbar slot texture is made
        g2.setColor(Configs.TRANSPARENT);
        for(int i = 0; i < number; i++){

            if(Tools.isHoveredOnScreen(sx, sy, width, height)){
                g2.setColor(Configs.SHOCKED_WHITE_1);
            }
            else{
                g2.setColor(Configs.TRANSPARENT);
            }

            g2.fillRect(sx, sy, width, height);

            if(orientation == 1) sx += spacing;
            else sy += spacing;
        }
    }

    private static void renderElement(
            Graphics2D g2, int sx, int sy, int width, int height
    ){
        //temporary until hotbar slot texture is made
        g2.setColor(Configs.TRANSPARENT);
        g2.fillRect(sx, sy, width, height);
    }

    private static int hotbarSlots = 10;
    private static int hotbarSlotHeight = ts;
    private static int hotbarSlotWidth = ts;
    private static int hotbarSlotGap = q;
    private static int hotbarWidth =
            (hotbarSlots * hotbarSlotWidth) + (hotbarSlots - 1 * hotbarSlotGap);

    private static void hotbar(Graphics2D g2){

        int sx = (Configs.SCREEN_WIDTH / 2) - (hotbarWidth / 2) - ts;
        int sy = Configs.SCREEN_HEIGHT - hotbarSlotHeight - ts;

        renderElement(
                g2, 1, hotbarSlots, sx, sy,
                hotbarSlotWidth, hotbarSlotHeight,
                hotbarSlotWidth + hotbarSlotGap
        );
    }

    private static int topRightButtons = 3;
    private static int topRightButtonWidth = ts;
    private static int topRightButtonHeight = ts;
    private static int topRightButtonGap = q;

    private static void topRightButtons(Graphics2D g2){

        int sx = h;
        int sy = h;

        renderElement(
                g2, 1, topRightButtons, sx, sy,
                topRightButtonWidth, topRightButtonHeight,
                topRightButtonWidth + topRightButtonGap
        );
    }

    private static int inventoryTabsNum = 5;
    private static int inventoryTabWidth = ts;
    private static int inventoryTabHeight = ts;
    private static int inventoryTabGap = q;
    private static int inventoryTabsHeight =
            (inventoryTabsNum * inventoryTabHeight) +
                    ((inventoryTabsNum - 1) * inventoryTabGap);


    private static void inventoryTabs(Graphics2D g2, int sx, int sy){

        renderElement(
                g2, 2, inventoryTabsNum, sx, sy,
                inventoryTabWidth, inventoryTabHeight,
                inventoryTabHeight + inventoryTabGap
        );

    }

    public static boolean inventoryClicked = false;

    private static int inventorySlots = hotbarSlots - 1;
    private static int inventorySlotWidth = ts;
    private static int inventorySlotHeight = ts;
    private static int inventorySlotGap = q;
    private static int inventoryHeight =
            (inventoryTabsHeight);
    private static int inventoryWidth =
            (inventorySlots * inventorySlotWidth) + ((inventorySlots - 1) * inventorySlotGap);

    private static void inventory(Graphics2D g2){

        if(!inventoryClicked) return;

        int sx = (Configs.SCREEN_WIDTH / 2) - (hotbarWidth / 2) - ts;
        int sy = Configs.SCREEN_HEIGHT - inventoryTabsHeight - (ts * 2) - inventoryTabGap;

        inventoryTabs(g2, sx, sy);

        //shift
        sx += inventoryTabWidth + inventoryTabGap;

        renderElement(g2, sx, sy, inventoryWidth, inventoryHeight);

    }

    public static void handleClick(int mx, int my){

//        System.out.println(mx + " " + my);

        Rectangle r;

        for(int i = 0; i < buttons.size(); i++){

            r = buttons.get(i);

            if(r.contains(mx, my)){
                //check which button

                if(i == 1){
                    inventoryClicked = !inventoryClicked;
                }
            }
        }
    }

    private static void saveButtonCoords(
            int orientation,
            int number, int sx, int sy, int width, int height, int spacing
    ){
        for(int i = 0; i < number; i++){

            buttons.add(
                    new Rectangle(sx, sy, width, height)
            );

            if(orientation == 1) sx += spacing;
            else sy += spacing;
        }
    }

    private static void initButtons(){

        int sx = h;
        int sy = h;

        saveButtonCoords(
                1, topRightButtons, sx, sy,
                topRightButtonWidth, topRightButtonHeight,
                topRightButtonWidth + topRightButtonGap
        );

        sx = (Configs.SCREEN_WIDTH / 2) - (hotbarWidth / 2) - ts;
        sy = Configs.SCREEN_HEIGHT - hotbarSlotHeight - ts;

        saveButtonCoords(
                1, hotbarSlots, sx, sy,
                hotbarSlotWidth, hotbarSlotHeight,
                hotbarSlotWidth + hotbarSlotGap
        );

        sx = (Configs.SCREEN_WIDTH / 2) - (hotbarWidth / 2) - ts;
        sy = Configs.SCREEN_HEIGHT - inventoryTabsHeight - (ts * 4);

        saveButtonCoords(
                2, inventoryTabsNum, sx, sy,
                inventoryTabWidth, inventoryTabHeight,
                inventoryTabHeight + inventoryTabGap
        );

//        for(Rectangle r : buttons){
//            System.out.println(r.x + " " + r.y);
//        }
    }

    public static void init(){

        keyHandler = Game.keyHandler;
        mouseHandler = Game.mouseHandler;

        initButtons();

    }

    public static void render(Graphics2D g2){

        topRightButtons(g2);

        inventory(g2);

        hotbar(g2);

    }
}
