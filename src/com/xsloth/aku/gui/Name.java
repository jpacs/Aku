/**
 * 
 */
package com.xsloth.aku.gui;

import org.newdawn.slick.Graphics;

import com.xsloth.aku.util.GamePreferences;

/**
 * @author xilu
 *
 */
public class Name implements Gui {
	
	static final int P1SIDE = Math.round(GamePreferences.getResX()*0.02f);
	static final int P2SIDE = Math.round(GamePreferences.getResX()*0.9f);
	
	String p1, p2;
	
	public Name(String p1, String p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawString("" + p1, P1SIDE - p1.length()/2, GamePreferences.getResY()*0.05f);
		g.drawString("" + p2, P2SIDE - p2.length()/2, GamePreferences.getResY()*0.05f);
	}
	
	/**
	 * Getters and Setters
	 */
	
	/**
	 * Methods
	 */
}
