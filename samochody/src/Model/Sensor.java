package Model;

/**
 * Created by Tomek on 22.11.15.
 */

public interface Sensor {
     public boolean CheckRoad(Cell_Road[][] myRoad, int dis, int pas, int v,int lenght) throws CarFinish;
}
