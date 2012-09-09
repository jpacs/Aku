package com.xsloth.aku.ai.state;

import com.xsloth.aku.entity.AIFighter;

abstract public class State {
	
	AIFighter fighter;
	
	public State(AIFighter fighter){
		this.fighter = fighter;
	}
	
	public abstract void enter();
	
	public abstract void execute();
	
	public abstract void leave();
}
