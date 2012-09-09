/**
 * 
 */
package com.xsloth.aku.game.combo;

import java.util.ArrayList;

import org.jdom2.DataConversionException;
import org.jdom2.Element;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.PackedSpriteSheet;

import com.xsloth.aku.math.BoundingBox;
import com.xsloth.aku.math.ImpactBox;

public class Combo {
	ArrayList<Move> combo;
	int currentMove;
	boolean active;
	boolean idle;
	
	String name;
	int damage=0;
	
	Animation animation, animationLeft;
	public ArrayList<BoundingBox> bb, bbLeft;
	
	public Combo(PackedSpriteSheet pss, PackedSpriteSheet pssLeft, Element combo) throws DataConversionException{
		this.combo = new ArrayList<Move>();
		
		//Creating idle combo or normal combo
		if(combo.getChild("moveSequence").getChildren().size() > 0){
			for(Element move : combo.getChild("moveSequence").getChildren()){
				this.combo.add(new Move(move));
				this.idle = false;
			}
		}
		else{
			this.idle = true;
			this.combo.add(new Move());
		}
		
		//System.out.println("AI AI");
		
		this.currentMove = 0;
		this.name = combo.getChildText("name");
		this.damage = new Integer(combo.getChildText("damage")).intValue();
		
		//Setting up animations
		Image[] newIA = new Image[new Integer(combo.getChildText("frameNumber")).intValue()];
		for(int i = 0; i < newIA.length; i++){
			newIA[i] = (pss.getSpriteSheet(name + "_" + (i+1)));
		}
		animation = new Animation(newIA, 140);
		animation.setLooping(new Boolean(combo.getChildText("loop")).booleanValue());
		
		for(int i = 0; i < newIA.length; i++){
			newIA[i] = (pssLeft.getSpriteSheet(name + "_" + (i+1)));
		}
		animationLeft = new Animation(newIA, 140);
		animationLeft.setLooping(new Boolean(combo.getChildText("loop")).booleanValue());
		
		//Setting up bounding boxes
		this.bb = new ArrayList<BoundingBox>();
		
		if(combo.getChild("collisionSequence") != null){
			for(Element frame : combo.getChild("collisionSequence").getChildren()){
				this.bb.add(new BoundingBox(frame));
			}
		}
		//System.out.println("ONE DONE");
	}

	/**
	 * Getters and Setters
	 */
	
	public boolean isActive() {
		return active;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public Animation getAnimationLeft() {
		return animationLeft;
	}

	public void setAnimationLeft(Animation animationLeft) {
		this.animationLeft = animationLeft;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ArrayList<Move> getCombo() {
		return combo;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}
	
	/**
	 * Methods
	 */
	
	public void tick(){
		if(combo.size() == 1 && !idle){
			Move move = combo.get(currentMove);
			if(move.isPressed()){
				setActive(true);
				animation.restart();
				//System.out.println("WAS HERE Name: " + name + " currMove: " + currentMove);
				return;
			}
		}
		else if(!idle){
			if(currentMove == combo.size()){
				//System.out.println("COMBO ACTIVE " + name + " currMove: " + currentMove);
				setActive(true);
				animation.restart();
				currentMove=0;
			}
			else{
				Move move = combo.get(currentMove);
				if(move.isPressed()){
					//System.out.println("MOVE ACTIVE " + name + " currMove: " + currentMove);
					currentMove+=1;
					return;
				}
				currentMove=0;
			}
		}
//		//System.out.println("Name: " + name + " currMove: " + currentMove);
//		//Move move = combo.get(currentMove);
//		//if(!move.idleMove){
//			if(currentMove == combo.size()){
//				System.out.println("WAS HERE ACTIVE Name: " + name + " currMove: " + currentMove);
//				setActive(true);
//				currentMove=0;
//			}
//			else{
//				Move move = combo.get(currentMove);
//				setActive(false);
//				if(move.isPressed()){
//					System.out.println("WAS HERE Name: " + name + " currMove: " + currentMove);
//					currentMove+=1;
//				}
//			}
//		//}
	}
}
