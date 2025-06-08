package core;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTintingExample {

    public static void main(String[] args) {
        // Standard Swing setup to create a window
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Tinting Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TintPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

class TintPanel extends JPanel {

    private BufferedImage originalMango;
    private BufferedImage goldMango;
    private BufferedImage bloodlitMango;
    private BufferedImage cosmicMango;

    public TintPanel() {
        // --- Load the original image ---
        try {
            originalMango = ImageIO.read(ImageTintingExample.class.getResourceAsStream("/textures/items/debug_item.png"));
        } catch (IOException e) {
            System.err.println("Error: Could not find mango.png in the same directory!");
            // Create a fallback image if mango.png is missing
            originalMango = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = originalMango.createGraphics();
            g.setColor(Color.RED);
            g.drawString("MANGO", 20, 50);
            g.dispose();
        }

        // --- Create the tinted versions ---
        // We use a semi-transparent color for a nice tint effect.
        // The alpha value (the first number) controls transparency. 255 is opaque, 0 is invisible.
        Color goldColor = new Color(255, 215, 0, 128); // Gold with 50% transparency
        Color bloodColor = new Color(180, 20, 20, 200); // Dark red with ~40% transparency
        Color cosmicColor = new Color(138, 43, 226, 110); // Purple with ~43% transparency

        goldMango = tintImage(originalMango, goldColor);
        bloodlitMango = tintImage(originalMango, bloodColor);
        cosmicMango = tintImage(originalMango, cosmicColor);

        // Set the panel size and background
        int width = (originalMango.getWidth() + 20) * 4;
        int height = originalMango.getHeight() + 80;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(80, 80, 80));
    }

    /**
     * The magic happens here! This function takes an image and applies a color tint.
     * @param image The source image to tint.
     * @param tintColor The color to apply as a filter.
     * @return A new BufferedImage with the tint applied.
     */
    public static BufferedImage tintImage(BufferedImage image, Color tintColor) {
        // Create a new blank image with the same dimensions and an alpha channel
        BufferedImage tintedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = tintedImage.createGraphics();

        // 1. Draw the original image onto the new one
        g2d.drawImage(image, 0, 0, null);

        // 2. Set the composite rule. SRC_ATOP means the new color is drawn only on top
        //    of the existing pixels of the destination (our mango).
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP));

        // 3. Set the tint color
        g2d.setColor(tintColor);

        // 4. Fill a rectangle over the entire image. Due to the composite rule,
        //    this will only colorize the mango pixels.
        g2d.fillRect(0, 0, image.getWidth(), image.getHeight());

        // Clean up the graphics context
        g2d.dispose();

        return tintedImage;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // For better pixel art rendering, disable anti-aliasing
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        int imgWidth = originalMango.getWidth();
        int spacing = 20;
        int scale = 3; // Make the images bigger to see the pixels

        // Draw the images and their labels
        drawTitledImage(g2, "Original", originalMango, spacing, 20, scale);
        drawTitledImage(g2, "Gold Mango", goldMango, spacing + (imgWidth*scale + spacing), 20, scale);
        drawTitledImage(g2, "Bloodlit Mango", bloodlitMango, spacing + (imgWidth*scale + spacing) * 2, 20, scale);
        drawTitledImage(g2, "Cosmic Mango", cosmicMango, spacing + (imgWidth*scale + spacing) * 3, 20, scale);
    }

    private void drawTitledImage(Graphics2D g2, String title, BufferedImage image, int x, int y, int scale) {
        g2.drawImage(image, x, y, image.getWidth() * scale, image.getHeight() * scale, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString(title, x, y + image.getHeight() * scale + 20);
    }
}
