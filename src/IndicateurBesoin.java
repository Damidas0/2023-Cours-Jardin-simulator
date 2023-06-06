import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IndicateurBesoin extends JLayeredPane {
    private BufferedImage tileset;

    private ImageIcon icons[];
    private JLabel imgs[];

    private CoordImg coordImgs[] = {

    };

    public IndicateurBesoin(int widthCase, int heightCase){
        this.icons = new ImageIcon[6];
        this.imgs = new JLabel[6];

        for (int i = 0; i < 6; i++) {
            this.imgs[i] = new JLabel();
            this.imgs[i].setBounds(0,0,widthCase,heightCase);
            add(this.imgs[i], Integer.valueOf(1)); //au milieu
            changerImg(this.coordImgs[i], i, widthCase,heightCase);
        }
    }

    public void changerImg(int xCoinSupG, int yCoinSupG, int width, int height, int i, int widthCase, int heightCase) {
        BufferedImage boutTileset = this.tileset.getSubimage(xCoinSupG, yCoinSupG, width, height); // image de la plante  (x, y : coin supérieur gauche, w, h : largeur et hauteur)
        this.icons[i] = new ImageIcon(boutTileset.getScaledInstance(widthCase, heightCase, java.awt.Image.SCALE_SMOOTH)); // icône redimentionnée
        this.imgs[i].setIcon(this.icons[i]);
    }

    public void changerImg(CoordImg coordImg, int i, int widthCase, int heightCase) {
        changerImg(coordImg.xCoinSupG, coordImg.yCoinSupG, coordImg.width, coordImg.height, i, widthCase, heightCase);
    }
}
