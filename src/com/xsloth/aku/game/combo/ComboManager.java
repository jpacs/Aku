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

public class ComboManager {
	ArrayList<Combo> combos;
	boolean comboOn;
	Combo currentCombo;
	
	public ComboManager(PackedSpriteSheet pss, PackedSpriteSheet pssLeft, Element combos){
		this.combos = new ArrayList<Combo>();
		for(Element combo : combos.getChildren()){
			try {
				this.combos.add(new Combo(pss, pssLeft, combo));
			} catch (DataConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		currentCombo=null;
	}
	/**
	 * Getters and Setters
	 */
	
	public String getCurrentComboName() {
		return currentCombo.getName();
	}
	
	public boolean isComboOn() {
		return comboOn;
	}

	public void setComboOn(boolean comboOn) {
		this.comboOn = comboOn;
	}

	/**
	 * Methods
	 */
	public Combo getCurrentCombo(){
		return currentCombo;
	}
	
	public BoundingBox getCurrentBoundingBox(int i){
		System.out.println(currentCombo.getName());
		return currentCombo.bb.get(i);
	}
	
	public Animation getActiveCombo(boolean atRight){
		for(Combo combo : combos){
			combo.tick();
			if(combo.isActive()){
				System.out.println("ACTIVE " + combo.name);
				currentCombo = combo;
				setComboOn(true);
				if(atRight)
					return combo.getAnimationLeft();
				else
					return combo.getAnimation();
			}
		}
		//comboOn=false;
		return getIdleAnimation(atRight, "idle");
	}
	
	public Animation getIdleAnimation(boolean atRight, String idle){
		for(Combo combo : combos){
			if(combo.getName().equals(idle)){
				currentCombo = combo;
				if(atRight)
					return combo.getAnimationLeft();
				else
					return combo.getAnimation();
			}
		}
		return null;
	}
	
	public void deactivateCurrentAnimation(){
		if(currentCombo != null){
			currentCombo.setActive(false);
			currentCombo=null;
			setComboOn(false);
		}
	}
	
	public Animation setCombo(boolean atRight){
		for(Combo combo : combos){
			combo.tick();
			//System.out.println("SEARCHING " + combo.name);
			if(combo.name.equals("punch")){
				combo.setActive(true);
				System.out.println("AIACTIVE " + combo.name);
				currentCombo = combo;
				comboOn=true;
				//flipAnimation(combo);
				if(atRight)
					return combo.getAnimationLeft();
				else
					return combo.getAnimation();
			}
		}
		return null;
	}
	
	public void flipAnimation(Combo combo){
		Image[] newIA = new Image[combo.getAnimation().getFrameCount()];
		for(int i=0; i<combo.getAnimation().getFrameCount(); i++){
			newIA[i] = combo.getAnimation().getImage(i).getFlippedCopy(true, false);
		}
		combo.setAnimation(new Animation(newIA, 140));
		combo.getAnimation().setLooping(false);
	}
}
