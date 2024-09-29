package javadasar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIIArtGUI extends JFrame {
    private BufferedImage image;

    public ASCIIArtGUI() {
        setTitle("ASCII Art");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Buat gambar buffered
        createImage();

        // Override metode paint untuk menggambar gambar
        setVisible(true);
    }

    private void createImage() {
        int width = 150;
        int height = 30;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SanSerif", Font.BOLD, 24));
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("ALPIAN", 10, 20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, null);

        // Menampilkan ASCII art di konsol
        displayASCIIArt();
    }

    private void displayASCIIArt() {
        StringBuilder asciiArt = new StringBuilder();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            StringBuilder builder = new StringBuilder();

            for (int x = 0; x < width; x++) {
                builder.append(image.getRGB(x, y) == -16777216 ? " " : "0");
            }
            asciiArt.append(builder).append("\n");
        }

        // Tampilkan hasil di konsol
        System.out.println(asciiArt.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ASCIIArtGUI::new);
    }
}
