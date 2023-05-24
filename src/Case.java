public class Case implements Runnable{

    public Case(){
        Ordonnanceur.getOrdonnanceur().addRunable(this);
    }

    /***
     * Revoie un entier symbolisant le type de la case :
     * [0 : case cide, 1 : culture, ]
     * @return int
     */
    public int typeCase(){
        return 0;
    }

    public void afficher(){
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE CASE");
        System.out.println("");
        System.out.println("-------------------");
    }

    @Override
    public void run() {

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
