package dev.bitbite.networking.protocols;

import lombok.Getter;

public class Request {

    @Getter private String clientAddress;

    public Request(String clientAddress) {
        this.clientAddress = clientAddress;
    }
    
}
