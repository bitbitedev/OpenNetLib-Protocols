package dev.bitbite.networking.protocols.http;

import java.util.ArrayList;
import java.util.HashMap;

import dev.bitbite.networking.protocols.Request;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an HTTP request.
 */
public class HTTPRequest extends Request {

	/**
	 * The request header.
	 */
	@Getter @Setter private String requestHeader;

	/**
	 * The session associated with the request.
	 */
	@Getter @Setter private Session session;

	/**
	 * The list of cookies sent with the request.
	 */
	@Getter private ArrayList<Cookie> cookies;

	/**
	 * The options specified in the request.
	 */
	@Getter private HashMap<String, String> options;

	/**
	 * The response headers to be sent back.
	 */
	@Getter private ArrayList<String> responseHeaders;

	/**
	 * Creates a new HTTPRequest object with the specified client address.
	 *
	 * @param clientAddress The client address.
	 */
	public HTTPRequest(String clientAddress) {
		super(clientAddress);
		this.cookies = new ArrayList<Cookie>();
		this.options = new HashMap<String, String>();
		this.responseHeaders = new ArrayList<String>();
	}

	/**
	 * Checks if the request is valid.
	 *
	 * @return true if the request is valid, false otherwise.
	 */
	public boolean isValid() {
		return requestHeader.startsWith("GET");
	}

	/**
	 * Adds an option to the request.
	 *
	 * @param content The option content.
	 */
	public void addOption(String content) {
		String[] parts = content.split(":");
		if (parts[0].startsWith("Cookie")) {
			this.cookies.addAll(Cookie.parse(parts[1]));
			if (this.cookies.size() > 0) {
				for (var cookie : cookies) {
					if (cookie.getName().contentEquals("SESSION_ID")) {
						this.session = SessionStorage.get(cookie.getValue());
						if (this.session == null) {
							responseHeaders.add("Set-Cookie: SESSION_ID=0; Max-Age=0");
						}
					}
				}
			}
		} else {
			if (parts.length == 1) {
				System.out.println(content);
			} else {
				this.options.put(parts[0], parts[1]);
			}
		}
	}

	/**
	 * Gets the HTTP method of the request.
	 *
	 * @return The HTTP method.
	 */
	public String getMethod() {
		return this.requestHeader.split(" ")[0];
	}

	/**
	 * Gets the requested path of the request.
	 *
	 * @return The requested path.
	 */
	public String getRequestedPath() {
		return this.requestHeader.split(" ")[1].substring(1);
	}

	/**
	 * Gets the protocol of the request.
	 *
	 * @return The protocol.
	 */
	public String getProtocol() {
		return this.requestHeader.split(" ")[2];
	}
}
