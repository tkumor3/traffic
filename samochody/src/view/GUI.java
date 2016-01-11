package view;


import Model.Connection;
import Model.Traffic;

import javax.swing.*;
import java.io.IOException;


/**
 * Created by Tomek on 20.07.15.
 */
public class GUI extends JFrame {
   Traffic traffic;
    CarPanel board;

    public GUI(){
        super("Autko");
        traffic = new Traffic();

        Thread g = new Thread(traffic);
        g.start();
        board = new CarPanel(traffic);

        add(board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(3000,100);
        setVisible(true);
    }
    static GUI ssp;

    public static void main(String[] args) throws Exception{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp = new GUI();

                }

        });

    }

}
