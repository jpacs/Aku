package com.xsloth.aku.entity;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.lwjgl.Sys;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.PackedSpriteSheet;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.xsloth.aku.game.combo.ComboManager;
import com.xsloth.aku.gui.Gui;
import com.xsloth.aku.input.ActionState;
import com.xsloth.aku.input.InputAction;
import com.xsloth.aku.input.InputManager;
import com.xsloth.aku.math.BoundingBox;
import com.xsloth.aku.util.GamePreferences;

public class Fighter implements Gui {
	
	public static final int IDLE = 0;
	public static final int DEFENDING = 1;
	public static final int MOVING = 2;
	public static final int ATTACKING = 3;
	
	String name;
	int speed, life;
	int positionX, positionY;
	
	Animation player;
	int[] duration = {100,100,100,100};
	ComboManager combos;
	boolean atRight;
	BoundingBox bb;
	
	boolean rightFlag=false;
	
	public Fighter(String name, int numPlayer) throws SlickException{
		//XML
		SAXBuilder builder = new SAXBuilder();
		try{
			Document document = builder.build(new File("res/fighters/" + name + ".xml"));
			Element combos = document.getRootElement().getChild("combos");
			
			PackedSpriteSheet pss = new PackedSpriteSheet("res/img/fighters/" + name + "/" + name + "_sheet.def");
			PackedSpriteSheet pssLeft = new PackedSpriteSheet("res/img/fighters/" + name + "/" + name + "_sheet_left.def");
			this.combos = new ComboManager(pss, pssLeft, combos);

		} catch(JDOMException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		
		this.name = name;
		this.life = 100;
		if(numPlayer == 1){
			this.positionX = (int) Math.floor(GamePreferences.getResX()*0.2f);
			atRight = false;
		}
		else{
			this.positionX = (int) Math.floor(GamePreferences.getResX()*0.6f);
			atRight = true;
		}
		
		this.positionY = (int) Math.floor(GamePreferences.getResY()*0.5f);
		
		this.player = combos.getIdleAnimation(atRight, "idle");
		bb = new BoundingBox(positionX, positionY, player.getCurrentFrame().getWidth()*3, player.getCurrentFrame().getHeight()*3);
	}
	
	/**
	 * State bridges
	 */
	public void update(){
		if(player.isStopped()){
			player.restart();
			combos.deactivateCurrentAnimation();
		}
		
		if(!combos.isComboOn()){
			player = combos.getActiveCombo(atRight);
			if(!combos.isComboOn()){
				if(InputManager.getActionState(InputAction.ACTION_LEFT) == ActionState.STATE_PRESSED){
					player = combos.getIdleAnimation(atRight, "walking");
					move(1);
				}
				if(InputManager.getActionState(InputAction.ACTION_RIGHT) == ActionState.STATE_PRESSED){
					player = combos.getIdleAnimation(atRight, "walking");
					move(0);
				}
			}
		}
		//System.out.println(combos.getCurrentComboName());
		if(combos.getCurrentCombo() != null){
			bb = combos.getCurrentBoundingBox(player.getFrame());
			bb.update(positionX, positionY);
		}
		
//		if(player.isStopped()){
//			combos.deactivateCurrentAnimation();
//			rightFlag=false;
//		}
//		if(!combos.isComboOn()){
//			player = combos.getActiveCombo();
//
//			if(InputManager.getActionState(InputAction.ACTION_LEFT) == ActionState.STATE_PRESSED){
//				player = combos.getIdleAnimation("walking");
//				if(player.isStopped()){
//					if(atRight && !rightFlag){
//						rightFlag=true;
//						flipAnimation();
//					}
//					player.restart();
//				}
//					move(1);
//			}
//			if(InputManager.getActionState(InputAction.ACTION_RIGHT) == ActionState.STATE_PRESSED){
//				player = combos.getIdleAnimation("walking");
//				if(player.isStopped()){
//					if(atRight && !rightFlag){
//						rightFlag=true;
//						flipAnimation();
//					}
//					player.restart();
//				}
//					move(0);
//			}
//			if(atRight && !rightFlag){
//				rightFlag=true;
//				flipAnimation();
//			}
//		}
//		else if(combos.isComboOn()){
//			if(atRight && !rightFlag){
//				rightFlag=true;
//				flipAnimation();
//			}
//		}
		//bb.update(positionX, positionY, player.getCurrentFrame().getWidth()*3, player.getCurrentFrame().getHeight()*3);
		
	}
	
	@Override
	public void draw(Graphics g) {
		player.draw(positionX, positionY, player.getWidth()*3, player.getHeight()*3);
		if(combos.getCurrentCombo() != null)
			bb.draw(g);
		//g.draw(bb.getBox());
	}
	
	/**
	 * Getters and Setters
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Animation getPlayer() {
		return player;
	}
	public void setPlayer(Animation player) {
		this.player = player;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public int getPositionX() {
		return positionX;
	}
	public boolean isAtRight() {
		return this.atRight;
	}
	public void setAtRight(boolean atRight) {
		if(this.atRight && !atRight)
			rightFlag=true;
		else if(!this.atRight && atRight)
			rightFlag=true;
		else
			rightFlag=false;
		this.atRight = atRight;
	}
	public BoundingBox getBb() {
		return bb;
	}

	/**
	 * Methods
	 */
	public void flipAnimation(){
		Image[] newIA = new Image[player.getFrameCount()];
		for(int i=0; i<player.getFrameCount(); i++){
			newIA[i] = player.getImage(i).getFlippedCopy(true, false);
		}
		this.player = new Animation(newIA, 140);
		this.player.setLooping(false);
	}
	
	public void move(int i){
		switch (i) {
		case 0: 
			positionX += 2;
			break;
		case 1:
			positionX -= 2;
			break;
		default:
			break;
		}
	}
	
	public void attacked(int damage){
		setLife(getLife()-damage);
	}
	
	public int getPower(){
		return combos.getCurrentCombo().getDamage();
	}
}
