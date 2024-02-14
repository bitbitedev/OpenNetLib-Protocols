package dev.bitbite.networking.protocols;

import dev.bitbite.networking.protocols.http.HTTPResponse;

public abstract class Server extends dev.bitbite.networking.Server {

    private Protocol protocol;

    public Server(Class<? extends Protocol> protocol, int port) {
        super(port);
        try {
            this.protocol = protocol.getConstructor(Server.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
        }
    }

    public abstract Response handleRequest(Request request, Response response);

    @Override
    protected void processReceivedData(String clientAddress, byte[] data) {
        this.protocol.acceptMessage(clientAddress, data);
    }

    public void sendResponse(HTTPResponse response) {
        this.send(response.getClientAddress(), response.toString().getBytes());
    }
    
}
