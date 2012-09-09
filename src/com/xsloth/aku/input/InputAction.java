/**
 *  Actions that the system interprets throughout all the game states
 */
package com.xsloth.aku.input;

public enum InputAction {
	MENU_CONFIRM,		//Confirming an option
	MENU_CANCEL,		//Canceling any state or option
	
	ACTION_UP,			//Input arrow Up
	ACTION_DOWN,		//Input arrow Down
	ACTION_LEFT,		//Input arrow Left
	ACTION_RIGHT,		//Input arrow Right
	
	ACTION_1,			//Input action key/button 1
	ACTION_2,			//Input action key/button 2
	ACTION_3,			//Input action key/button 3
	ACTION_4,			//Input action key/button 4
	ACTION_PAUSE;		//Input action pause button
	
	InputAction getAction(String action){
		switch (action) {
		case "up": return ACTION_UP;
		case "down": return ACTION_DOWN;
		case "left": return ACTION_LEFT;
		case "right": return ACTION_RIGHT;
		case "1": return ACTION_1;
		case "2": return ACTION_2;
		case "3": return ACTION_3;
		case "4": return ACTION_4;

		default: return ACTION_1;
		}
	}
}
