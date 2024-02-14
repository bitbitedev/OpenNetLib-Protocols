package dev.bitbite.networking.protocols.http;

import java.util.HashMap;

/**
 * The SessionStorage class provides methods for managing HTTP sessions.
 */
public class SessionStorage {

	private static HashMap<String, Session> sessions = new HashMap<String, Session>();

	/**
	 * Private constructor to prevent instantiation.
	 */
	private SessionStorage() {}

	/**
	 * Creates a new session and adds it to the session storage.
	 * 
	 * @return The newly created session.
	 */
	public static Session createSession() {
		var session = new Session();
		sessions.put(session.getId(), session);
		return session;
	}

	/**
	 * Creates a new session, associates it with the given request and response, and adds it to the session storage.
	 * 
	 * @param request  The HTTP request associated with the session.
	 * @param response The HTTP response associated with the session.
	 * @return The newly created session.
	 */
	public static Session createSession(HTTPRequest request, HTTPResponse response) {
		var session = new Session();
		sessions.put(session.getId(), session);
		request.setSession(session);
		response.addSession(session);
		return session;
	}

	/**
	 * Retrieves the session with the specified ID from the session storage.
	 * 
	 * @param id The ID of the session to retrieve.
	 * @return The session with the specified ID, or null if no session is found.
	 */
	public static Session get(String id) {
		return sessions.get(id);
	}

	/**
	 * Prints the IDs of all sessions in the session storage.
	 */
	public static void printSessionIDs() {
		System.out.println("SESSIONS:");
		for (var session : sessions.entrySet()) {
			System.out.println(session.getKey());
		}
	}

}
