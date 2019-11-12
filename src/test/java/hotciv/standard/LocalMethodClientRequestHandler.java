package hotciv.standard;

import frds.broker.ClientRequestHandler;
import frds.broker.ReplyObject;
import frds.broker.RequestObject;

public class LocalMethodClientRequestHandler implements ClientRequestHandler {
    public LocalMethodClientRequestHandler(Invoker invoker) {
    }

    @Override
    public ReplyObject sendToServer(RequestObject requestObject) {
        return null;
    }

    @Override
    public void setServer(String hostname, int port) {

    }

    @Override
    public void close() {

    }
}
