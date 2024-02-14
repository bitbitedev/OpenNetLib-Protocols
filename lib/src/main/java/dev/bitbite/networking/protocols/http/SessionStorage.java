package dev.bitbite.networking.protocols.http;

import java.util.HashMap;

public class SessionStorage {

	private static HashMap<String, Session> sessions = new HashMap<String, Session>();
	
	public static Session createSession() {
		var session = new Session();
		sessions.put(session.getId(), session);
		return session;
	}
	
	public static Session createSession(HTTPRequest request, HTTPResponse response) {
		var session = new Session();
		sessions.put(session.getId(), session);
		request.setSession(session);
		response.addSession(session);
		return session;
	}
	
	public static Session get(String id) {
		return sessions.get(id);
	}
	
	public static void printSessionIDs() {
		System.out.println("SESSIONS:");
		for(var session : sessions.entrySet()) {
			System.out.println(session.getKey());
		}
	}
	
}
