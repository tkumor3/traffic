package Model;

/**
 * Created by Krzysiek on 2015-12-01.
 */
public class JsonUtils {
    public static class RoadNoCells{

        public int distance;
        public int pasy;
        public  RoadNoCells(Road road){
            distance =road.getDistance();
            pasy =road.getPasy();

        }

    }


}
