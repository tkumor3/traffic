package Model;

/**
 * Created by Tomek on 16.10.15.
 */
public class Cell_Road {
    private TypeOfCell is_car;
    private int v;

    Cell_Road(){
        is_car = TypeOfCell.EMPTY;
    }
    void setCar(int v){
        this.v = v;
        is_car = TypeOfCell.CAR;

    }

    void setLights(){
        this.v = 0;
        is_car = TypeOfCell.LIGHTS;
    }


    public TypeOfCell getIs_car() {
        return is_car;
    }

    void setEmpty(){
        is_car = TypeOfCell.EMPTY;
    }

    public boolean is_car() {
        if(is_car == TypeOfCell.CAR || is_car == TypeOfCell.LIGHTS) {
            return true;
        }else{
            return false;
        }

    }

    public int getV() {
        return v;
    }
}
