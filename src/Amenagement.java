public class Amenagement extends Case{

    private int type;

    private ConditionEnvironementale modificateur;

    private int niveau;

    public Amenagement(int type, int niveau){
        if(type < 1){
            type = 1;
        }else if(type > 6){
            type = 6;
        }
        this.type = type;
        changerNiveau(niveau);
    }

    public void changerNiveau(int niveau){
        if(niveau < 1){
            niveau = 1;
        }else if(niveau > 9){
            niveau = 9;
        }
        this.niveau = niveau;

        switch (this.type){
            case 0: // + humidité : aroseur
                this.modificateur = new ConditionEnvironementale(10*niveau,0,0);
                break;
            case 1: // - humidité : ?
                this.modificateur = new ConditionEnvironementale(100-10*niveau,0,0);
                break;
            case 2: // + température : radiateur
                this.modificateur = new ConditionEnvironementale(0, 10*niveau,0);
                break;
            case 3: // - température : climatisation
                this.modificateur = new ConditionEnvironementale(0, 100-10*niveau,0);
                break;
            case 4: // + ensoleilement : miroir
                this.modificateur = new ConditionEnvironementale(0,0,10*niveau);
                break;
            case 5: // - ensoleilement : parasol
                this.modificateur = new ConditionEnvironementale(0,0,100-10*niveau);
                break;
            default:
                break;
        }
    }

    public ConditionEnvironementale getModificateur() {
        return modificateur;
    }
}
