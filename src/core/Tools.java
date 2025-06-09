package core;

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

    public static void renderSeedTooltip(Graphics2D g2, Seed hoveredSeed){

        if (hoveredSeed != null && !hoveredSeed.hide) {

            Font tooltipFont = Configs.COMIC_SANS;
            g2.setFont(tooltipFont);
            FontMetrics fm = g2.getFontMetrics();

            String seedName = hoveredSeed.name != null ? hoveredSeed.name : "Unknown Seed";
            ArrayList<String> mutationDisplaySegments
                    = hoveredSeed.getActiveMutationDisplayStrings();

            // --- Calculate dimensions for the background box ---
            int V_PADDING = 3;
            int H_PADDING = 5;
            int PLUS_SIGN_WIDTH = fm.stringWidth(" + ");
            int LINE_SPACING_IF_MULTILINE_NEEDED = 3; // Only if mutations wrap

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

            int maxLineWidth = Math.max(nameLineWidth, mutationsLineWidth);
            int totalTooltipWidth = maxLineWidth + (2 * H_PADDING);

            int numberOfLinesForTooltip = 1; // Start with name line
            if (!mutationDisplaySegments.isEmpty()) {
                numberOfLinesForTooltip++; // Add line for mutations
            }

            int totalTooltipHeight = (numberOfLinesForTooltip * fm.getHeight()) +
                    ((numberOfLinesForTooltip - 1) * LINE_SPACING_IF_MULTILINE_NEEDED) +
                    (2 * V_PADDING);


            int boxBaseX = Game.mousePosition.x + 15;
            int boxBaseY = Game.mousePosition.y + 5;

            // Draw background box
            g2.setColor(Configs.TOOLTIP_BACKGROUND);
            g2.fillRect(boxBaseX, boxBaseY, totalTooltipWidth, totalTooltipHeight);
            g2.setColor(Color.DARK_GRAY);
            g2.drawRect(boxBaseX, boxBaseY, totalTooltipWidth, totalTooltipHeight);

            // --- Draw Text ---
            int currentY = boxBaseY + fm.getAscent() + V_PADDING;

            // 1. Draw Seed Name (Centered)
            g2.setColor(Color.WHITE); // Color for seed name
            int nameLineX = boxBaseX + (totalTooltipWidth - nameLineWidth) / 2;
            g2.drawString(seedName, nameLineX, currentY);

            // 2. Draw Mutations Line (if any)
            if (!mutationDisplaySegments.isEmpty()) {
                currentY += fm.getHeight() + LINE_SPACING_IF_MULTILINE_NEEDED; // Move to next line
                int currentX = boxBaseX + H_PADDING; // Starting X for the mutations line (left-aligned)

                for (int k = 0; k < mutationDisplaySegments.size(); k++) {
                    String segmentText = mutationDisplaySegments.get(k); // e.g., "Moist (x2)"

                    // Extract raw mutation name from the segmentText for color lookup
                    String rawMutationName = segmentText.split(" \\(")[0].trim();

                    Color segmentColor
                            = Configs.TOOLTIP_COLORS_MAP.getOrDefault(
                            rawMutationName, Color.LIGHT_GRAY
                    );
                    g2.setColor(segmentColor);
                    g2.drawString(segmentText, currentX, currentY);

                    currentX += fm.stringWidth(segmentText); // Move X for the next segment

                    // Draw " + " if it's not the last mutation segment
                    if (k < mutationDisplaySegments.size() - 1) {
                        g2.setColor(Color.WHITE); // Color for the " + " sign
                        g2.drawString(" + ", currentX, currentY);
                        currentX += PLUS_SIGN_WIDTH;
                    }
                }
            }
        }
    }

}