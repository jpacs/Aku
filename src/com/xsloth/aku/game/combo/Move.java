/**
 * 
 */
package com.xsloth.aku.game.combo;

import java.util.ArrayList;

import org.jdom2.Element;

import com.xsloth.aku.input.ActionState;
import com.xsloth.aku.input.InputAction;
import com.xsloth.aku.input.InputManager;

public class Move {
	
	ArrayList<InputAction> actions;
	boolean idleMove;
	
	public Move(){
			this.actions = null;
			idleMove = true;
			//System.out.println("IDLE move created");
	}
	
	public Move(Element move){
		if(move.getChildren() == null){
			this.actions = null;
			idleMove = true;
		}
		else{
			this.actions = new ArrayList<InputAction>();
			for(Element button : move.getChildren()){
				switch (button.getText()) {
				case "up": this.actions.add(InputAction.ACTION_UP); break;
				case "down": this.actions.add(InputAction.ACTION_DOWN); break;
				case "left": this.actions.add(InputAction.ACTION_LEFT); break;
				case "right": this.actions.add(InputAction.ACTION_RIGHT); break;
				case "1": this.actions.add(InputAction.ACTION_1); break;
				case "2": this.actions.add(InputAction.ACTION_2); break;
				case "3": this.actions.add(InputAction.ACTION_3); break;
				case "4": this.actions.add(InputAction.ACTION_4); break;
				}
			}
			idleMove = false;
		}
	}

	/**
	 * Getters and Setters
	 */
	
	
	/**
	 * Methods
	 */
	public boolean isPressed(){
		if(idleMove){
			for(InputAction action : InputAction.values()){
				if((InputManager.getActionState(action) == ActionState.STATE_PRESSED)){
					//System.out.println("IDLE " + action.toString() + " pressed");
					return false;
				}
			}
			//System.out.println("IDLE move true");
			return true;
		}
		else{
			//System.out.println("Non idle move checking Move idle: " + idleMove);
			for(InputAction action : actions){
				if(!(InputManager.getActionState(action) == ActionState.STATE_PRESSED)){
					//System.out.println(action.toString() + " not pressed");
					return false;
				}
				//System.out.println(action.toString() + " pressed");
			}
			return true;
		}
	}
}
