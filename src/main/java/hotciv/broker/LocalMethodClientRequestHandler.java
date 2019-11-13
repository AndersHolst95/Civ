package hotciv.broker;

import frds.broker.ClientRequestHandler;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;

public class LocalMethodClientRequestHandler implements ClientRequestHandler {
    private final Invoker invoker;

    public LocalMethodClientRequestHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    public ReplyObject sendToServer(RequestObject requestObject) {
        System.out.println("--> " + requestObject);
        ReplyObject reply = invoker.handleRequest(requestObject.getObjectId(), requestObject.getOperationName(),
                requestObject.getPayload());
        return reply;
    }

    public void setServer(String hostname, int port) {

    }

    public void close() {

    }
}
