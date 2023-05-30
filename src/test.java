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

        cases.setLayout(new BorderLayout());

        // couleur de fond
        cases.setBackground(Color.CYAN);
        cases.setOpaque(true);

        JLabel fd = new JLabel();
        //fd.setBounds(0,0,150,150);
        fd.setBackground(new Color(12,15,66));
        fd.setOpaque(true);

        JLabel dv = new JLabel();
        //dv.setBounds(0,0,150,150);
        dv.setBackground(new Color(254,254,66));
        dv.setOpaque(true);

        cases.add(fd,BorderLayout.NORTH, Integer.valueOf(2)); //entre les 2
        cases.add(dv, Integer.valueOf(1)); //entre les 2


        frame.add(cases);
        frame.setVisible(true);
    }
}
