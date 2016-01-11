package Model;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import java.util.*;


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
    private Connection connection;
    private int counter = 0;

    public Traffic(){
        road = new  Road(640,2);
        cars = new LinkedList<>();
        wait_cars = new LinkedList<>();
        crossRoad = new LinkedList<>();
        light = new LinkedList<>();
        cars.add(new Car());
        time = 0;
        generator = new Random();
        Gson gson = new Gson();
        String roadString = gson.toJson(new JsonUtils.RoadNoCells(road));

        connection = new Connection();
        try {
           connection.sendRoad(roadString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        crossRoad.add(new CrossRoad(200, 41, 50 , 70)); //samochod co 4.1 sekundy
        crossRoad.add(new CrossRoad(400, 50, 50 , 70)); // samochod co 5 sekund
        crossRoad.add(new CrossRoad(600, 1000000000, 40 , 75)); // nie wjezdzaja

        for (CrossRoad road : crossRoad ){
            light.add(road.getLight());
        }
        Collections.sort(light, new Comparator<TrafficLight>() {
            @Override
            public int compare(TrafficLight o1, TrafficLight o2) {
                return o1.getDist() <  o2.getDist() ? -1 : 1;
            }
        });

        updateLights(light, true);

    }

    void simulation() {
        counter++;
        for (TrafficLight light : this.light )
        light.driver(road);

        for(CrossRoad crossRoad : this.crossRoad) {
            crossRoad.addCar(time);
            while (crossRoad.canGo2(road)) {
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

        if (counter == 6) {
            Car m_car = new Car();
            if (cars.getLast().getDys() > m_car.getBumper() && wait_cars.isEmpty())
                cars.addLast(m_car);
            else {
                wait_cars.add(m_car);
            }
            counter = 0;

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

    public void updateLights(List<TrafficLight> list){
        updateLights(list, false);
    }

    public void updateLights(List<TrafficLight> list, boolean update){

        for(TrafficLight l: list){
            if(update || l.justChanged){
                Gson gson = new Gson();
                String lightsString = gson.toJson(new lightsJson(light));
                try {
                    connection.sendSth(lightsString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                l.justChanged = false;
            }
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
            Gson gson = new Gson();
            String carsString = gson.toJson(cars);
             try {
                 connection.sendSth(carsString);
             } catch (IOException e) {
                e.printStackTrace();
            }
            updateLights(light);
            time++;

            if(CarFinish.count == 50)
                System.out.println("time: " + time + " loops");

            System.out.println("cars count: "  + CarFinish.count);

        }
    }

    public class lightsJson{
        public String type = "lights";
        public List<TrafficLight> lights;
        public  lightsJson(List<TrafficLight> l){
            this.lights = l;
        }

    }
}

