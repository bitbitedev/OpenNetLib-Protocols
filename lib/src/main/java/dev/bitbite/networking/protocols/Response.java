package dev.bitbite.networking.protocols;

import lombok.Getter;
import lombok.Setter;

/**
 * The Response class represents a response from a server to a client in a networking protocol.
 */
public abstract class Response {
    
    @Getter @Setter private String clientAddress;
    @Getter @Setter private String data;

    /**
     * Constructs a new Response object with the specified client address.
     * 
     * @param clientAddress the address of the client
     */
    public Response(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    /**
     * Returns a string representation of the Response object.
     * 
     * @return a string representation of the Response object
     */
    @Override
    public abstract String toString();

}
