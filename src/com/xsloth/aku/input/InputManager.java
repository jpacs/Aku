/**
 * Receive raw Input from the Input Listeners on the BasicGameStates and translate it to the respective Action.
 * Load Input mappings, default or custom. (Input mappings are the attribution from a determined input to a game comprehensible Action)
 */
package com.xsloth.aku.input;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;

/**
 * @author xilu
 *
 */
public class InputManager {
	
	static final int MOUSE_BUTTON_LEFT = 0;
	static final int MOUSE_BUTTON_RIGHT = 1;
	static final int MOUSE_BUTTON_MIDDLE = 2;
	static final int MOUSE_WHEEL_UP = 3;
	static final int MOUSE_WHELL_DOWN = 4;
	
	static int mouseX=0, mouseY=0, mouseDX=0, mouseDY=0;
	static Map<Integer, Action> mouseMapping = new HashMap<>();
	static Map<Integer, Action> keyMapping = new HashMap<>();
	
	public InputManager(){
		
	}
	
	
	/**
	 * Getters and Setters
	 */
	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		InputManager.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		InputManager.mouseY = mouseY;
	}

	public static int getMouseDX() {
		return mouseDX;
	}

	public static void setMouseDX(int mouseDX) {
		InputManager.mouseDX = mouseDX;
	}

	public static int getMouseDY() {
		return mouseDY;
	}

	public static void setMouseDY(int mouseDY) {
		InputManager.mouseDY = mouseDY;
	}
	
	public static ActionState getActionState(InputAction action){
		for(Action value : keyMapping.values()){
			//System.out.println("Action / Value " + action  + "/" + value.getAction());
			if(value.getAction() == action){
				return value.getState();
			}
		}
		for(Action value : mouseMapping.values()){
			//System.out.println("Action / Value " + action  + "/" + value);
			if(value.getAction() == action){
				return value.getState();
			}
		}
		return ActionState.STATE_RELEASED;
	}
	
	/**
	 * Methods
	 */
	// Load the default mapping
	public static boolean loadMap(){
		
		Action mConfirm = new Action(InputAction.MENU_CONFIRM, ActionState.STATE_RELEASED),
					mCancel = new Action(InputAction.MENU_CANCEL, ActionState.STATE_RELEASED),
					aUp = new Action(InputAction.ACTION_UP, ActionState.STATE_RELEASED),
					aDown = new Action(InputAction.ACTION_DOWN, ActionState.STATE_RELEASED),
					aLeft = new Action(InputAction.ACTION_LEFT, ActionState.STATE_RELEASED),
					aRight = new Action(InputAction.ACTION_RIGHT, ActionState.STATE_RELEASED),
					a1 = new Action(InputAction.ACTION_1, ActionState.STATE_RELEASED),
					a2 = new Action(InputAction.ACTION_2, ActionState.STATE_RELEASED),
					a3 = new Action(InputAction.ACTION_3, ActionState.STATE_RELEASED),
					a4 = new Action(InputAction.ACTION_4, ActionState.STATE_RELEASED),
					aPause = new Action(InputAction.ACTION_PAUSE, ActionState.STATE_RELEASED);
		
		mouseMapping.put(MOUSE_WHEEL_UP, aUp);
		mouseMapping.put(MOUSE_WHELL_DOWN, aDown);
		mouseMapping.put(MOUSE_BUTTON_LEFT, mConfirm);
		mouseMapping.put(MOUSE_BUTTON_RIGHT, mCancel);
		
		keyMapping.put(Keyboard.KEY_RETURN, mConfirm);
		keyMapping.put(Keyboard.KEY_BACK, mCancel);
		//keyMapping.put(Keyboard.KEY_ESCAPE, mCancel);
		
		keyMapping.put(Keyboard.KEY_UP, aUp);
		keyMapping.put(Keyboard.KEY_DOWN, aDown);
		keyMapping.put(Keyboard.KEY_LEFT, aLeft);
		keyMapping.put(Keyboard.KEY_RIGHT, aRight);
		
		keyMapping.put(Keyboard.KEY_W, a1);
		keyMapping.put(Keyboard.KEY_A, a2);
		keyMapping.put(Keyboard.KEY_S, a3);
		keyMapping.put(Keyboard.KEY_D, a4);
		keyMapping.put(Keyboard.KEY_ESCAPE, aPause);
		return true;
	}
	
	//Load the user custom mapping, if none defined load the default one
	public static boolean loadCustomMap(){
		//if search directory for custom file true load, else false
		//if()
		return true;
	}
	
	/**
	 * 
	 * @param key Id
	 * @param ClickedCount variable to store click count (double click)
	 * @param type  of Input: 0 - pressed, 1 - released
	 */
	public static synchronized void handleMouseInput(int key, int ClickedCount, int type){
		//System.out.println("Houston we have input: " + key);
		if(mouseMapping.containsKey(key)){
			switch(type){
			case 0: mouseMapping.get(key).pressed(); break;
			case 1: mouseMapping.get(key).released(); break;
			}
			//System.out.println("Houston we have RIGHT input: " + key + " " + mouseMapping.get(key).getState());
		}
	}
	
	/**
	 * 
	 * @param wheelValue variable to store  wheel movement
	 */
	public static synchronized void handleMouseWheelInput(int wheelValue){
		//System.out.println("Houston we have input: " + wheelValue);
		int id = wheelValue > 0 ? MOUSE_WHEEL_UP : MOUSE_WHELL_DOWN; mouseMapping.get(id).pressed();
	}
	
	/**
	 * 
	 * @param oldx
	 * @param oldy
	 * @param newx
	 * @param newy
	 * @param type
	 */
	public static synchronized void handleMouseMoveInput(int oldx, int oldy, int newx, int newy, int type){
		mouseX = newx;
		mouseY = newy;
		mouseDX = mouseX - oldx;
		mouseDY = mouseY - oldy;
	}
	
	/**
	 * 
	 * @param key Id
	 * @param type of Input: 0 - pressed, 1 - released
	 */
	public static synchronized void handleKeyInput(int key, int type){
		if(keyMapping.containsKey(key)){
			switch(type){
			case 0: keyMapping.get(key).pressed(); break;
			case 1: keyMapping.get(key).released(); break;
			}
			//System.out.println("Houston we have input: " + key + " " + keyMapping.get(key).getState());
		}
		//System.out.println("Houston we have UNCHARTED input: " + key);
	}
	
	public static synchronized void handleControllerInput(int key, String s){
		System.out.println("Houston we have input: " + s + " " + key);
	}
	
	// Call Action updates on time event
	public static void tick(){
		for(Action value : keyMapping.values()){
			value.tick();
		}
		for(Action value : mouseMapping.values()){
			value.tick();
		}
	}
}
