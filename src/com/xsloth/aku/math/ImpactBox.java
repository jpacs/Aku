package com.xsloth.aku.math;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.newdawn.slick.geom.Rectangle;

public class ImpactBox {

	public static final int IDLE = 0;
	public static final int ATTACK = 1;
	public static final int DEFEND = 2;
	
	Rectangle box;
	int type, relativeX, relativeY, width, height;
	
	public ImpactBox(int relativeX, int relativeY, int width, int height, int type){
		this.type = type;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		box = new Rectangle(relativeX, relativeY, width, height);
		//System.out.println("Created box with(x/y/width/height): " + relativeX + "/" + relativeY + "/" + width + "/" + height + "/" + type);
	}
	
	public ImpactBox(Element square) throws DataConversionException{
		this(square.getAttribute("x").getIntValue(), square.getAttribute("y").getIntValue(), square.getAttribute("w").getIntValue(),square.getAttribute("h").getIntValue(), square.getAttribute("type").getIntValue());
	}
	
	/**
	 * Getters and Setters
	 */
	
	public Rectangle getBox() {
		return box;
	}
	public void setBox(Rectangle box) {
		this.box = box;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * Methods
	 */
	public void update(int x, int y){
		//System.out.println("Being updated: " + x + " " + y);
		box.setLocation(x+relativeX, y+relativeY);
	}
	
	public boolean intersects(ImpactBox box){
		return this.box.intersects(box.getBox());
	}
	
}
