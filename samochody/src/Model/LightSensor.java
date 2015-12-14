package Model;

/**
 * Created by Tomek on 08.12.15.
 */
public class LightSensor implements Sensor {

    @Override
    public boolean CheckRoad(Cell_Road[][] myRoad, int dis, int pas, int v, int lenght) throws CarFinish {
        if (myRoad[dis][pas].getType() == TypeOfCell.GreenLIGHTS) {
            return true;

        }
        return true;

    }

}
