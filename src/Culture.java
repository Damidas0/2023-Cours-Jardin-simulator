public class Culture extends Case{
    private Plante plante;
    private ConditionEnvironementale conditionCase;

    public Culture(){}


    public Culture(Plante plante, ConditionEnvironementale conditionCase){
        this.plante = new Plante(plante);
        this.conditionCase = new ConditionEnvironementale(conditionCase);
    }

    @Override
    public void run(){
        //TODO: REMPLACER LA VITESSE DE POUSSE
        this.plante.developper(this.conditionCase, 5);
    }
}   
