package dev.bitbite.networking.protocols;

import dev.bitbite.networking.protocols.http.HTTPResponse;
import lombok.Getter;

public abstract class Protocol {

    @Getter private Server server;

    public Protocol(Server server) {
        this.server = server;
    }
    
    public abstract void acceptMessage(String clientAddress, byte[] data);

    public void sendResponse(HTTPResponse response) {
        this.server.send(response.getClientAddress(), response.toString().getBytes());
    }

}
