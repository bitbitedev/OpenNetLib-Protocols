package dev.bitbite.networking.protocols.http;

import java.util.HashMap;

import lombok.Getter;

public class RequestManager {

	@Getter private HashMap<String, HTTPRequest> pendingRequests;

	public RequestManager() {
		pendingRequests = new HashMap<String, HTTPRequest>();
	}
	
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
	
	public HTTPRequest finalizeRequest(String clientAddress) {
		HTTPRequest request;
		if((request = getActiveRequest(clientAddress)) != null) {
			pendingRequests.remove(clientAddress);
		}
		return request;
	}
	
	public HTTPRequest getActiveRequest(String clientAddress) {
		return pendingRequests.get(clientAddress);
	}
	
}
