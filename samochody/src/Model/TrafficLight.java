package Model;

/**
 * Created by Tomek on 26.10.2015.
 */
public class TrafficLight {
    private int dist;
    private int time_red;
    private int time_green;
    private boolean can_go;
    private int time_to_change;

    public TrafficLight(int dist,int time_red,int time_green){
        this.dist = dist;
        this.time_red = time_red;
        this.time_green = time_green;
        can_go = true;
        time_to_change = time_green;
    }

    public boolean timer(){
        time_to_change --;
        if(time_to_change == 0){
            return true;
        }else{
            return false;
        }

    }


    public void driver(Road road){
        if(timer()){
            can_go = !can_go;
            if(can_go){
               time_to_change = time_green;
                for (int i = 0; i < road.getPasy(); i++) {
                    road.setGreenLights(dist,i);
                }

            }else {
                time_to_change = time_red;
                for (int i = 0; i < road.getPasy(); i++) {
                    road.setRedLights(dist,i);
                }
            }




        }
    }





}
