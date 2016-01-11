package Model;

/**
 * Created by Tomek on 16.10.15.
 */
public class CarFinish extends Exception {
    public static int count = 0;

    public CarFinish(boolean shouldCount){
        if(shouldCount)
            CarFinish.count++;
    }
}
