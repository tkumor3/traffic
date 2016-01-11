package Model;

/**
 * Created by Tomek on 22.11.15.
 */
public class LeftSensor implements Sensor {
    @Override

    public boolean CheckRoad(Cell_Road[][] myRoad, int dis, int pas, int v, int lenght) {
        Cell_Road pom;
        int disp = dis - 15 - lenght;
            /*
            nie zajezdza drogi
             */
            if (disp > 0 ) {
                for (int i = disp ; i < dis + lenght; i++) {
                    pom = myRoad[i][pas + 1];
                    if (pom.is_car() && ((dis - lenght - i+ v - pom.getV())-1) < 0) {
                        return false;
                    }
                }
                /*
                sprawdza czy moze wyprzedzić
               */

                for (int j = dis; j < dis + v+1; j++) {
                    pom = myRoad[j][pas + 1];
                    if ((pom.is_car() && (j+pom.getV() - dis + v) > 1  || pom.is_car() && (dis > j)) ) {
                        return false;
                    }
                }

                return true;

            } else {

                return false;

            }
    }
}
