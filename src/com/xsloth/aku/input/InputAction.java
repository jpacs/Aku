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
	ACTION_PAUSE		//Input action pause button
}
