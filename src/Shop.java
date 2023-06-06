import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Shop extends JPanel{
    private List<ShopElementCulture> cultureListe; 
    private List<ShopElementAmenagement> amenagementListe;
    private List<ShopElementAmelioration> ameliorationListe;



    public Shop(){
        this(new ArrayList<ShopElementCulture>(), 
            new ArrayList<ShopElementAmelioration>(),
            new ArrayList<ShopElementAmenagement>());
        
    }

    public Shop(List<ShopElementCulture> cultures, List<ShopElementAmelioration> ameliorations, List<ShopElementAmenagement> amenagements){
        this.cultureListe = cultures;
        this.amenagementListe = amenagements;
        this.ameliorationListe = ameliorations;

        this.setLayout(new FlowLayout());

        JPanel s = new JPanel();

        s.setLayout(new GridLayout(6,1));
        s.add(new JLabel("Culture"));
        s.add(this.buildCulture());
        s.add(new JLabel("Amélioration"));
        s.add(this.buildAmelioration());
        s.add(new JLabel("Amenagement"));
        s.add(this.buildAmenagement());
        
        
        
        JScrollPane scrollPane = new JScrollPane(s);
        



        //Paramétrage scrollpane 
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);

        
        
    }

    private JPanel buildCulture(){
        JPanel culturePanel = new JPanel(new GridLayout(this.cultureListe.size(), 1));
        for(int i = 0; i<this.cultureListe.size(); i++){
            culturePanel.add(this.cultureListe.get(i));
        }
        return culturePanel;
    }

    private JPanel buildAmenagement(){
        JPanel amenagementPanel = new JPanel(new GridLayout(this.amenagementListe.size(), 1));
        for(int i = 0; i<this.amenagementListe.size(); i++){
            amenagementPanel.add(this.amenagementListe.get(i));
        }
        return amenagementPanel;
    }

    private JPanel buildAmelioration(){
        JPanel ameliorationPanel = new JPanel(new GridLayout(this.ameliorationListe.size(), 1));
        for(int i = 0; i<this.ameliorationListe.size(); i++){
            ameliorationPanel.add(this.ameliorationListe.get(i));
        }
        return ameliorationPanel;
    }


    public static void main(String[] args) {
        JFrame f = new JFrame();
        
        f.setSize(1000, 1000);
        f.add(new Shop());
        f.setVisible(true);

    }
}
