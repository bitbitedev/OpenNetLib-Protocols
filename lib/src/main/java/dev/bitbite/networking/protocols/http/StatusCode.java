package dev.bitbite.networking.protocols.http;

/**
 * Enumeration representing HTTP status codes.
 */
public enum StatusCode {

	/**
	 * HTTP status code 100: Continue.
	 */
	_100(100, "Continue"),

	/**
	 * HTTP status code 101: Switching Protocols.
	 */
	_101(101, "Switching Protocols"),

	/**
	 * HTTP status code 200: OK.
	 */
	_200(200, "OK"),

	/**
	 * HTTP status code 201: Created.
	 */
	_201(201, "Created"),

	/**
	 * HTTP status code 202: Accepted.
	 */
	_202(202, "Accepted"),

	/**
	 * HTTP status code 203: Non-Authoritative Information.
	 */
	_203(203, "Non-Authoritative Information"),

	/**
	 * HTTP status code 204: No Content.
	 */
	_204(204, "No Content"),

	/**
	 * HTTP status code 205: Reset Content.
	 */
	_205(205, "Reset Content"),

	/**
	 * HTTP status code 206: Partial Content.
	 */
	_206(206, "Partial Content"),

	/**
	 * HTTP status code 300: Multiple Choices.
	 */
	_300(300, "Multiple Choices"),

	/**
	 * HTTP status code 301: Moved Permanently.
	 */
	_301(301, "Moved Permanently"),

	/**
	 * HTTP status code 302: Found.
	 */
	_302(302, "Found"),

	/**
	 * HTTP status code 303: See Other.
	 */
	_303(303, "See Other"),

	/**
	 * HTTP status code 304: Not Modified.
	 */
	_304(304, "Not Modified"),

	/**
	 * HTTP status code 305: Use Proxy.
	 */
	_305(305, "Use Proxy"),

	/**
	 * HTTP status code 307: Temporary Redirect.
	 */
	_307(307, "Temporary Redirect"),

	/**
	 * HTTP status code 400: Bad Request.
	 */
	_400(400, "Bad Request"),

	/**
	 * HTTP status code 401: Unauthorized.
	 */
	_401(401, "Unauthorized"),

	/**
	 * HTTP status code 402: Payment Required.
	 */
	_402(402, "Payment Required"),

	/**
	 * HTTP status code 403: Forbidden.
	 */
	_403(403, "Forbidden"),

	/**
	 * HTTP status code 404: Not Found.
	 */
	_404(404, "Not Found"),

	/**
	 * HTTP status code 405: Method Not Allowed.
	 */
	_405(405, "Method Not Allowed"),

	/**
	 * HTTP status code 406: Not Acceptable.
	 */
	_406(406, "Not Acceptable"),

	/**
	 * HTTP status code 407: Proxy Authentication Required.
	 */
	_407(407, "Proxy Authentication Required"),

	/**
	 * HTTP status code 408: Request Time-out.
	 */
	_408(408, "Request Time-out"),

	/**
	 * HTTP status code 409: Conflict.
	 */
	_409(409, "Conflict"),

	/**
	 * HTTP status code 410: Gone.
	 */
	_410(410, "Gone"),

	/**
	 * HTTP status code 411: Length Required.
	 */
	_411(411, "Length Required"),

	/**
	 * HTTP status code 412: Precondition Failed.
	 */
	_412(412, "Precondition Failed"),

	/**
	 * HTTP status code 413: Request Entity Too Large.
	 */
	_413(413, "Request Entity Too Large"),

	/**
	 * HTTP status code 414: Request URI Too Large.
	 */
	_414(414, "Request URI Too Large"),

	/**
	 * HTTP status code 415: Unsupported Media Type.
	 */
	_415(415, "Unsupported Media Type"),

	/**
	 * HTTP status code 416: Request range not satisfiable.
	 */
	_416(416, "Request range not satisfiable"),

	/**
	 * HTTP status code 417: Expectation Failed.
	 */
	_417(417, "Expectation Failed"),

	/**
	 * HTTP status code 500: Internal Server Error.
	 */
	_500(500, "Internal Server Error"),

	/**
	 * HTTP status code 501: Not Implemented.
	 */
	_501(501, "Not Implemented"),

	/**
	 * HTTP status code 502: Bad Gateway.
	 */
	_502(502, "Bad Gateway"),

	/**
	 * HTTP status code 503: Service Unavailable.
	 */
	_503(503, "Service Unavailable"),

	/**
	 * HTTP status code 504: Gateway Time-out.
	 */
	_504(504, "Gateway Time-out"),

	/**
	 * HTTP status code 505: HTTP Version not supported.
	 */
	_505(505, "HTTP Version not supported");

	private int code;
	private String phrase;

	/**
	 * Constructs a StatusCode enum value with the given code and phrase.
	 *
	 * @param code   the HTTP status code
	 * @param phrase the HTTP status phrase
	 */
	StatusCode(int code, String phrase) {
		this.code = code;
		this.phrase = phrase;
	}

	/**
	 * Returns the HTTP status code.
	 *
	 * @return the HTTP status code
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * Returns the HTTP status phrase.
	 *
	 * @return the HTTP status phrase
	 */
	public String getPhrase() {
		return this.phrase;
	}
}
