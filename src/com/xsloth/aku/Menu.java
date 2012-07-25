package com.xsloth.aku;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Menu {
	
	private Image background;
	private Image selector;
	private Image[] menuOptions;
	private int x, y, width, height;
	
	private int selectedOption = 1;
	private int optionsNumber = 0;
	
	public Menu(int x, int y, int width, int height, Image[] menuOptions, Image background, Image selector){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.menuOptions = menuOptions;
		this.background = background;
		this.selector = selector;
		this.optionsNumber = 4;
		
	}
	
	/*
	 * Getters and Setters
	 */
	
	public int getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(int selectedOption) {
		this.selectedOption = selectedOption;
	}
	
	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}
	
	public Image[] getMenuOptions() {
		return menuOptions;
	}

	public void setMenuOptions(Image[] menuOptions) {
		this.menuOptions = menuOptions;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	/*
	 * Methods
	 */
	public void draw(){
		int offset = 50;
		int offset2 = 0;
		int i=0;
		background.draw(0,0);
		
		selector.draw(x - width / 2 - 50, (y - height / 2) + selectedOption * 50 );
		
		for(Image option : menuOptions){
			if(offset2 == 0){
				option.draw(x - width / 2, (y - height / 2) + offset);
				offset += 50;
				offset2 = 1;
			}
			else if(offset2 == 1)
				offset2 = 0;
			
			i++;
		}
	}
	
	public void selectedOptionUp(){
		selectedOption--;
		if(selectedOption < 1)
			selectedOption = optionsNumber;
	}
	
	public void selectedOptionDown(){
		selectedOption++;
		if(selectedOption > optionsNumber)
			selectedOption = 1;
	}
	
}
