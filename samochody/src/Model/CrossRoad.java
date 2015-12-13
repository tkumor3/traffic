package Model;

import java.util.LinkedList;

/**
 * Created by Tomek on 13.12.15.
 */
public class CrossRoad {
    private LinkedList<Car> car;
    private int dis;
    private int interval;
    private int pas;
    private boolean free_crossroad;

    CrossRoad(int dis, int interval){
        this.dis = dis;
        this.interval = interval;
        car = new LinkedList<Car>();
    }
    public void addCar(int time){
        if(time%interval == 0){
            car.add(new Car(dis));
        }

    }
    public Car getCar(){
        Car pomCar =  car.removeFirst();
        pomCar.setPas(pas);
        return pomCar ;
    }
    public boolean canGo(Road road) {
        if ((road.getRoads()[dis-1][0].getType() == TypeOfCell.RedLIGHTS)&& !car.isEmpty()) {
            for (int j = 0; j < road.getPasy(); j++) {
                free_crossroad = true;
                for (int i = dis - 2; i < dis + 4; i++) {
                    if (!(road.getRoads()[i][j].getType() == TypeOfCell.EMPTY)) {
                        free_crossroad = false;
                        break;
                    }
                }
                if(free_crossroad == true)
                    pas = j;
                    return true;
            }
        }
        return false;
    }
}
