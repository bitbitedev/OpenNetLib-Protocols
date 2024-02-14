package dev.bitbite.networking.protocols.http;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Cookie {

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
	
	public Cookie(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public Cookie(Session session) {
		this.name = "SESSION_ID";
		this.value = session.getId();
		this.secure = true;
		this.httpOnly = true;
		this.sameSite = SameSite.Strict;
	}
	
	public void setExpires(Date date) {
		var sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.expires = sdf.format(date).replace(".", "");
	}
	
	public static ArrayList<Cookie> parse(String rawCookies){
		var cookies = new ArrayList<Cookie>();
		for(var cookie : rawCookies.split(";")) {
			cookie = cookie.trim();
			cookies.add(new Cookie(cookie.split("=")[0],cookie.split("=")[1]));
		}
		return cookies;
	}
	
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
