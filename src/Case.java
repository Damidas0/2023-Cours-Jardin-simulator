public class Case implements Runnable{
    public Case(){
        Ordonnanceur.getOrdonnanceur().addRunable(this);
    }

    @Override
    public void run() {
        System.out.println("Je suis dans le run de la case");
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
