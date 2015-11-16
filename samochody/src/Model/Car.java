package Model;

import java.util.Random;

/**
 * Created by Tomek on 16.10.15.
 */
public class Car {
    private int pas, dys;
    private int pas_l,dys_l;
    private int v, vmax;
    private boolean is_safety;
    private int free_cell;
    private int next_v;


    Car() {
        pas = 0;
        dys = 0;
        Random generator = new Random();
        vmax = 6;
        int i = generator.nextInt(6) + 1;
        v = i;

    }

    /**
     * przyspieszenie naszego pojazdu ale tylko jesli jest to bezpieczne
     */
    private void acceletion() {
        if (!is_safety) {
            v -= 1;
        } else {
            if (!(v == vmax)) {
                v += 1;
            }
        }
    }
    /*
        jesli jest mozliwość to jade swoja prędkośćia w przeciwnym razie podjeżdza pod drugi podjazd i przejmuje jego prędkość
     */

    private void move() {

        if(v-(next_v+free_cell)<0) {
            dys += v;
            is_safety = true;
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

        free_cell = 0;
        try {
            while (!(road[my_pos_dis + 1][my_pos_pas].is_car()) && free_cell < v+1) {

                my_pos_dis += 1;

                free_cell += 1;

            }
            if (free_cell < 16)
                next_v = road[my_pos_dis+1][my_pos_pas ].getV();

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
}
