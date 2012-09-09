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
public class Lifebar implements Gui {
	
	static final int P1SIDE = Math.round(GamePreferences.getResX()*0.02f);
	static final int P2SIDE = Math.round(GamePreferences.getResX()*0.9f);
	
	int p1Life, p2Life;
	
	public Lifebar(int p1, int p2){
		this.p1Life = p1;
		this.p2Life = p2;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawString("" + p1Life, P1SIDE, GamePreferences.getResY()*0.005f);
		g.drawString("" + p2Life, P2SIDE, GamePreferences.getResY()*0.005f);
		
	}

	
	/**
	 * Getters and Setters
	 */

	public void setP1Life(int p1Life) {
		this.p1Life = p1Life;
	}

	public void setP2Life(int p2Life) {
		this.p2Life = p2Life;
	}
	
	/**
	 * Methods
	 */
}
