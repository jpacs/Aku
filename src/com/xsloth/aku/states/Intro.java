package com.xsloth.aku.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.xsloth.aku.AkuGame;

public class Intro extends BasicGameState {
	
	int stateID;
	public Image[] intro;
	int state = 0;
	float alpha = 0.01f;

	public Intro(int stateId){
		this.stateID = stateId;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		intro = new Image[2];
		intro[0] = new Image("res/img/intro/estgppresents.png");
		intro[1] = new Image("res/img/intro/xslothproduction.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics arg2) throws SlickException {
		if(state == 0)
			intro[state].draw(0, 0);
		else if(state == 1)
			intro[state].draw(0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		//System.out.println("Alpha: " + alpha);
		//try {
			//Thread.sleep(2500);
			intro[state].setAlpha(alpha);
			alpha+=0.001;
			if(alpha > 1){
				state += 1;
				alpha = 0.01f;
			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		if (state == 2){
			sbg.enterState(AkuGame.MENUSTATE);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
