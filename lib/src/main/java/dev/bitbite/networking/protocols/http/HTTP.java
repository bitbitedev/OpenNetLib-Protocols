package dev.bitbite.networking.protocols.http;

import dev.bitbite.networking.ServerListener;
import dev.bitbite.networking.protocols.Protocol;
import dev.bitbite.networking.protocols.Server;

import java.nio.charset.StandardCharsets;

/**
 * The HTTP class represents the HTTP protocol implementation.
 * It extends the Protocol class and handles incoming HTTP messages.
 */
public class HTTP extends Protocol {

    private RequestManager requestManager;

	/**
	 * Represents an HTTP protocol implementation.
	 * This class extends the base Protocol class and provides functionality specific to the HTTP protocol.
	 * It manages the request handling and response generation for HTTP requests.
	 *
	 * @param server The server instance associated with this HTTP protocol.
	 */
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

	/**
	 * Accepts a message from a client and processes it accordingly.
	 * If the message length is less than or equal to 1, it finalizes the request,
	 * generates a response, and sends it back to the client.
	 * If the message length is greater than 1, it adds the message as an option to
	 * the active request if it exists, otherwise it creates a new request with the message.
	 * If an exception occurs during processing, it generates an error response and sends it back to the client.
	 *
	 * @param clientAddress The address of the client sending the message.
	 * @param rawData The raw data of the message.
	 */
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
