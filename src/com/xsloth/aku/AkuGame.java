package com.xsloth.aku;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import com.xsloth.aku.states.Game;
import com.xsloth.aku.states.Intro;
import com.xsloth.aku.states.MainMenu;

public class AkuGame extends StateBasedGame  {

	public static final String gamename = "AKU";
	public static final int INTROSTATE = 0;
	public static final int MENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 2;
	
	public AkuGame() {
		super(gamename);
		this.addState(new Intro(INTROSTATE));
		this.addState(new MainMenu(MENUSTATE));
		this.addState(new Game(GAMEPLAYSTATE));
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer appgc;
		try{
			appgc = new AppGameContainer(new AkuGame());
			appgc.setDisplayMode(800, 600, false);
			//appgc.setFullscreen(true);
			appgc.start();
		}catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(INTROSTATE).init(gc, this);
		this.getState(MENUSTATE).init(gc, this);
		this.getState(GAMEPLAYSTATE).init(gc, this);
		this.enterState(MENUSTATE);
		
	}

}
