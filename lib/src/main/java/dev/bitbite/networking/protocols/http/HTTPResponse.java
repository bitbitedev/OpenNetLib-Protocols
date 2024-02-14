package dev.bitbite.networking.protocols.http;

import java.util.ArrayList;

import dev.bitbite.networking.protocols.Response;
import lombok.Getter;
import lombok.Setter;

public class HTTPResponse extends Response {

	@Getter private final HTTPRequest request;
	@Getter @Setter private StatusCode status;
	@Getter private String header;
	@Getter private String contentType = "text/html";
	@Getter private String charset = "utf-8";
	private ArrayList<String> additionalHeaders;
	
	public HTTPResponse(HTTPRequest request) {
		super(request.getClientAddress());
		this.request = request;
		this.additionalHeaders = new ArrayList<String>();
		this.additionalHeaders.addAll(request.getResponseHeaders());
	}
	
	public HTTPResponse(String clientAddress, Exception e) {
		super(clientAddress);
		this.request = null;
		this.status = StatusCode._500;
		this.setData(e.getMessage());
		this.additionalHeaders = new ArrayList<String>();
	}

	private void buildHeader() {
		this.header = "HTTP/1.1 "+this.status.getCode()+" "+this.status.getPhrase()+"\r\n" +
				"Content-Length: "+this.getData().length()+"\r\n" + 
				"Content-Type: "+this.contentType+"; charset="+this.charset+"\r\n";
		additionalHeaders.forEach(h -> this.header += h+"\r\n");
	}
	
	@Override
	public String toString() {
		buildHeader();
		return header + "\r\n" + this.getData();
	}
	
	public void addSession(Session session) {
		if(session == null) {
			return;
		}
		addCookie(new Cookie(session));
	}
	
	public void addCookie(Cookie cookie) {
		if(cookie == null) {
			return;
		}
		this.addHeader("Set-Cookie: "+cookie.toString());
	}
	
	public void addHeader(String header) {
		if(header == null) {
			return;
		}
		this.additionalHeaders.add(header);
	}

}
