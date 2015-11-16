package Model;

/**
 * Created by Tomek on 16.10.15.
 */
public class Test {
    public static void main(String[] args) {
        Traffic t = new Traffic();
        t.simulation();
        t.printRoad();
        System.out.println("");
        t.simulation();
        t.printRoad();
    }
}
