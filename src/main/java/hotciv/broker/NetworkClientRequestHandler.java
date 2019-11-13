package hotciv.broker;

import com.google.gson.Gson;
import frds.broker.ClientRequestHandler;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkClientRequestHandler implements ClientRequestHandler {
    private Gson gson = new Gson();
    private Socket socket;
    private PrintWriter out;
    private BufferedReader serverIn;


    public NetworkClientRequestHandler(String ip) {
        setServer(ip, 2800);
    }

    public ReplyObject sendToServer(RequestObject requestObject) {
        String payload = gson.toJson(requestObject);
        out.println(payload);
        String inputLine;
        try {
            inputLine = serverIn.readLine();
            return gson.fromJson(inputLine, ReplyObject.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("We return null in requesthandler");
        return null;
    }

    public void setServer(String hostname, int port) {
        try {
            socket = new Socket(hostname, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {

    }
}
