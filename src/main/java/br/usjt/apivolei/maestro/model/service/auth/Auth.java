package br.usjt.apivolei.maestro.model.service.auth;

import java.util.HashMap;
import java.util.Map;

public class Auth {
	
	private static Map<String, Long> tokens;
	static {
		tokens = new HashMap<String, Long>();
	}
	
	public static boolean isValidToken(String token) {
		return tokens.containsKey(token);
	}
	
	public static boolean containsValue(Long id) {
		return tokens.containsValue(id);
	}
	
	public static Long getId(String token) {
		return tokens.get(token);
	}
	
	public static void putToken(String token, Long id) {
		tokens.put(token, id);
	}
	
	public static void removeToken(String token) {
		tokens.remove(token);
	}

}