import javax.swing.JPanel;

//TODO: replace JPANEL
public class Case extends JPanel implements Runnable{
    public Case(){
        super();
        Ordonnanceur.getOrdonnanceur().addRunable(this);
    }

    @Override
    public void run() {
        //System.out.println("Je suis dans le run de la case");
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
