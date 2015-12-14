package Model;

/**
 * Created by Tomek on 16.10.15.
 */
public class Cell_Road {
    private TypeOfCell typ;
    private int v;

    Cell_Road(){
        typ = TypeOfCell.EMPTY;
    }
    void setCar(int v){
        this.v = v;
        typ = TypeOfCell.CAR;
    }

    void setRedLights(){
        this.v = 0;
        typ = TypeOfCell.RedLIGHTS;
    }


    void setGreenLights(){
        this.v = 0;
        typ = TypeOfCell.GreenLIGHTS;
    }


    public TypeOfCell getType() {
        return typ;
    }

    void setEmpty(){
        typ = TypeOfCell.EMPTY;
    }

    public boolean is_car() {
        if(typ == TypeOfCell.CAR ) {
            return true;
        }else{
            return false;
        }

    }

    public int getV() {
        return v;
    }
    
}
