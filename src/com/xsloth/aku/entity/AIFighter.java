package com.xsloth.aku.entity;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.xsloth.aku.ai.state.IdleState;
import com.xsloth.aku.ai.state.State;
import com.xsloth.aku.game.Fight;

public class AIFighter extends Fighter {
	
	State currentState;
	Fight fight;

	public AIFighter(String name, int numPlayer, Fight fight) throws SlickException {
		super(name, numPlayer);
		// TODO Auto-generated constructor stub
		
		currentState = new IdleState(this);
		this.fight = fight;
	}

	/**
	 * State bridges
	 */

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//super.update();
		currentState.execute();
		//bb.update(positionX, positionY, player.getCurrentFrame().getWidth()*3, player.getCurrentFrame().getHeight()*3);
		if(combos.getCurrentCombo() != null){
			bb = combos.getCurrentBoundingBox(player.getFrame());
			bb.update(positionX, positionY);
		}
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		super.draw(g);
		//player.draw(positionX, positionY, player.getWidth()*3, player.getHeight()*3);
		//g.draw(bb.getBox());
	}
	
	public void changeState(State state){
		currentState = state;
	}
	
	/**
	 * Methods
	 */
	
	public int getDistanceFromFoe(){
		return this.fight.getPlayersDistance();
	}
	
	public void attack(){
		player = combos.setCombo(atRight);
		//player.restart();
	}

}
