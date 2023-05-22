public class Culture extends Case{
    private Plante plante;
    private ConditionEnvironementale conditionActuelle;

    public Culture(){}

    
    public Culture(Plante plante, ConditionEnvironementale conditionAct){
        this.plante = plante;
        this.conditionActuelle = conditionAct;
    }

    @Override
    public void run(){
        //TODO: REMPLACER LA VITESSE DE POUSSE
        this.plante.developper(this.conditionActuelle, 5);
    }
}   
