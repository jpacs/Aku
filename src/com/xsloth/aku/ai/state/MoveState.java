package com.xsloth.aku.ai.state;

import com.xsloth.aku.entity.AIFighter;
import com.xsloth.aku.math.RandomGenerator;

public class MoveState extends State {

	public MoveState(AIFighter fighter) {
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

		System.out.println("I better move im scared");
		if(!(fighter.getDistanceFromFoe() < 100) && fighter.getLife() > 20 && RandomGenerator.generateAndGetResult(100, 0.2f)){
			//fighter.changeState(new IdleState(fighter));
			if(fighter.getPlayer().isStopped())
				fighter.getPlayer().restart();
			if(fighter.isAtRight())
				fighter.move(1);
			else
				fighter.move(0);
		}
		else
			fighter.changeState(new AttackState(fighter));

		if(fighter.getLife() < 20){
			fighter.changeState(new DefendState(fighter));
		}
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub

	}

}
