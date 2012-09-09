package com.xsloth.aku.network;

import com.xsloth.aku.db.hibernate.User;

public class NetworkData {
	
	private static User user;
	private static boolean logged=false;
	
	private static User opponent;

	/**
	 * Getters and Setters
	 */
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User u) {
		user = u;
	}

	public static boolean isLogged() {
		return logged;
	}

	public static void setLogged(boolean log) {
		logged = log;
	}

	public static User getOpponent() {
		return opponent;
	}

	public static void setOpponent(User opp) {
		opponent = opp;
	}
	
	

}
