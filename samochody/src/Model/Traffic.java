package Model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Tomek on 16.10.15.
 */
public class Traffic implements Runnable {
    private LinkedList<Car> cars;
    private LinkedList<Car> wait_cars;
    private Road road;
    private int time;
    private LinkedList<CrossRoad> crossRoad;
    private LinkedList<TrafficLight> light;
    Random generator;

    public Traffic(){
        road = new  Road(1000,3);
        cars = new LinkedList<Car>();
        wait_cars = new LinkedList<Car>();
        crossRoad = new LinkedList<CrossRoad>();
        light = new LinkedList<TrafficLight>();
        cars.add(new Car());
        time = 0;
        generator = new Random();
        light.add(new TrafficLight(50, 30, 10));
        light.add(new TrafficLight(100,90,10));
        crossRoad.add(new CrossRoad(51, 2));

    }

    void simulation() {
        for (TrafficLight light : this.light )
        light.driver(road);

        for(CrossRoad crossRoad : this.crossRoad) {
            crossRoad.addCar(time);
            while (crossRoad.canGo(road)) {
                cars.add(crossRoad.getCar());
            }
        }

        for(Iterator<Car> iterator = cars.iterator(); iterator.hasNext();){
            try {
                Car a = iterator.next();
                a.makeMove(road.getRoads(),light);
            } catch (CarFinish carFinish) {
                iterator.remove();
            }


        }
        if (!wait_cars.isEmpty() && cars.getLast().getDys() > wait_cars.getFirst().getBumper()) {
            cars.add(wait_cars.getFirst());
            wait_cars.removeFirst();
        }
        int proba = generator.nextInt(3) + 1;
        if (proba == 3) {
            Car m_car = new Car();
            if (cars.getLast().getDys() > m_car.getBumper() && wait_cars.isEmpty())
                cars.addLast(m_car);
            else {
                wait_cars.add(m_car);
            }

        }

        int dog_p = generator.nextInt(20);
        if (dog_p == 2) {
            dogOnRoad();
        }


        if (!cars.isEmpty()) {
            road.reset();
            for (Car a : cars) {

                road.setCar(a.getDys(), a.getPas(), a.getV(), a.getLength());


            }
        }
    }

    void dogOnRoad(){
        int car = generator.nextInt(cars.size());
        cars.get(car).setV(1);


    }

    void printRoad(){
        for (int i = 0; i < road.getDistance(); i++) {
            for (int j = 0; j < road.getPasy(); j++) {
                 if(road.getRoads()[i][j].is_car()){
                     System.out.print(road.getRoads()[i][j].getV());
                 }else {
                     System.out.print("_");
                 }

                 }
             System.out.println("");

            }
    }

    public Road getRoad() {
        return road;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simulation();
            time ++;
        }
    }
}
