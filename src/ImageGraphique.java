import javax.swing.*;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageGraphique extends JLabel {
    private ImageIcon icon;
    int height, width;

    CoordImg coordImg;

    public ImageGraphique(int width, int height, CoordImg coordImg){
        super();
        this.height = height;
        this.width = width;
        this.coordImg = coordImg;
        changerImg(coordImg);
    }

    public ImageGraphique(int width, int height, int idGraine){
        super();
        this.height = height;
        this.width = width;
        this.coordImg = Tileset.IMAGE_GRAINE[idGraine];
        changerImg(this.coordImg);
    }

    public ImageGraphique(int width, int height, String nom){
        super();
        this.height = height;
        this.width = width;
        this.coordImg = Tileset.IMAGE_AUTRE.get(nom);
        changerImg(this.coordImg);
    }

    public void changerTaille(int width, int height){
        this.height = height;
        this.width = width;
        changerImg(this.coordImg);
    }

    public Image getImage(){
        return this.icon.getImage();
    }

    private void changerImg(int xCoinSupG, int yCoinSupG, int width, int height) {
        BufferedImage boutTileset = Tileset.tileset.getSubimage(xCoinSupG, yCoinSupG, width, height); // image de la plante  (x, y : coin supérieur gauche, w, h : largeur et hauteur)
        this.icon = new ImageIcon(boutTileset.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)); // icône redimentionnée
        setIcon(this.icon);
    }

    public void changerImg(CoordImg coordImg) {
        if(coordImg != null) {
            this.coordImg = coordImg;
            changerImg(coordImg.xCoinSupG, coordImg.yCoinSupG, coordImg.width, coordImg.height);
        }
    }

    public void changerImg(int idGraine) {
        this.coordImg = Tileset.IMAGE_GRAINE[idGraine];
        changerImg(this.coordImg);
    }

    public void changerImgAmenagement(int idAmenagement) {
        this.coordImg = Tileset.IMAGE_AMENAGEMENT[idAmenagement];
        changerImg(this.coordImg);
    }


    public void changerImg(String nom) {
        this.coordImg = Tileset.IMAGE_AUTRE.get(nom);
        changerImg(this.coordImg);
    }
}
