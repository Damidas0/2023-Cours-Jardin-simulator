import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test {


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Le poti potager");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLayeredPane cases = new JLayeredPane();

        // couleur de fond
        cases.setBackground(Color.CYAN);
        cases.setOpaque(true);
        // bordure
        cases.setBorder(BorderFactory.createLineBorder(Color.black,1));

        //cases.setLayout(new BorderLayout());

        /*
        //bar de progression
        JProgressBar progressBar = new JProgressBar(0);
        progressBar.setForeground(new Color(0x69B00B));
        progressBar.setBorder(BorderFactory.createEmptyBorder());
        //progressBar.setBorder(BorderFactory.createLineBorder(Color.red,0));
        progressBar.setVisible(true);
        cases.add(progressBar, BorderLayout.SOUTH, Integer.valueOf(3)); // tout devant
*/

        //img de la plante
        String cheminImgPlante = "img/salade.png";

        // la j'ai une exception ...
        ImageIcon imgPlante;
        try {
            imgPlante = new ImageIcon(ImageIO.read(new File("img/logo.png")).getScaledInstance(20,20,java.awt.Image.SCALE_SMOOTH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*BufferedImage imageBuffer;
        imageBuffer = ImageIO.read(new File("assets/Terre.png")); // chargement de l'image globale
        ImageIcon icon = new ImageIcon(imageBuffer);
        Image image = icon.getImage().getScaledInstance(icon.getIconWidth() / 200 * 78,
                icon.getIconHeight() / 10 * 86,
                Image.SCALE_SMOOTH);
        icon = new ImageIcon(image, icon.getDescription());
        ptest.setIcon(icon); // partie rafraichissement*/

        JLabel planteLabel = new JLabel();
        planteLabel.setVisible(true);
        planteLabel.setOpaque(true);
        planteLabel.setIcon(imgPlante);
        planteLabel.setBackground(Color.BLUE);
        planteLabel.setBounds(0,0,500,500);

        cases.add(planteLabel, Integer.valueOf(1)); //entre les 2



        frame.add(cases);
        frame.setVisible(true);
    }
}
