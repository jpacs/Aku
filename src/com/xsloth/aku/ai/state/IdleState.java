package com.xsloth.aku.ai.state;

import com.xsloth.aku.entity.AIFighter;

public class IdleState extends State {

	
	public IdleState(AIFighter fighter) {
		super(fighter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Yup Im idle now");
		if(fighter.getDistanceFromFoe() < 180){
			fighter.changeState(new MoveState(fighter));
		}
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

}
