import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IndicateurBesoin extends JPanel {

    private ImageIcon icons[];
    private JLabel imgs[];

    private CoordImg coordImgs[] = {
            new CoordImg(2,0), // manque d'humidité
            new CoordImg(2,1), // trop d'humidité
            new CoordImg(2,2), // manque de température (froid)
            new CoordImg(2,3), // trop de température (chaud)
            new CoordImg(2,4), // manque d'ensoleillement
            new CoordImg(2,5), // trop d'ensoleillement
    };

    public IndicateurBesoin(int widthCase, int heightCase){
        this.icons = new ImageIcon[6];
        this.imgs = new JLabel[6];

        this.setOpaque(false);

        setLayout(new FlowLayout(FlowLayout.CENTER, heightCase/15, widthCase/15));

        for (int i = 0; i < 6; i++) {
            this.imgs[i] = new JLabel();
            this.imgs[i].setVisible(false);
            add(this.imgs[i]); //au milieu
            changerImg(this.coordImgs[i], i, widthCase,heightCase);
        }
    }

    public void changerImg(int xCoinSupG, int yCoinSupG, int width, int height, int i, int widthCase, int heightCase) {
        BufferedImage boutTileset = Tileset.tileset.getSubimage(xCoinSupG, yCoinSupG, width, height); // image de la plante  (x, y : coin supérieur gauche, w, h : largeur et hauteur)
        this.icons[i] = new ImageIcon(boutTileset.getScaledInstance(widthCase/4, heightCase/4, java.awt.Image.SCALE_SMOOTH)); // icône redimentionnée
        this.imgs[i].setIcon(this.icons[i]);
    }

    public void changerImg(CoordImg coordImg, int i, int widthCase, int heightCase) {
        changerImg(coordImg.xCoinSupG, coordImg.yCoinSupG, coordImg.width, coordImg.height, i, widthCase, heightCase);
    }

    public void afficherBesoin(int besoin, boolean afficher){
        this.imgs[besoin].setVisible(afficher);
    }

    public void afficherBesoin(boolean[] besoin){
        if(besoin.length != 7){
            System.out.println("le tableau des besoin est invalide, la case n'est probablement pas une culture");
        }else
        for (int i = 0; i < 6; i++) {
            this.imgs[i].setVisible(besoin[i+1]);
        }
    }

    public void afficherBesoin(int besoin){
        this.imgs[besoin].setVisible(true);
    }
}
