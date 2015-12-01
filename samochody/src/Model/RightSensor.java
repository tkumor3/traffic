package Model;

/**
 * Created by Tomek on 23.11.15.
 */
public class RightSensor implements Sensor  {
    @Override
    public boolean CheckRoad(Cell_Road[][] myRoad, int dis, int pas, int v) throws CarFinish {
        try {


            Cell_Road pom;
            if (dis > 10) {
                for (int j = dis - 10; j < dis + v + 2; j++) {
                    pom = myRoad[j][pas - 1];
                    if (pom.is_car() && (j - dis) + pom.getV() < v) {
                        return false;
                    }
                }
                return true;
            } else {

                return false;


            }
        }catch (Exception e){
            throw new CarFinish();
        }
    }
}
