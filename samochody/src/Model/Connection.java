package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Krzysiek on 2015-11-16.
 */
public class Connection {
    public void sendRoad(String json) throws IOException {
        final String host = "192.168.0.24";
        final int portNumber = 8001;
        //System.out.println("Creating socket to '" + host + "' on port " + portNumber);

        while (true) {
            Socket socket = new Socket(host, portNumber);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(json);
            socket.close();

            if(true)
                return;

            if ("exit".equalsIgnoreCase(json)) {
                socket.close();
                break;
            }
        }
    }
    Socket carSocket = null;
    public void sendCarsTable(String carsTable) throws IOException{
        final String host = "192.168.0.24";

        final int portNumber = 8001;

        if(carSocket == null)
            carSocket = new Socket(host, portNumber);

        PrintWriter out = new PrintWriter(carSocket.getOutputStream(), true);
        out.println(carsTable);


        //carSocket.close();





    }



}
