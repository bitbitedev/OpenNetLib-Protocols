package dev.bitbite.networking.protocols.http;

import java.util.ArrayList;

import dev.bitbite.networking.protocols.Response;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an HTTP response.
 */
public class HTTPResponse extends Response {

	/**
	 * The HTTP request associated with this response.
	 */
	@Getter private final HTTPRequest request;

	/**
	 * The status code of the response.
	 */
	@Getter @Setter private StatusCode status;

	/**
	 * The header of the response.
	 */
	@Getter private String header;

	/**
	 * The content type of the response.
	 */
	@Getter private String contentType = "text/html";

	/**
	 * The character encoding of the response.
	 */
	@Getter private String charset = "utf-8";

	/**
	 * Additional headers of the response.
	 */
	private ArrayList<String> additionalHeaders;

	/**
	 * Constructs an HTTPResponse object with the given HTTPRequest.
	 *
	 * @param request The HTTPRequest associated with this response.
	 */
	public HTTPResponse(HTTPRequest request) {
		super(request.getClientAddress());
		this.request = request;
		this.additionalHeaders = new ArrayList<String>();
		this.additionalHeaders.addAll(request.getResponseHeaders());
	}

	/**
	 * Constructs an HTTPResponse object with the given client address and exception.
	 *
	 * @param clientAddress The client address associated with this response.
	 * @param e The exception to be included in the response.
	 */
	public HTTPResponse(String clientAddress, Exception e) {
		super(clientAddress);
		this.request = null;
		this.status = StatusCode._500;
		this.setData(e.getMessage());
		this.additionalHeaders = new ArrayList<String>();
	}

	/**
	 * Builds the header of the response.
	 */
	private void buildHeader() {
		this.header = "HTTP/1.1 " + this.status.getCode() + " " + this.status.getPhrase() + "\r\n" +
				"Content-Length: " + this.getData().length() + "\r\n" +
				"Content-Type: " + this.contentType + "; charset=" + this.charset + "\r\n";
		additionalHeaders.forEach(h -> this.header += h + "\r\n");
	}

	/**
	 * Returns a string representation of the HTTPResponse object.
	 *
	 * @return The string representation of the HTTPResponse object.
	 */
	@Override
	public String toString() {
		buildHeader();
		return header + "\r\n" + this.getData();
	}

	/**
	 * Adds a session to the response by adding a cookie.
	 *
	 * @param session The session to be added.
	 */
	public void addSession(Session session) {
		if (session == null) {
			return;
		}
		addCookie(new Cookie(session));
	}

	/**
	 * Adds a cookie to the response.
	 *
	 * @param cookie The cookie to be added.
	 */
	public void addCookie(Cookie cookie) {
		if (cookie == null) {
			return;
		}
		this.addHeader("Set-Cookie: " + cookie.toString());
	}

	/**
	 * Adds a header to the response.
	 *
	 * @param header The header to be added.
	 */
	public void addHeader(String header) {
		if (header == null) {
			return;
		}
		this.additionalHeaders.add(header);
	}

}
