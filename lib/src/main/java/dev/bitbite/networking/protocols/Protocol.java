package dev.bitbite.networking.protocols;

import dev.bitbite.networking.protocols.http.HTTPResponse;
import lombok.Getter;

/**
 * The base class for all protocols in the networking library.
 */
public abstract class Protocol {

    @Getter private Server server;

    /**
     * Constructs a Protocol object with the specified server.
     * 
     * @param server the server associated with this protocol
     */
    public Protocol(Server server) {
        this.server = server;
    }
    
    /**
     * Accepts a message from a client and processes it.
     * 
     * @param clientAddress the address of the client
     * @param data the message data
     */
    public abstract void acceptMessage(String clientAddress, byte[] data);

    /**
     * Sends a response to the client.
     * 
     * @param response the HTTP response to send
     */
    public void sendResponse(HTTPResponse response) {
        this.server.send(response.getClientAddress(), response.toString().getBytes());
    }

}
