package dev.bitbite.networking.protocols.http;

import java.util.HashMap;

import lombok.Getter;

/**
 * The RequestManager class manages HTTP requests from clients.
 */
public class RequestManager {

	@Getter private HashMap<String, HTTPRequest> pendingRequests;

	/**
	 * Constructs a new RequestManager object.
	 */
	public RequestManager() {
		pendingRequests = new HashMap<String, HTTPRequest>();
	}
	
	/**
	 * Creates a new HTTPRequest object and adds it to the pending requests.
	 * 
	 * @param clientAddress The address of the client making the request.
	 * @param requestHeader The header of the HTTP request.
	 * @return The created HTTPRequest object if it is valid and added to the pending requests, null otherwise.
	 */
	public HTTPRequest createRequest(String clientAddress, String requestHeader) {
		if(getActiveRequest(clientAddress) == null) {
			HTTPRequest request = new HTTPRequest(clientAddress);
			request.setRequestHeader(requestHeader);
			if(request.isValid()) {
				pendingRequests.put(clientAddress, request);
				return request;
			}
		}
		return null;
	}
	
	/**
	 * Finalizes the HTTPRequest for the specified client address and removes it from the pending requests.
	 * 
	 * @param clientAddress The address of the client for which to finalize the request.
	 * @return The finalized HTTPRequest object if it exists in the pending requests, null otherwise.
	 */
	public HTTPRequest finalizeRequest(String clientAddress) {
		HTTPRequest request;
		if((request = getActiveRequest(clientAddress)) != null) {
			pendingRequests.remove(clientAddress);
		}
		return request;
	}
	
	/**
	 * Retrieves the active HTTPRequest for the specified client address.
	 * 
	 * @param clientAddress The address of the client for which to retrieve the active request.
	 * @return The active HTTPRequest object if it exists in the pending requests, null otherwise.
	 */
	public HTTPRequest getActiveRequest(String clientAddress) {
		return pendingRequests.get(clientAddress);
	}
	
}
