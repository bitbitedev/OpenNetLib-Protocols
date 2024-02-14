package dev.bitbite.networking.protocols.http;

import java.util.ArrayList;
import java.util.HashMap;

import dev.bitbite.networking.protocols.Request;
import lombok.Getter;
import lombok.Setter;

public class HTTPRequest extends Request {

	@Getter @Setter private String requestHeader;
	@Getter @Setter private Session session;
	@Getter private ArrayList<Cookie> cookies;
	@Getter private HashMap<String, String> options;
	@Getter private ArrayList<String> responseHeaders;
	
	public HTTPRequest(String clientAddress) {
        super(clientAddress);
		this.cookies = new ArrayList<Cookie>();
		this.options = new HashMap<String, String>();
		this.responseHeaders = new ArrayList<String>();
	}
	
	public boolean isValid() {
		return requestHeader.startsWith("GET");
	}
	
	public void addOption(String content) {
		String[] parts = content.split(":");
		//check if request contains cookies
		if(parts[0].startsWith("Cookie")) { 
			this.cookies.addAll(Cookie.parse(parts[1]));
			if(this.cookies.size() > 0) {
				//looking for active sessions
				for(var cookie : cookies) { 
					if(cookie.getName().contentEquals("SESSION_ID")) {
						// looking if session exists on serverside
						this.session = SessionStorage.get(cookie.getValue()); 
						if(this.session == null) {
							//setting http header to delete the session id cookie on clientside
							responseHeaders.add("Set-Cookie: SESSION_ID=0; Max-Age=0"); 
						}
					}
				}
			}
		} else {
			if(parts.length == 1) {
				System.out.println(content);
			} else {
				this.options.put(parts[0], parts[1]);
			}
		}
	}

	public String getMethod() {
		return this.requestHeader.split(" ")[0];
	}
	
	public String getRequestedPath() {
		return this.requestHeader.split(" ")[1].substring(1);
	}
	
	public String getProtocoll() {
		return this.requestHeader.split(" ")[2];
	}
	
}
