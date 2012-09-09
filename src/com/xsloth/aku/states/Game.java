package com.xsloth.aku.states;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.xsloth.aku.AkuGame;
import com.xsloth.aku.game.Fight;
import com.xsloth.aku.game.SurvivalFight;
import com.xsloth.aku.input.ActionState;
import com.xsloth.aku.input.InputAction;
import com.xsloth.aku.input.InputManager;

public class Game extends BasicGameState {
	
	int stateID;
	
	Fight fight;
	
	String p1, p2, stage;
	int gameMode;
	
	int timerTime;
	
	public Game(int stateId){
		this.stateID = stateId;
	}
	
	public Game(int stateId, int gameoption){
		this(stateId);
		System.out.println("GameOption: " + gameoption);
	}
	
	/**
	 * State Logic
	 */
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		p1 = p2 = stage = null;
		gameMode = -1;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		switch(gameMode){
		case 0:
			fight = new Fight(p1, p2, stage);
			break;
		case 1:
			fight = new SurvivalFight(p1, p2, stage);
			break;
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		p1 = p2 = stage = null;
		gameMode = -1;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		fight.render(g);
		Display.sync(60);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(gc.hasFocus()){
			timerTime += delta;
			if(timerTime > 1000){
				fight.tick();
				timerTime=0;
			}
			//System.out.println(delta);

			if(InputManager.getActionState(InputAction.MENU_CANCEL) == ActionState.STATE_TAPPED){
				sbg.enterState(AkuGame.MENUSTATE);
			}
			if(!fight.isFightOver())
				fight.update(delta);
			else
				sbg.enterState(AkuGame.MENUSTATE);

			InputManager.tick();
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
	
	/**
	 * Getters and Setters
	 */
	
	public void setCharacters(String p1, String p2){
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public void setStage(String stage){
		this.stage = stage;
	}
	
	public void setGameMode(int mode){
		this.gameMode = mode;
	}
	
	public void setFightParameters(String p1, String p2, String stage, int mode){
		setCharacters(p1, p2);
		setStage(stage);
		setGameMode(mode);
	}
	
	/**
	 * Input Listener
	 */
	
	@Override
	public void controllerButtonPressed(int controller, int button) {
		InputManager.handleControllerInput(controller, "OMG Controller is Uping");
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		InputManager.handleControllerInput(controller, "OMG Controller is Uping");
	}

	@Override
	public void controllerDownPressed(int controller) {
		super.controllerDownPressed(controller);
	}

	@Override
	public void controllerDownReleased(int controller) {
		super.controllerDownReleased(controller);
	}

	@Override
	public void controllerLeftPressed(int controller) {
		super.controllerLeftPressed(controller);
	}

	@Override
	public void controllerLeftReleased(int controller) {
		super.controllerLeftReleased(controller);
	}

	@Override
	public void controllerRightPressed(int controller) {
		super.controllerRightPressed(controller);
	}

	@Override
	public void controllerRightReleased(int controller) {
		super.controllerRightReleased(controller);
	}

	@Override
	public void controllerUpPressed(int controller) {
		InputManager.handleControllerInput(controller, "OMG Controller is Uping");
	}

	@Override
	public void controllerUpReleased(int controller) {
		super.controllerUpReleased(controller);
	}

	@Override
	public void inputEnded() {
		System.out.println("Input has ended");
	}

	@Override
	public void inputStarted() {
		System.out.println("Input has started");
	}

	@Override
	public void keyPressed(int key, char c) {
		InputManager.handleKeyInput(key, 0);
	}

	@Override
	public void keyReleased(int key, char c) {
		InputManager.handleKeyInput(key, 1);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		//InputManager.handleMouseInput(button, clickCount, 0);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		InputManager.handleMouseInput(button, -1, 1);
	}

	@Override
	public void mouseWheelMoved(int wheelValue) {
		InputManager.handleMouseWheelInput(wheelValue);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		InputManager.handleMouseMoveInput(oldx, oldy, newx, newy, 0);
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		InputManager.handleMouseInput(button, -1, 0);
	}
}
