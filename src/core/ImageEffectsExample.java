package core;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageEffectsExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Effects: Gradient & Neon");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new EffectsPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class EffectsPanel extends JPanel {

    private BufferedImage originalMango;
    private BufferedImage goldMango;
    private BufferedImage gradientMango;
    private BufferedImage neonMango;

    public EffectsPanel() {
        // --- Load the original image ---
        try {
            originalMango = ImageIO.read(ImageTintingExample.class.getResourceAsStream("/textures/items/debug_item.png"));
        } catch (IOException e) {
            System.err.println("Error: Could not find mango.png in the same directory!");
            originalMango = createFallbackImage();
        }

        // --- Create the tinted and effect versions ---
        goldMango = tintImage(originalMango, new Color(255, 0, 121, 255));

        // Create the new effects
        gradientMango = createGradientMango(originalMango, new Color(255, 100, 0, 150), new Color(200, 0, 255, 150));
        neonMango = createNeonMango(originalMango, new Color(0, 255, 150), 10);

        // Adjust panel size to fit all images
        int width = (originalMango.getWidth() * 3 + 100) * 2; // Scaled width + padding
        int height = originalMango.getHeight() * 3 + 80; // Scaled height + padding
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(20, 20, 30)); // Dark background for neon
    }

    /**
     * Tints an image with a solid color. (From previous example)
     */
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

    /**
     * Applies a gradient overlay to an image.
     * @param image The source image.
     * @param color1 The starting color of the gradient.
     * @param color2 The ending color of the gradient.
     * @return A new BufferedImage with the gradient applied.
     */
    public static BufferedImage createGradientMango(BufferedImage image, Color color1, Color color2) {
        BufferedImage gradientImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = gradientImage.createGraphics();

        // 1. Draw the original mango first
        g2d.drawImage(image, 0, 0, null);

        // 2. Create a GradientPaint object. This one goes from top-left to bottom-right.
        GradientPaint gp = new GradientPaint(
                0, 0, color1,
                image.getWidth(), image.getHeight(), color2);

        // 3. Set the composite rule to only draw on top of existing pixels
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));

        // 4. Set the paint to our gradient and fill
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

        g2d.dispose();
        return gradientImage;
    }

    /**
     * Creates a neon glow effect around an image.
     * This is a multi-step process: create the glow, then draw a bright version on top.
     * @param image The source image.
     * @param neonColor The color of the glow.
     * @param glowSize The thickness of the glow effect.
     * @return A new BufferedImage with the neon effect.
     */
    public static BufferedImage createNeonMango(BufferedImage image, Color neonColor, int glowSize) {
        // Create a new image that's larger to accommodate the glow
        int newWidth = image.getWidth() + glowSize * 2;
        int newHeight = image.getHeight() + glowSize * 2;
        BufferedImage neonImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = neonImage.createGraphics();

        // --- STEP 1: Create the outer glow ---
        // We'll do this by creating a "mask" of the mango and blurring it.
        // A simple way to "blur" manually is to draw the image multiple times with transparency.

        // Create a solid color version of the mango to use as a glow source
        BufferedImage glowMask = tintImage(image, neonColor);

        // Set a low alpha composite to make the glow transparent
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));

        // Draw the glow mask multiple times with slight offsets to create a "blur"
        for (int i = -glowSize; i <= glowSize; i++) {
            for (int j = -glowSize; j <= glowSize; j++) {
                if (i*i + j*j <= glowSize*glowSize) { // Draw in a circle for a smoother glow
                    g2d.drawImage(glowMask, glowSize + i, glowSize + j, null);
                }
            }
        }

        // --- STEP 2: Draw the bright inner part on top ---
        // Reset composite to normal drawing
        g2d.setComposite(AlphaComposite.SrcOver);

        // Create a bright, almost white version of the mango for the "bulb"
        BufferedImage brightMango = tintImage(image, new Color(230, 255, 250));

        // Draw the original mango outline in the neon color for a crisp edge
        g2d.drawImage(glowMask, glowSize, glowSize, null);
        // Draw the bright "bulb" on top of that
        g2d.drawImage(brightMango, glowSize, glowSize, null);

        g2d.dispose();
        return neonImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        int scale = 3;
        int spacing = 40;
        int startX = 30;
        int startY = 30;

        // Draw original and gold mango on the first row
        drawTitledImage(g2, "Original", originalMango, startX, startY, scale);
        int nextX = startX + originalMango.getWidth() * scale + spacing;
        drawTitledImage(g2, "Gold Mango", goldMango, nextX, startY, scale);

        // Draw gradient and neon mango on the second row
        int nextY = startY + originalMango.getHeight() * scale + 60;
        drawTitledImage(g2, "Sunset Gradient", gradientMango, startX, nextY, scale);
        // Neon image is larger, so we adjust its position slightly to align
        int neonX = nextX - 10 * scale; // Adjust for glow size
        int neonY = nextY - 10 * scale; // Adjust for glow size
        drawTitledImage(g2, "Neon Mango", neonMango, neonX, neonY, scale);
    }

    private void drawTitledImage(Graphics2D g2, String title, BufferedImage image, int x, int y, int scale) {
        g2.drawImage(image, x, y, image.getWidth() * scale, image.getHeight() * scale, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString(title, x, y + image.getHeight() * scale + 20);
    }

    private BufferedImage createFallbackImage() {
        BufferedImage fallback = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = fallback.createGraphics();
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, 32, 32);
        g.setColor(Color.BLACK);
        g.drawString("?", 12, 22);
        g.dispose();
        return fallback;
    }
}