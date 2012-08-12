/**
 * Class to handle basic menu operations.
 */
package com.xsloth.aku.menu;

import org.newdawn.slick.Image;

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
	// Draw menu options
	public void draw(){
		int offset = 50;
		int offset2 = 0;
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
		}
	}
	
	/**
	 * Get option hover by mouse XY __ Rever depois nestas funções os offsets2
	 * @return 1 to n the option selected. -1 if mouse is not hovering an option
	 */
	public int getOptionMouseHover(int x, int y){
		int offset = 50;
		int offset2 = 0;
		
		y =  -(y - this.y * 2);
		
		for(int i = 1; i < optionsNumber+1; i++){
			offset += 50;
			if(offset2 == 0){
				if(x > (this.x - width / 2) && x < (this.x + width / 2) && y > (this.y - height / 2) && y < this.y - (height / 2) + (offset)){
					offset2 = 1;
					selectedOption=i;
					return i;
				}
			}
			else if(offset2 == 1)
				offset2 = 0;
		}
		return -1;
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
