package Model;

import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Tomek on 16.10.15.
 */
public class Car {
    private int pas, dys;
    private int v, vmax;
    private int length;
    private boolean is_safe;
    private int next_v;
    private static int ID = 0;
    private Sensor sensorl;
    private Sensor sensorr;
    private int carID;
    private int a;
    private TrafficLight closest_light;
    private boolean wait = false;

    private boolean shouldCount = false;

    Car() {
        shouldCount = true;
        Car.ID++;
        carID = ID;
        length = 3;
        pas = 0;
        dys = 15;
        pas = 0;
        Random generator = new Random();
        vmax =  6 + generator.nextInt(3);
        v = vmax;
        pas = generator.nextInt(2);
        a = generator.nextInt(2) + 1;
        sensorl = new LeftSensor();
        sensorr = new RightSensor();


    }
    Car(int dys) {
        shouldCount = false;
        Car.ID++;
        carID = ID;
        length = 3;
        pas = 0;
        this.dys = dys;
        pas = 0;
        Random generator = new Random();
        vmax =  6 + generator.nextInt(3);
        v = vmax;
        pas = 0;
        a = generator.nextInt(2) + 1;
        sensorl = new LeftSensor();
        sensorr = new RightSensor();


    }

    /**
     * przyspieszenie naszego pojazdu ale tylko jesli jest to bezpieczne
     */

    private void accelerate() {
        if(!wait) {
            if (!is_safe) {
                v -= a;
            } else {
                if (v + a <= vmax) {
                    v += a;
                } else {
                    v = vmax;
                }
            }
        }
    }

    /*
        jesli jest mozliwość to jade swoja prędkośćia w przeciwnym razie podjeżdza pod drugi podjazd i przejmuje jego prędkość
     */

    private void move() {

        if(is_safe) {
            dys += v;
            accelerate();

        }else {
            dys += next_v;
        }
    }

    /*
    sprawdzam ile mam wolnych miejsc prze autem i z jaką prędkością ten samochów się porusza
    dostosowanie prędkości
     */
    private boolean checkRoad(Cell_Road[][] road, int my_pos_dis, int my_pos_pas, LinkedList<TrafficLight> lights) throws CarFinish {
        if(my_pos_dis > 620)
            throw new CarFinish(shouldCount);

        my_pos_dis = my_pos_dis + length;


        for (TrafficLight light : lights){
            if(light.getDist() > my_pos_dis ){
                closest_light = light;
                break;
            }
        }

        int distanceToCar = 0;
        int distanceToLights = 0;

        while ((!road[my_pos_dis + distanceToCar][my_pos_pas].is_car()) && distanceToCar <= v + 1) {
            distanceToCar++;
        }

        if(closest_light != null)
            while (my_pos_dis + distanceToLights != closest_light.getDist() && distanceToLights <= v + length) {
                distanceToLights++;
            }

        if (distanceToCar < 2 + v) {
            next_v = distanceToCar - 1;

            if (pas < 1 && sensorl.CheckRoad(road, dys + 5, my_pos_pas, v, length)) {
                pas = pas+1;
                return true;
            } else if (pas > 0 && sensorr.CheckRoad(road, dys + 5, my_pos_pas, v, length)) {
                pas = pas-1;
                return true;
            }

            return false;
        }


        if (closest_light != null && distanceToLights < length + 1 + v) {
            if (closest_light.isCan_go()) {
                return true;
            } else {
                next_v = distanceToLights - 1;
                return false;
            }
        }

        return true;
    }


    public void makeMove(Cell_Road [][] my_road,LinkedList<TrafficLight> light) throws CarFinish {
        is_safe = checkRoad(my_road,dys,pas,light);
        move();
    }




    public void setPas(int pas) {
        this.pas = pas;
    }

    public int getDys() {
        return dys;
    }



    public int getPas() {
        return pas;
    }


    public void setV(int v) {
        this.v = v;
    }

    public int getV() {
        return v;
    }

    public int getLength() {
        return length;
    }
    public int getBumper() {
        return length+dys;
    }
}
