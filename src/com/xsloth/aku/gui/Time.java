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
public class Time implements Gui {
	
	int seconds;

	public Time(int time){
		if(time == 10 || time == 60 || time == 90)
			seconds = time;
	}

	@Override
	public void draw(Graphics g) {
		g.drawString("" + seconds, GamePreferences.getResX()*0.5f, GamePreferences.getResY()*0.005f);
	}
	
	/**
	 * Getters and Setters
	 */
	
	public int getSeconds() {
		return seconds;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	
	/**
	 * Methods
	 */
	public void tick(){
		if(seconds>=1)
			seconds--;
	}
	
	public boolean isTimeUp(){
		if(seconds==0)
			return true;
		else
			return false;
	}
}
