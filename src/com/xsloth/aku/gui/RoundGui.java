/**
 * 
 */
package com.xsloth.aku.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.xsloth.aku.game.Round;
import com.xsloth.aku.util.GamePreferences;

/**
 * @author xilu
 *
 */
public class RoundGui implements Gui {
	
	static final int P1SIDE = Math.round(GamePreferences.getResX()*0.47f);
	static final int P2SIDE = Math.round(GamePreferences.getResX()*0.53f);
	
	Image round;
	Round gameRounds;
	int p1Vics=0, p2Vics=0;
	int width=20, height=20;
	
	public RoundGui(Image img, Round r){
		this.round = img;
		this.gameRounds = r;
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		for(int winner : gameRounds.getRoundStandings()){
			if(winner == 1){
				round.draw(P1SIDE - width/2 - width*p1Vics, Math.round(GamePreferences.getResY()*0.05f), width, height);
				p1Vics+=1;
			}
			if(winner == 2){
				round.draw(P2SIDE + width/2 + width*p2Vics, Math.round(GamePreferences.getResY()*0.05f), width, height);
				p2Vics+=1;
			}
		}
		p1Vics = p2Vics = 0;
	}

	/**
	 * Getters and Setters
	 */
	
	/**
	 * Methods
	 */
}
