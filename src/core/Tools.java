package core;

import entities.Entity;
import entities.Player;
import entities.Seed;
import entities.normal_seeds.*;
import world.Door;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Tools {

    static Random rand = new Random();
    static int maxX = MapConstructor.mapWidth;
    static int maxY = MapConstructor.mapHeight;

    public static int ts = Configs.TILE_SIZE;
    public static int h = Configs.HALF;
    public int q = Configs.QUARTER;
    public int trd = Configs.THIRD;
    public static int et = Configs.EIGHTH;

    //world tiles
    public static Door openDoor = new Door();

    public static int getRandomX(){
        return rand.nextInt(0, maxX);
    }

    public static int getRandomY(){
        return rand.nextInt(0, maxY);
    }

    public static boolean isTouch(Entity e){

        Player p = Game.player;

        int pl = p.reach.x;
        int pr = p.reach.x + p.reach.width;
        int pt = p.reach.y;
        int pb = p.reach.y + p.reach.height;

        int il = (int)e.x;
        int ir = (int)(e.x + e.ewidth);
        int it = (int)e.y;
        int ib = (int)(e.y + e.eheight);

        return !(pl >= ir || pr <= il || pt >= ib || pb <= it);
    }

    public static boolean isHovered(Entity e){

        int px = (int) Game.player.x;
        int py = (int) Game.player.y;
        int psx = Game.player.sx;
        int psy = Game.player.sy;

        int mx = px - psx + Game.mousePosition.x;
        int my = py - psy + Game.mousePosition.y;

        int ex = (int) e.x - et;
        int ey = (int) e.y - et;
        int ew = e.ewidth;
        int eh = e.eheight;

        Rectangle entityBounds = new Rectangle(ex, ey, ew, eh);

        return entityBounds.contains(mx, my);

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

    static int V_PADDING = 10;
    static int H_PADDING = 20;
    static int LINE_SPACING = 3;

    private static void renderDialogueBox(
            Graphics2D g2, int x, int y, int width, int height
    ){

        g2.setColor(Configs.DIALOGUE_BOX);
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.DARK_GRAY);

    }

    private static void renderWord(
            Graphics2D g2, String word, Color color, int x, int y
    ){
        g2.setColor(color);
        g2.drawString(word, x, y);
    }

    public static void renderWordBox(Graphics2D g2, Entity e, String word){

        Font tooltipFont = Configs.COMIC_SANS;
        g2.setFont(tooltipFont);
        FontMetrics fm = g2.getFontMetrics();

        int wordWidth = fm.stringWidth(word) + (2 * H_PADDING);
        int wordHeight = fm.getHeight() + (2 * V_PADDING);

        int cx = (e.sx + (e.ewidth / 2)) - (wordWidth / 2);
        int cy = (e.sy + (e.eheight / 3));

        int boxBaseX = cx;
        int boxBaseY = cy;

        renderDialogueBox(g2, boxBaseX, boxBaseY, wordWidth, wordHeight);

        int currentY = boxBaseY + fm.getAscent() + V_PADDING;
        int currentX = boxBaseX + H_PADDING;

        renderWord(g2, word, Color.WHITE, currentX, currentY);

    }

    public static boolean isHoveredOnScreen(int sx, int sy, int width, int height){

        int mx = Game.mousePosition.x;
        int my = Game.mousePosition.y;

        Rectangle bounds = new Rectangle(sx, sy, width, height);

        return bounds.contains(mx, my);
    }

    public static void renderMultilineBox(Graphics2D g2, Entity e, String[] words){

        Font tooltipFont = Configs.COMIC_SANS;
        g2.setFont(tooltipFont);
        FontMetrics fm = g2.getFontMetrics();

        //iterate and get the longest word
        int boxWidth = 0;
        int longWordWidth = 0;

        for (String word : words) {
            boxWidth = Math.max(boxWidth, fm.stringWidth(word));
        }

        int wordHeight = fm.getHeight();
        longWordWidth = boxWidth;
        boxWidth += (2 * H_PADDING);

        int numberOfLines = words.length;

        int boxHeight = (numberOfLines * wordHeight) +
                ((numberOfLines - 1) * LINE_SPACING) +
                (2 * V_PADDING);

        int cx = (e.sx + (e.ewidth / 2)) + h;
        int cy = (e.sy + (e.eheight / 2)) + h;

        int boxBaseX = cx;
        int boxBaseY = cy;

        renderDialogueBox(
                g2, boxBaseX, boxBaseY,
                boxWidth, boxHeight
        );

        int currentY = boxBaseY + fm.getAscent() + V_PADDING;
        int currentX = boxBaseX + H_PADDING;

        Color color;

        for(String word : words){

            if(isHoveredOnScreen(currentX, currentY - wordHeight, longWordWidth, wordHeight)){
                color = Configs.HOVER_COLOR;
                //logic here!
            }
            else color = Color.WHITE;

            renderWord(g2, word, color, currentX, currentY);
            currentY += wordHeight + LINE_SPACING;
        }

    }

    public static void renderSeedTooltip(Graphics2D g2, Seed hoveredSeed){

        if (hoveredSeed != null && !hoveredSeed.hide) {

            Font tooltipFont = Configs.COMIC_SANS;
            g2.setFont(tooltipFont);
            FontMetrics fm = g2.getFontMetrics();

            String seedName = hoveredSeed.name != null ? hoveredSeed.name : "Unknown Seed";
            ArrayList<String> mutationDisplaySegments
                    = hoveredSeed.getActiveMutationDisplayStrings();

            // --- Calculate dimensions for the background box ---
            int PLUS_SIGN_WIDTH = fm.stringWidth(" + ");

            // Width of the name line
            int nameLineWidth = fm.stringWidth(seedName);

            // Calculate width of the mutations line
            int mutationsLineWidth = 0;
            if (!mutationDisplaySegments.isEmpty()) {
                for (int k = 0; k < mutationDisplaySegments.size(); k++) {
                    mutationsLineWidth += fm.stringWidth(mutationDisplaySegments.get(k));
                    if (k < mutationDisplaySegments.size() - 1) {
                        mutationsLineWidth += PLUS_SIGN_WIDTH;
                    }
                }
            }

            //compare which is longer for rectangle basis
            int maxLineWidth = Math.max(nameLineWidth, mutationsLineWidth);
            int totalTooltipWidth = maxLineWidth + (2 * H_PADDING);

            int numberOfLinesForTooltip = 1; // Start with name line
            if (!mutationDisplaySegments.isEmpty()) {
                numberOfLinesForTooltip++; // Add line for mutations
            }

            int totalTooltipHeight = (numberOfLinesForTooltip * fm.getHeight()) +
                    ((numberOfLinesForTooltip - 1) * LINE_SPACING) +
                    (2 * V_PADDING);

            //positioning for the box
            int boxBaseX = Game.mousePosition.x + 15;
            int boxBaseY = Game.mousePosition.y + 5;

            renderDialogueBox(
                    g2, boxBaseX, boxBaseY,
                    totalTooltipWidth, totalTooltipHeight
            );

            // --- Draw Text ---
            int currentY = boxBaseY + fm.getAscent() + V_PADDING;

            // 1. Draw Seed Name (Centered)
            int nameLineX = boxBaseX + (totalTooltipWidth - nameLineWidth) / 2;
            renderWord(g2, seedName, Color.WHITE, nameLineX, currentY);

            // 2. Draw Mutations Line (if any)
            if (!mutationDisplaySegments.isEmpty()) {
                currentY += fm.getHeight() + LINE_SPACING; // Move to next line
                int currentX = boxBaseX + H_PADDING; // Starting X for the mutations line (left-aligned)

                for (int k = 0; k < mutationDisplaySegments.size(); k++) {
                    String segmentText = mutationDisplaySegments.get(k); // e.g., "Moist (x2)"

                    // Extract raw mutation name from the segmentText for color lookup
                    String rawMutationName = segmentText.split(" \\(")[0].trim();

                    Color segmentColor
                            = Configs.TOOLTIP_COLORS_MAP.getOrDefault(
                            rawMutationName, Color.LIGHT_GRAY
                    );
                    renderWord(g2, segmentText, segmentColor, currentX, currentY);

                    currentX += fm.stringWidth(segmentText); // Move X for the next segment

                    // Draw " + " if it's not the last mutation segment
                    if (k < mutationDisplaySegments.size() - 1) {
                        renderWord(g2, " + ", Color.WHITE, currentX, currentY);
                        currentX += PLUS_SIGN_WIDTH;
                    }
                }
            }
        }
    }

}