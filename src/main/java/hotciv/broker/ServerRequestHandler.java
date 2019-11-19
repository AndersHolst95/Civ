package hotciv.broker;
import com.google.gson.Gson;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;
import hotciv.broker.invokers.Invoker;
import hotciv.standard.GameImpl;
import hotciv.standard.factory.SemiFactory;
import hotciv.stub.ServerStub;
import java.net.*;
import java.io.*;

public class ServerRequestHandler {
    public static void main(String[] args) {
        int portNumber = 2800;
        Invoker invoker = new Invoker(new ServerStub());
        Gson gson = new Gson();

        try {
            while(true) {
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    RequestObject request = gson.fromJson(inputLine, RequestObject.class);
                    ReplyObject reply = invoker.handleRequest(request.getObjectId(), request.getOperationName(), request.getPayload());
                    out.println(gson.toJson(reply));
                }
                // Closing the streams and the server socket
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("I/O exception on the server side...");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
