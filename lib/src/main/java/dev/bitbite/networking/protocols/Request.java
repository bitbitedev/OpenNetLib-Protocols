package dev.bitbite.networking.protocols;

import lombok.Getter;

/**
 * Represents a request made by a client.
 */
public class Request {

    @Getter private String clientAddress;

    /**
     * Constructs a new Request object with the specified client address.
     * 
     * @param clientAddress the address of the client making the request
     */
    public Request(String clientAddress) {
        this.clientAddress = clientAddress;
    }
    
}
