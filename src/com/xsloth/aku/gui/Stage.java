/**
 * 
 */
package com.xsloth.aku.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.xsloth.aku.util.GamePreferences;

/**
 * @author xilu
 *
 */
public class Stage implements Gui {
	
	Image background;
	
	public Stage(Image img){
		this.background = img;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		background.draw(0, 0, GamePreferences.getResX(), GamePreferences.getResY());
	}
	
	/**
	 * Getters and Setters
	 */
	
	/**
	 * Methods
	 */
}
