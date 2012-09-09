/**
 * 
 */
package com.xsloth.aku.gui;

import org.newdawn.slick.Graphics;

/**
 * @author xilu
 *
 */
public class Infotext implements Gui {
	
	String text, variableText;
	int x, y;
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawString(text + ": " + variableText, x, y);
	}
	
	/**
	 * Getters and Setters
	 */
	
	/**
	 * Methods
	 */
}
