package com.xsloth.aku.util;

public class GamePreferences {
	public static int resX, resY;
	public static boolean fullScreenMode;
	
	
	/**
	 * Getters and Setters
	 */
	public static int getResX() {
		return resX;
	}
	public static void setResX(int resX) {
		GamePreferences.resX = resX;
	}
	public static int getResY() {
		return resY;
	}
	public static void setResY(int resY) {
		GamePreferences.resY = resY;
	}
	public static boolean isFullScreenMode() {
		return fullScreenMode;
	}
	public static void setFullScreenMode(boolean fullScreenMode) {
		GamePreferences.fullScreenMode = fullScreenMode;
	}
	
	/**
	 * Methods
	 */
	public static void init(int resX, int resY, boolean fullScreenMode){
			setResX(resX);
			setResY(resY);
			setFullScreenMode(fullScreenMode);
		}
}
