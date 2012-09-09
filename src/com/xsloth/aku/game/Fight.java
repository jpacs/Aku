/**
 * 
 */
package com.xsloth.aku.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.xsloth.aku.entity.AIFighter;
import com.xsloth.aku.entity.Fighter;
import com.xsloth.aku.gui.Face;
import com.xsloth.aku.gui.Lifebar;
import com.xsloth.aku.gui.Name;
import com.xsloth.aku.gui.RoundGui;
import com.xsloth.aku.gui.Stage;
import com.xsloth.aku.gui.Time;
import com.xsloth.aku.input.ActionState;
import com.xsloth.aku.input.InputAction;
import com.xsloth.aku.input.InputManager;

public class Fight {
	
	Fighter p1, p2;
	
	//Gui Variables
	Face p1Face, p2Face;
	Lifebar lifebar;
	Name name;
	Round rounds;
	RoundGui roundGui;
	Stage stage;
	Time time;
	int timerCollision=0;
	
	public Fight(){
		try {
			lifebar = new Lifebar(100, 100);
			rounds = new Round(5);	// Ler file
			roundGui = new RoundGui(new Image("res/img/game/round.png"), rounds);
			time = new Time(60);	// Ler file
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Fight(String p1, String p2, String stage){
		this();
		try {
			this.p1 = new Fighter(p1, 1);
			this.p2 = new AIFighter(p2, 2, this);
			this.stage = new Stage(new Image("res/img/stages/" + stage + ".png"));
			name = new Name(this.p1.getName(), this.p2.getName());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * State bridges
	 */
	public void render(Graphics g){
		stage.draw(g);
		
		p1.draw(g);
		p2.draw(g);
		
		lifebar.draw(g);
		name.draw(g);
		roundGui.draw(g);
		time.draw(g);
	}
	
	public void update(int delta){
		if(isRoundOver()){
			setRoundWinner();
			resetRound();
		}
		else{
			if(p1.getPositionX()>p2.getPositionX()){
				p1.setAtRight(true);
				p2.setAtRight(false);
			}
			else{
				p1.setAtRight(false);
				p2.setAtRight(true);
			}
			p1.update();
			p2.update();
			timerCollision+=delta;
			if(timerCollision>140){
				collisionDetection();
				timerCollision=0;
			}
			lifebar.setP1Life(p1.getLife());
			lifebar.setP2Life(p2.getLife());
		}
	}
	
	/**
	 * Getters and Setters
	 */
	public int getTime(){
		return time.getSeconds();
	}
	
	public int getPlayersDistance(){
		return Math.abs(p1.getPositionX() - p2.getPositionX());
	}
	
	/**
	 * Round Methods
	 */
	protected boolean isRoundOver(){
		if(time.isTimeUp() || p1.getLife() <= 0 || p2.getLife() <= 0)
			return true;
		else
			return false;
	}
	
	protected void resetRound(){
		time.setSeconds(60); // Ler file
		p1.setLife(100);
		p2.setLife(100);
	}
	
	private void setRoundWinner(){
		if(p1.getLife() > 0 && p2.getLife() <= 0)
			rounds.setWinner(1);
		else if(p2.getLife() > 0 && p1.getLife() <= 0)
			rounds.setWinner(2);
	}
	
	protected boolean isRoundWon(){
		if(rounds.isWon() > 0)
			return true;
		else
			return false;
	}
	
	protected int getWinner(){
		return rounds.isWon();
	}
	
	/**
	 * Fight Methods
	 */
	public boolean isFightOver(){
		if(isRoundWon())
			return true;
		else
			return false;
	}
	
	public void collisionDetection(){
		switch(p1.getBb().intersects(p2.getBb())){
		case 0: break;//didnt touch break;
		case 1: break;//touch cant walk break;
		case 2: p1.attacked(p2.getPower()); break;
		case 3: break;//idle and defend break;
		case 4: p2.attacked(p1.getPower()); break;
		case 5: p2.attacked(Math.round(p1.getPower()*0.15f)); p1.attacked(Math.round(p2.getPower()*0.15f)); break;
		case 6: p2.attacked(Math.round(p1.getPower()*0.05f)); break;
		case 7: break;//idle and defend break;
		case 8: p1.attacked(p2.getPower()); break;
		case 9: break;//double defend probably not going to happen 
		}
	}
	
	public void tick(){
		time.tick();
	}
}
