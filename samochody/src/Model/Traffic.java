package Model;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Tomek on 16.10.15.
 */
public class Traffic implements Runnable {
    private LinkedList<Car> cars;
    private LinkedList<Car> wait_cars;
    private Road road;
    private Car f_car;
    private TrafficLight light;
    Random generator;

    public Traffic(){
        road = new  Road(1000,2);
        cars = new LinkedList<Car>();
        wait_cars = new LinkedList<Car>();
        cars.add(new Car());
        generator = new Random();
        light = new TrafficLight(50,20,10);

    }

    void simulation(){
        light.driver(road);
        for( Car a :cars){
            try {
                a.makeMove(road.getRoads());
            } catch (CarFinish carFinish) {
                f_car = a;
            }
        }


        if(!wait_cars.isEmpty() && cars.getLast().getDys() > wait_cars.getFirst().getBumper()){
            cars.add(wait_cars.getFirst());
            wait_cars.removeFirst();
        }

        cars.remove(f_car);

        int proba = generator.nextInt(3) + 1;
        if(proba == 3){
            Car m_car =  new Car();
            if(cars.getLast().getDys() > m_car.getBumper() && wait_cars.isEmpty())
                cars.addLast(m_car);
            else{
                wait_cars.add(m_car);
            }

        }

        int dog_p = generator.nextInt(20);
        if(dog_p == 2){
            dogOnRoad();
        }





        if(!cars.isEmpty()) {
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
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simulation();
        }
    }
}
