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
    private TrafficLight  light;

    CrossRoad(int dis, int interval,int red,int green){
        this.dis = dis;
        this.interval = interval;
        light = new TrafficLight(dis-1,red,green);
        car = new LinkedList<>();

    }



    public void addCar(int time){
        if(time%interval == 0){
            car.add(new Car(dis+3));
        }

    }
    public Car getCar(){
        Car pomCar =  car.removeFirst();
        pomCar.setPas(pas);
        return pomCar ;
    }

    public boolean canGo2(Road road) {
        if ((!light.isCan_go() && !car.isEmpty())) {
            for (int j = 0; j < road.getPasy(); j++) {
                free_crossroad = true;
                for (int i = dis ; i < dis + 4; i++) {
                    if (!(road.getRoads()[i][j].getType() == TypeOfCell.EMPTY)) {
                        free_crossroad = false;
                        break;
                    }
                }
                if (free_crossroad == true) {
                    pas = j;
                    return true;
                }
            }
        }
        return false;
    }

    public TrafficLight getLight() {
        return light;
    }


}
