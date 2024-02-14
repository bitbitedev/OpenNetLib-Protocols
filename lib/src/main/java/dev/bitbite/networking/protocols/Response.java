package dev.bitbite.networking.protocols;

import lombok.Getter;
import lombok.Setter;

public abstract class Response {
    
    @Getter @Setter private String clientAddress;
    @Getter @Setter private String data;

    public Response(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public abstract String toString();

}
