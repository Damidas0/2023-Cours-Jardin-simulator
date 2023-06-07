import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class Tileset {
    public static BufferedImage tileset;

    //IMAGE_GRAINE[idGraine] => les coordImg de la plante
    final static public CoordImg[] IMAGE_GRAINE = {
            new CoordImg(0,0), //debug
            new CoordImg(0,1), //salade
            new CoordImg(0,2), //carotte
            new CoordImg(0,3), //patate
            new CoordImg(0,4), //ail
            new CoordImg(0,5), //epinard
            new CoordImg(0,6), //courge
    };

    static public HashMap<String, CoordImg> IMAGE_AUTRE = new HashMap<>();

    public Tileset(){
        Tileset.IMAGE_AUTRE.put("sol_case", new CoordImg(1,0));
        Tileset.IMAGE_AUTRE.put("sol_culture", new CoordImg(1,1));
        Tileset.IMAGE_AUTRE.put("selection", new CoordImg(1,2));
        Tileset.IMAGE_AUTRE.put("clim", new CoordImg(0,0));
        Tileset.IMAGE_AUTRE.put("radiateur", new CoordImg(0,0));
        Tileset.IMAGE_AUTRE.put("vaporisateur", new CoordImg(0,0));
        Tileset.IMAGE_AUTRE.put("filtre_aire", new CoordImg(0,0));
        Tileset.IMAGE_AUTRE.put("ombrelle", new CoordImg(0,0));
        Tileset.IMAGE_AUTRE.put("miroire", new CoordImg(0,0));
        Tileset.IMAGE_AUTRE.put("plante_morte", new CoordImg(1,3));

        charger();
    }

    public static void charger(){
        try {
            Tileset.tileset = ImageIO.read(new File("./src/img/data.png")); // chargement de l'image globale
        } catch (java.io.IOException e) {
            System.out.println("ERREUR : Impossoble d'ouvrir la tileset ./src/img/data.png   "+ e.getMessage());
        }
    }

}
