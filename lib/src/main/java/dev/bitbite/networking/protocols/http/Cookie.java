package dev.bitbite.networking.protocols.http;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents an HTTP cookie.
 */
@AllArgsConstructor
public class Cookie {

	/**
	 * Enumeration for the SameSite attribute of the cookie.
	 */
	enum SameSite {
		Strict,
		Lax,
		None
	}
	
	@Getter @Setter private String name;
	@Getter @Setter private String value;
	@Getter private String expires;
	@Getter @Setter private int maxAge = -1;
	@Getter @Setter private String domain;
	@Getter @Setter private String path;
	@Getter @Setter private boolean secure;
	@Getter @Setter private boolean httpOnly;

	@Getter @Setter private SameSite sameSite;
	
	/**
	 * Creates a new cookie with the given name and value.
	 * @param name the name of the cookie
	 * @param value the value of the cookie
	 */
	public Cookie(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Creates a new cookie with the session ID.
	 * @param session the session object
	 */
	public Cookie(Session session) {
		this.name = "SESSION_ID";
		this.value = session.getId();
		this.secure = true;
		this.httpOnly = true;
		this.sameSite = SameSite.Strict;
	}
	
	/**
	 * Sets the expiration date of the cookie.
	 * @param date the expiration date
	 */
	public void setExpires(Date date) {
		var sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.expires = sdf.format(date).replace(".", "");
	}
	
	/**
	 * Parses the raw cookie string and returns a list of cookies.
	 * @param rawCookies the raw cookie string
	 * @return a list of parsed cookies
	 */
	public static ArrayList<Cookie> parse(String rawCookies){
		var cookies = new ArrayList<Cookie>();
		for(var cookie : rawCookies.split(";")) {
			cookie = cookie.trim();
			cookies.add(new Cookie(cookie.split("=")[0],cookie.split("=")[1]));
		}
		return cookies;
	}
	
	/**
	 * Returns a string representation of the Cookie object.
	 * The string representation includes the name, value, and optional attributes of the cookie.
	 * If the cookie has an expiration date, it includes the "Expires" attribute.
	 * If the cookie has a maximum age, it includes the "Max-Age" attribute.
	 * If the cookie has a domain, it includes the "Domain" attribute.
	 * If the cookie has a path, it includes the "Path" attribute.
	 * If the cookie is secure, it includes the "Secure" attribute.
	 * If the cookie is HTTP-only, it includes the "HttpOnly" attribute.
	 * If the cookie has a SameSite attribute, it includes the "SameSite" attribute.
	 * If the SameSite attribute is set to "None" and the cookie is not secure, it also includes the "Secure" attribute.
	 *
	 * @return a string representation of the Cookie object
	 */
	@Override
	public String toString() {
		String cookieString = name+"="+value+";";
		if(expires != null) {
			cookieString += " Expires="+expires+";";
		}
		if(maxAge >= 0) {
			cookieString += " Max-Age="+maxAge+";";
		}
		if(domain != null) {
			cookieString += " Domain="+domain+";";
		}
		if(path != null) {
			cookieString += " Path="+path+";";
		}
		if(secure) {
			cookieString += " Secure;";
		}
		if(httpOnly) {
			cookieString += " HttpOnly;";
		}
		if(sameSite != null) {
			cookieString += " SameSite="+sameSite+";";
			if(sameSite == SameSite.None && !secure) {
				cookieString += " Secure;";
			}
		}
		return cookieString.substring(0, cookieString.length()-1);
	}
	
}
