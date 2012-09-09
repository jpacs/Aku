package com.xsloth.aku.ai.state;

import com.xsloth.aku.entity.AIFighter;

public class AttackState extends State {

	public AttackState(AIFighter fighter) {
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
		System.out.println("Imma wipe your ass");
		
		if(fighter.getPlayer().isStopped())
			fighter.attack();
		
		if(fighter.getDistanceFromFoe() > 180){
			fighter.changeState(new MoveState(fighter));
		}
		if(fighter.getLife() < 20){
			fighter.changeState(new DefendState(fighter));
		}
		//if(fighter.)
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

}
