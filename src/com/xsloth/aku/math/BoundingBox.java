package com.xsloth.aku.math;

import java.util.ArrayList;

import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.xsloth.aku.game.combo.Combo;

public class BoundingBox {
	Rectangle box;
	
	public ArrayList<ImpactBox> boxes;
	
	BoundingBox(){
		
	}
	
	public BoundingBox(int x, int y, int width, int height){
		box = new Rectangle(x, y, width, height);
	}
	
	public BoundingBox(Element frame) throws DataConversionException{
		this.boxes = new ArrayList<ImpactBox>();
		for(Element square : frame.getChildren("square")){
			//System.out.println("Frame: " + square.getAttributeValue("w"));
			boxes.add(new ImpactBox(square));
		}
	}
	
	/**
	 * Getters and Setters
	 */

	public Rectangle getBox() {
		return box;
	}
	
	/**
	 * Methods
	 */
	
	public void update(int x, int y){
		for(ImpactBox box : boxes){
			box.update(x, y);
		}
	}
	
	public void update(int x, int y, int width, int height){
		box.setLocation(x, y);
		box.setSize(width, height);
	}
	
	public void draw(Graphics g){
		//System.out.println(boxes.size());
		for(ImpactBox box : boxes){
			//System.out.println(box.height);
			g.draw(box.getBox());
		}
	}
	
	/**
	 * 
	 * @param bb
	 * @return 0-no intersections, 1-non attacking intersection
	 */
	public int intersects(BoundingBox bb){
		for(ImpactBox boxI : this.boxes){
			for(ImpactBox boxJ : bb.boxes){
//				System.out.println("p1 " + boxI.relativeX + "/" + boxI.relativeY);
//				System.out.println("p2 " + boxJ.relativeX + "/" + boxJ.relativeY);
				if(boxI.intersects(boxJ)){
//					System.out.println("INTERSECTION");
					switch (boxI.getType()) {
					case 0:
						switch (boxJ.getType()) {
						case 0: return 1;
						case 1: return 2;
						case 2: return 3;
						}
						break;
					case 1:
						switch (boxJ.getType()) {
						case 0: return 4;
						case 1: return 5;
						case 2: return 6;
						}
						break;
					case 2:
						switch (boxJ.getType()) {
						case 0: return 7;
						case 1: return 8;
						case 2: return 9;
						}
						break;
					}
				}
			}
		}
		return 0;
	}
}
