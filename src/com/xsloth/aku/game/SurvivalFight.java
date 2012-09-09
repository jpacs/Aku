/**
 * 
 */
package com.xsloth.aku.game;

import java.util.Date;

import org.newdawn.slick.Graphics;

import com.xsloth.aku.db.DataWriter;
import com.xsloth.aku.network.NetworkData;
import com.xsloth.aku.util.GamePreferences;

public class SurvivalFight extends Fight {
	
	int streak;
	
	public SurvivalFight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SurvivalFight(String p1, String p2, String stage) {
		super(p1, p2, stage);
		rounds = new Round(3);
		//System.out.println("Criado");
		// TODO Auto-generated constructor stub
	}

	/**
	 * State bridges
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		if(streak > 0)
			g.drawString("Win: " + streak, GamePreferences.resX*0.1f, GamePreferences.resY*0.9f);
	}

	@Override
	public void update(int delta) {
		if(isRoundWon()){
			streak+=1;
			rounds.reset();
			super.resetRound();
		}
		super.update(delta);
	}

	/**
	 * Getters and Setters
	 */
	
	
	/**
	 * Methods
	 */

	@Override
	protected boolean isRoundOver() {
		// TODO Auto-generated method stub
		return super.isRoundOver();
	}



	@Override
	public boolean isFightOver() {
		if(getWinner() == rounds.P2){
			if(NetworkData.isLogged())
				DataWriter.registerOffSurvivalFight(String.valueOf(streak));
			return true;
		}
		else
			return false;
	}
	
	
}
