package view;
import Model.Traffic;
import Model.TypeOfCell;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomek on 16.10.15.
 */
public class CarPanel extends JPanel implements Runnable {
   Traffic traffic; 

        public CarPanel(Traffic game){
            this.traffic = game;
            Thread watek = new Thread(this);
            watek.start();


        }


        public void setBoard(Traffic game){
            this.traffic = game;
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            for (int i = 0; i < traffic.getRoad().getDistance(); i++) {
                for (int j = 0; j < traffic.getRoad().getPasy(); j++) {
                    if(traffic.getRoad().getRoads()[i][j].is_car()){
                        if(traffic.getRoad().getRoads()[i][j].getIs_car() == TypeOfCell.CAR) {
                            g.setColor(Color.green);
                            g.drawRect(i * 5, j, 5, 20);
                        }else {
                            g.setColor(Color.red);
                            g.drawRect(i * 5, j, 5, 20);
                        }

                    }else {
                        g.setColor(Color.gray);
                        g.fillRect(i*5,j,5,20);


                    }

                }
                System.out.println("");

            }


        }

        @Override

        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                repaint();
            }
            }
        }




