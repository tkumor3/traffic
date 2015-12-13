package Model;

/**
 * Created by Tomek on 16.10.15.
 */
public class Road {
    private Cell_Road[][] roads;
    private int distance;
    private int pasy;
    public Road(int dis, int pas) {
        distance = dis;
        pasy = pas;
        roads = new Cell_Road[dis][pas];
        for (int i = 0; i < dis; i++) {
            for (int j = 0; j < pas; j++) {
                roads[i][j] = new Cell_Road();

            }

        }


    }
    public Cell_Road[][] getRoads() {
        return roads;
    }
    /*
    czyszczenie tablicy !!! zobaczymy jak bedzoe obciazało pamiec
     */
    public void reset() {
        for (int i = 0; i < distance; i++) {
            for (int j = 0; j < pasy; j++) {
                if(!(roads[i][j].getType() == (TypeOfCell.RedLIGHTS) || (roads[i][j].getType() ==TypeOfCell.GreenLIGHTS))) {
                    setEmpty(i, j);
                }
            }
        }
    }
    public void setCar(int dis, int pas,int v, int lenght) {
        for (int i = 0; i < lenght; i++) {



                roads[dis + i][pas].setCar(v);


        }

    }


    public void setEmpty(int dis, int pas){
        roads[dis][pas].setEmpty();
    }
    public void setRedLights(int dis, int pas){
        roads[dis][pas].setRedLights();
    }
    public void setGreenLights(int dis, int pas){
        roads[dis][pas].setGreenLights();
    }
    public int getDistance() {
        return distance;
    }
    public int getPasy() {
        return pasy;
    }


}
