package dev.bitbite.networking.protocols;

import dev.bitbite.networking.protocols.http.HTTPResponse;

/**
 * The abstract Server class represents a server that can handle incoming requests and send responses.
 * It extends the dev.bitbite.networking.Server class.
 */
public abstract class Server extends dev.bitbite.networking.Server {

    private Protocol protocol;

    /**
     * Constructs a Server object with the specified protocol and port.
     * 
     * @param protocol the protocol class to be used by the server
     * @param port the port number on which the server will listen for incoming connections
     */
    public Server(Class<? extends Protocol> protocol, int port) {
        super(port);
        try {
            this.protocol = protocol.getConstructor(Server.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
            this.close();
        }
    }

    /**
     * Handles an incoming request and returns the corresponding response.
     * 
     * @param request the incoming request
     * @param response the response object to be populated
     * @return the response to the incoming request
     */
    public abstract Response handleRequest(Request request, Response response);

    @Override
    protected void processReceivedData(String clientAddress, byte[] data) {
        this.protocol.acceptMessage(clientAddress, data);
    }

    /**
     * Sends an HTTP response to the specified client.
     * 
     * @param response the HTTP response to be sent
     */
    public void sendResponse(HTTPResponse response) {
        this.send(response.getClientAddress(), response.toString().getBytes());
    }
    
}
