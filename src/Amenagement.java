public class Amenagement extends Case{

    private int type;


    public Amenagement(int type){
        if(type < 0){
            type = 0;
        }else if(type > 5){
            type = 5;
        }
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
