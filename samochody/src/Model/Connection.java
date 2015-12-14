package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by Krzysiek on 2015-11-16.
 */
public class Connection {

    Socket socket = null;
    PrintWriter out;
    public void sendRoad(String json) throws IOException {
        final String host = "172.20.10.3";
        final int portNumber = 8001;

        socket = new Socket(host, portNumber);
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(json);

    }

    public void sendSth(String carsTable) throws IOException{

        out.println(carsTable);

    }






}
