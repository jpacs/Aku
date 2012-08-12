/**
 * Actions management through all state changes
 */
package com.xsloth.aku.input;

/**
 * @author xilu
 *
 */
public class Action {
	
	private InputAction action;
	private ActionState state;
	
	public Action(InputAction action, ActionState state){
		this.action = action;
		this.state = state;
	}
	
	/**
	 * Getters and Setters
	 */
	public ActionState getState() {
		return state;
	}
	
	public InputAction getAction() {
		return action;
	}

	public void setState(ActionState state) {
		this.state = state;
	}

	public void setAction(InputAction action) {
		this.action = action;
	}
	
	/**
	 * Methods
	 */
	// Handle Action press
	public void pressed(){
		//System.out.println("PRESS   " + action + " " + state);
		if(state == ActionState.STATE_RELEASED){
			state = ActionState.STATE_PRESSED;
		}
		else if (state == ActionState.STATE_PRESSED) {
			state = ActionState.STATE_HOLDING;
		}
	}
	
	// Handle Action release
	public void released(){
		//System.out.println("RELEASE " + action + " " + state);
		if(state == ActionState.STATE_PRESSED){
			state = ActionState.STATE_TAPPED;
			//System.out.println("An action is tapped");
		}
		else if (state == ActionState.STATE_HOLDING) {
			state = ActionState.STATE_RELEASED;
		}
	}
	
	// Handle Action on time event
	public void tick(){
		if (state == ActionState.STATE_TAPPED) {
			state = ActionState.STATE_RELEASED;
			//System.out.println("An action is untapped");
		}
	}
	
	// Set the Action on the released state
	public void reset() {
		state = ActionState.STATE_RELEASED;
	}
}
