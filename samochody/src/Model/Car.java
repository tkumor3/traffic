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
    private boolean is_safety;
    private int free_cell;
    private int next_v;
    private static int ID = 0;
    private Sensor sensorl;
    private Sensor sensorr;
    private Sensor lsensor;
    private int carID;
    private int a;
    private TrafficLight cloest_light;
    private boolean wait;


    Car() {
        Car.ID++;
        carID = ID;
        System.out.println(carID);
        length = 3;
        pas = 0;
        dys = 15;
        pas = 0;
        Random generator = new Random();
        int i = generator.nextInt(6) + 1;
        v = i;
        pas = i%2;
        vmax = i%3 + 6;
        a = i%3 +1;
        sensorl = new LeftSensor();
        sensorr = new RightSensor();
        lsensor = new LightSensor();


    }
    Car(int dys) {
        Car.ID++;
        carID = ID;
        System.out.println(carID);
        length = 3;
        pas = 0;
        this.dys = dys;
        pas = 0;
        Random generator = new Random();
        int i = generator.nextInt(6) + 1;
        v = 4;
        pas = 0;
        vmax = i%3 + 6;
        a = i%3 +1;
        sensorl = new LeftSensor();
        sensorr = new RightSensor();
        lsensor = new LightSensor();


    }
    Car(int pas,int dys ,int vmax, int v, int a, int length){

        Car.ID++;
        carID = ID;
        System.out.println(carID);
        this.length = length;
        this.pas = pas;
        this.dys = dys;
        this.v = v;
        this.vmax = vmax;
        this.a = a;
        sensorl = new LeftSensor();
        sensorr = new RightSensor();
        lsensor = new LightSensor();

    }

    /**
     * przyspieszenie naszego pojazdu ale tylko jesli jest to bezpieczne
     */

    private void acceletion() {
        if(!wait) {
            if (!is_safety) {
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

        if(is_safety) {
            dys += v;
            acceletion();

        }else {

                dys = (free_cell + dys);
                v = next_v;


        }
    }

    /*
    sprawdzam ile mam wolnych miejsc prze autem i z jaką prędkością ten samochów się porusza
    dostosowanie prędkości
     */
    private boolean checkRoad(Cell_Road[][] road, int my_pos_dis, int my_pos_pas, LinkedList<TrafficLight> lights) throws CarFinish {
        if(my_pos_dis > 620)
        throw new CarFinish();
        my_pos_dis = my_pos_dis + length;
        free_cell = 0;


        for (TrafficLight light : lights){
            if(light.getDist() > my_pos_dis){
                cloest_light = light;
                break;
            }
        }



        if (pas > 0 && sensorr.CheckRoad(road, my_pos_dis, my_pos_pas, v, length)) {
            pas -= 1;

        }
        try {
            while ((!road[my_pos_dis + 1][my_pos_pas].is_car()) && free_cell <= v + 1) {
                if(my_pos_dis + 1 == cloest_light.getDist()){
                    if(!cloest_light.isCan_go()){
                        dys = my_pos_dis - 3;
                        free_cell = 0;
                        next_v = 0;
                        wait = true;
                        return false;
                    }else{
                        wait = false;

                    }

                }
            my_pos_dis += 1;
            free_cell += 1;
             }

            if (free_cell < v + 1) {
                next_v = road[my_pos_dis + 1][my_pos_pas].getV();
                if (next_v + free_cell < v) {
                    if (pas < 1 && sensorl.CheckRoad(road, dys + 3, my_pos_pas, v, length)) {
                        pas = pas+1;
                        return true;
                    } else {

                        return false;

                    }
                }


            } else {
                return true;
            }
        } catch (Exception e) {
            throw new CarFinish();
        }
        return true;
    }


    public void makeMove(Cell_Road [][] my_road,LinkedList<TrafficLight> light) throws CarFinish {
        is_safety = checkRoad(my_road,dys,pas,light);
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
