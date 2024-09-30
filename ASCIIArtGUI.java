package javadasar;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIIArtGUI extends JFrame {
    private BufferedImage image;
    private String text = "ALPIAN"; // Teks yang akan dianimasikan
    private int x = 10; // Posisi awal teks
    private int direction = 1; // Arah gerakan teks

    public ASCIIArtGUI() {
        setTitle("ASCII Art");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Buat gambar buffered
        createImage();

        // Mulai animasi
        Timer timer = new Timer(50, e -> animateText());
        timer.start();

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
        g2.drawString(text, x, 20);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, null);

        // Menampilkan ASCII art di konsol
        displayASCIIArt();
    }

    private void animateText() {
        // Update posisi teks
        x += direction * 2; // Ganti 2 dengan kecepatan gerakan

        // Ubah arah jika teks mencapai batas
        if (x > image.getWidth() - 50 || x < 10) {
            direction *= -1; // Balik arah
        }

        // Gambar ulang dengan posisi baru
        createImage();
        repaint();
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
