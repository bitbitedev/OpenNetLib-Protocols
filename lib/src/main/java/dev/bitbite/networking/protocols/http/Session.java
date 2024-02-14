package dev.bitbite.networking.protocols.http;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

import lombok.Getter;

/**
 * Represents a session in an HTTP protocol.
 */
public class Session {

	@Getter private String id;
	@Getter private HashMap<String,String> variables;
	
	/**
	 * Constructs a new Session object with a unique ID and an empty variables map.
	 */
	public Session() {
		long counter = new SecureRandom().nextLong();
		String text = ""+counter+System.nanoTime();
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			this.id = Base64.getEncoder().encodeToString(digest.digest(text.getBytes(StandardCharsets.UTF_8))).replace("=", "");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		this.variables = new HashMap<String,String>();
	}
	
	/**
	 * Stores a key-value pair in the session's variables map.
	 * 
	 * @param key the key to store
	 * @param value the value to store
	 */
	public void store(String key, String value) {
		this.variables.put(key, value);
	}
	
	/**
	 * Retrieves the value associated with the given key from the session's variables map.
	 * 
	 * @param key the key to retrieve
	 * @return the value associated with the key, or null if the key is not found
	 */
	public String get(String key) {
		return this.variables.get(key);
	}
	
}
