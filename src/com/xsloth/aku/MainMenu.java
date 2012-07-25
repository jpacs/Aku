package com.xsloth.aku;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.xsloth.aku.lang.Messages;

public class MainMenu extends BasicGameState implements MouseListener {
	
	int stateID;
	Image background;
	Image selector;
	Image[] optionImages;
	Menu main;
	
	public MainMenu(int stateId){
		this.stateID = stateId;
		
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		try {
			background = new Image("res/img/menu/mainmenu.png"); //$NON-NLS-1$
			selector = new Image("res/img/menu/menuselector.png"); //$NON-NLS-1$
			Image options = new Image("res/img/menu/mainmenubuttons.png"); //$NON-NLS-1$
			optionImages = new Image[8];
			optionImages[0] = options.getSubImage(0, 0, 360, 50);
			optionImages[1] = options.getSubImage(360, 0, 720, 50);
			
			optionImages[2] = options.getSubImage(0, 50, 360, 100);
			optionImages[3] = options.getSubImage(360, 50, 720, 100);
			
			optionImages[4] = options.getSubImage(0, 100, 360, 150);
			optionImages[5] = options.getSubImage(360, 100, 720, 150);
			
			optionImages[6] = options.getSubImage(0, 150, 360, 200);
			optionImages[7] = options.getSubImage(360, 150, 720, 200);
			
			main = new Menu(400, 300, 360, 250, optionImages, background, selector);
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		main.draw();
		Display.sync(60);
		
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException {
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			main.selectedOptionDown();
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			main.selectedOptionUp();
		}
		int x = Mouse.getX(), y = Mouse.getY();
		
		if(x < 400 + 360/2 && x > 400 - 360/2){
			
			if(y < 300 + 75 && y > 300 + 25){
				System.out.println(Messages.getString("MainMenu.single")); //$NON-NLS-1$
				main.setSelectedOption(1);
				if(Mouse.isButtonDown(0)){
					if(Messages.getCurrentLang().contentEquals("pt")){
						Messages.setLocale("en");
					}
					else if(Messages.getCurrentLang().contentEquals("en")){
						Messages.setLocale("fr");
					}
					else if(Messages.getCurrentLang().contentEquals("fr")){
						Messages.setLocale("pt");
					}
					
				}
			}
			if(y < 300 + 25 && y > 300 - 25){
				System.out.println(Messages.getString("MainMenu.multi")); //$NON-NLS-1$
				main.setSelectedOption(2);
			}
			if(y < 300 - 25 && y > 300 - 75){
				System.out.println(Messages.getString("MainMenu.option")); //$NON-NLS-1$
				main.setSelectedOption(3);
			}
			if(y < 300 - 75 && y > 300 - 125){
				System.out.println(Messages.getString("MainMenu.exit")); //$NON-NLS-1$
				if(Mouse.isButtonDown(0)){
					Display.destroy();
					System.exit(0);
				}
					
				main.setSelectedOption(4);
			}
			System.out.println(""); //$NON-NLS-1$
		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
