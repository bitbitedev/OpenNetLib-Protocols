package dev.bitbite.networking.protocols.http;

import dev.bitbite.networking.ServerListener;
import dev.bitbite.networking.protocols.Protocol;
import dev.bitbite.networking.protocols.Server;

import java.nio.charset.StandardCharsets;

public class HTTP extends Protocol {

    private RequestManager requestManager;

    public HTTP(Server server) {
        super(server);
        this.requestManager = new RequestManager();
        server.registerListener(new ServerListener() {
			@Override
			public void onSocketClosed(Exception exception, String clientAddress) {
				requestManager.finalizeRequest(clientAddress);
			}
		});
    }

    @Override
    public void acceptMessage(String clientAddress, byte[] rawData) {
        String data = new String(rawData, StandardCharsets.UTF_8);
		try {
			HTTPRequest request;
			if(rawData.length <= 1) {
				request = requestManager.finalizeRequest(clientAddress);
				HTTPResponse response = (HTTPResponse) this.getServer().handleRequest(request, new HTTPResponse(request));
				if(response != null){
					this.getServer().send(clientAddress, response.toString().getBytes(StandardCharsets.UTF_8));
				}
			} else {
				if((request = requestManager.getActiveRequest(clientAddress)) != null) {
					request.addOption(data);
				} else {
					requestManager.createRequest(clientAddress, data);
				}
			}
		} catch(Exception e) {
			HTTPResponse response = new HTTPResponse(clientAddress, e);
            if(response != null) {
                this.getServer().send(clientAddress, response.toString().getBytes(StandardCharsets.UTF_8));
            }
            e.printStackTrace();
		}
    }
    
}
