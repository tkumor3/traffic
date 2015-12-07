package Model;

/**
 * Created by Tomek on 23.11.15.
 */
public class RightSensor implements Sensor  {
    @Override
    public boolean CheckRoad(Cell_Road[][] myRoad, int dis, int pas, int v, int lenght) throws CarFinish {
        try {
            Cell_Road pom;

                for (int j = dis - lenght; j < dis + v; j++) {
                    pom = myRoad[j][pas - 1];
                    if (pom.is_car() && (j+pom.getV() - dis + v) > 1  || pom.is_car() && (dis > j)) {
                        return false;
                    }
                }
                return true;

        }catch (Exception e){
            throw new CarFinish();
        }
    }
}
