package com.xsloth.aku.gui;

import org.newdawn.slick.Image;

//import com.mojang.mojam.screen.*;

public class Font {
    public static String letters = "abcdefghijklmnopqrstuvwxtzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!?=";
    
    public static Image[] font;

    public static int getStringWidth(String s) {
        return s.length() * 32;
    }

    private Font() {
    }

//    public static void draw(Screen screen, String msg, int x, int y) {
//    	msg = msg.toUpperCase();
//    	int length = msg.length();
//    	for (int i = 0; i < length; i++) {
//    		int c = letters.indexOf(msg.charAt(i));
//    		if (c < 0) continue;
//    		screen.blit(font[c % 55][c / 55], x, y);
//    		x += 8;
//    	}
//    }
}
