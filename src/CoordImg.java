public class CoordImg {
    public int xCoinSupG, yCoinSupG;
    public int width, height;

    private final int TAILLE_TILESET = 101;

    public CoordImg(int xCoinSupG, int yCoinSupG, int width, int height){
        if (xCoinSupG>=0 && yCoinSupG>=0 && width>=1 && height>=1){
            this.xCoinSupG = xCoinSupG;
            this.yCoinSupG = yCoinSupG;
            this.width = width;
            this.height = height;
        }else{
            System.out.println("l'une de ces valeurs est incorecte : ");
            afficher();
        }
    }

    public CoordImg(int yCase, int xCase){
        int width = TAILLE_TILESET;
        int height = TAILLE_TILESET;
        if (xCase>=0 && yCase>=0){
            this.xCoinSupG = xCase * width;
            this.yCoinSupG = yCase * height;
            this.width = width;
            this.height = height;
        }else{
            System.out.println("l'une de ces valeurs est incorecte : ");
            afficher();
        }
    }

    public void afficher() {
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE COORDIMG");
        System.out.println("xCoinSupG: " + this.xCoinSupG);
        System.out.println("yCoinSupG: " + this.yCoinSupG);
        System.out.println("width: " + this.width);
        System.out.println("height: " + this.height);
    }
}
