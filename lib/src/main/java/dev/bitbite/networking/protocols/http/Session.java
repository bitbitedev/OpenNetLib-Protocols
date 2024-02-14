package dev.bitbite.networking.protocols.http;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;

import lombok.Getter;

public class Session {

	@Getter private String id;
	@Getter private HashMap<String,String> variables;
	
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
	
	public void store(String key, String value) {
		this.variables.put(key, value);
	}
	
	public String get(String key) {
		return this.variables.get(key);
	}
	
}
