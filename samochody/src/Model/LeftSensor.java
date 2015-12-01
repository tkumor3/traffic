package Model;

/**
 * Created by Tomek on 22.11.15.
 */
public class LeftSensor implements Sensor {
    @Override

    public boolean CheckRoad(Cell_Road[][] myRoad, int dis, int pas, int v) throws CarFinish {
        Cell_Road pom;
        try {
            if (dis > 7+5 ) {
                for (int i = dis - (7+5); i < dis + 1; i++) {
                    pom = myRoad[i][pas + 1];
                    if (pom.is_car() && ((dis - i) + (v - pom.getV())) < 0) {
                        return false;
                    }
                }


                for (int j = dis; j < dis + v; j++) {
                    pom = myRoad[j][pas + 1];
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
