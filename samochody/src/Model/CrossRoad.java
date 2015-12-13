package Model;

import java.util.LinkedList;

/**
 * Created by Tomek on 13.12.15.
 */
public class CrossRoad {
    private LinkedList<Car> car;
    private int dis;
    private int interval;

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
        return car.removeFirst();
    }
    public boolean canGo(Road road) {
        if ((road.getRoads()[dis-1][0].getType() == TypeOfCell.RedLIGHTS)&& !car.isEmpty()) {
            for (int i = dis - 2; i < dis + 4; i++) {
                for (int j = 0; j < road.getPasy(); j++) {
                    if (!(road.getRoads()[i][j].getType() == TypeOfCell.EMPTY)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
