package Model;

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
    private int carID;
    private int a;


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
        vmax = i%3 + 6;
        a = i%3 +1;
        sensorl = new LeftSensor();
        sensorr = new RightSensor();


    }

    /**
     * przyspieszenie naszego pojazdu ale tylko jesli jest to bezpieczne
     */

    private void acceletion() {
        if (!is_safety) {
            v -= a;
        } else {
            if (!(v == vmax)) {
                v += a;
            }else{
                v = vmax;
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

            dys = (free_cell+dys);
            v = next_v;

        }
    }

    /*
    sprawdzam ile mam wolnych miejsc prze autem i z jaką prędkością ten samochów się porusza
    dostosowanie prędkości
     */
    private void checkRoad(Cell_Road[][] road, int my_pos_dis, int my_pos_pas) throws CarFinish{

        my_pos_dis = my_pos_dis + length;
        free_cell = 0;

        if(pas > 0 && sensorr.CheckRoad(road,my_pos_dis,my_pos_pas,v,length)){
            pas -= 1;
        }
        try {
            while (!(road[my_pos_dis+1][my_pos_pas].is_car()) && free_cell <= v+1) {
                my_pos_dis += 1;
                free_cell += 1;
            }
            if (free_cell  < v+1 ) {
                next_v = road[my_pos_dis + 1][my_pos_pas].getV();
                if(next_v+free_cell < v){
                if (pas < 1 && sensorl.CheckRoad(road, dys + 3, my_pos_pas, v, length)) {
                    pas = + 1;
                    is_safety = true;
                } else {
                    is_safety =false;
                    next_v = road[my_pos_dis + 1][my_pos_pas].getV();
                }
            }


            }else {
                is_safety = true;
            }
        }catch (Exception e){
            throw new CarFinish();
        }
    }


    public void makeMove(Cell_Road [][] my_road) throws CarFinish {
        checkRoad(my_road,dys,pas);
        move();
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
