package com.xsloth.aku.states;

import java.util.List;

import org.lwjgl.input.Mouse;
//import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
//import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.xsloth.aku.AkuGame;
import com.xsloth.aku.db.dao.UserHome;
import com.xsloth.aku.db.hibernate.User;
import com.xsloth.aku.input.ActionState;
import com.xsloth.aku.input.InputAction;
import com.xsloth.aku.input.InputManager;
//import com.xsloth.aku.lang.Messages;
import com.xsloth.aku.menu.Menu;
import com.xsloth.aku.network.NetworkData;
import com.xsloth.aku.util.GamePreferences;

public class MainMenu extends BasicGameState {

	int stateID;
	Image background;
	Image selector;
	Image[] optionImages;
	Menu main;
	
	InputManager input = new InputManager();
	
	public MainMenu(int stateId){
		this.stateID = stateId;
	}
	
	/**
	 * State Logic
	 */
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		try {

			InputManager.loadMap();

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

			main = new Menu(GamePreferences.getResX()/2, GamePreferences.getResY()/2, 360, 250, optionImages, background, selector);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		main.draw();
		Display.sync(60);
	}
	//Resolver problema com input de rato vs input de teclado
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if(InputManager.getActionState(InputAction.ACTION_DOWN) == ActionState.STATE_TAPPED){
			main.selectedOptionDown();
		}
		else if(InputManager.getActionState(InputAction.ACTION_UP) == ActionState.STATE_TAPPED){
			main.selectedOptionUp();
		}
		else if (main.getSelectedOption() == 4 && InputManager.getActionState(InputAction.MENU_CONFIRM)== ActionState.STATE_PRESSED || InputManager.getActionState(InputAction.MENU_CANCEL)== ActionState.STATE_PRESSED) {
			Display.destroy();
			System.exit(0);
		}
		
		int x = Mouse.getX(), y = Mouse.getY();
		main.getOptionMouseHover(x, y);
		
		switch (main.getSelectedOption()) {
		case 1:
			if(InputManager.getActionState(InputAction.MENU_CONFIRM) == ActionState.STATE_TAPPED){
				((Game)sbg.getState(AkuGame.GAMEPLAYSTATE)).setFightParameters("Ken", "Ryu", "s1", 1); //fazer inicialização das personagens e mapa
				sbg.enterState(AkuGame.GAMEPLAYSTATE);
			}
			break;
		case 2:
			if(InputManager.getActionState(InputAction.MENU_CONFIRM) == ActionState.STATE_TAPPED){
				if(UserHome.getInstance().authenticate("jpacs", "jpacs"))
					System.out.println("User Logged In");
//				List<User> users = UserDao.getInstance().getAllUsers();
//				if(users.isEmpty())
//					System.out.println("FUCK");
//				for(User user : users)
//					System.out.println(user.getFirstName());
//				UserDao.getInstance().insertUser("biu", "biu", "b", "iu", "ahah");
			}
			break;
		case 3:
			if(InputManager.getActionState(InputAction.MENU_CONFIRM) == ActionState.STATE_TAPPED){
				if(NetworkData.isLogged()){
					NetworkData.setLogged(false);
					NetworkData.setUser(null);
					System.out.println("Sign off");
				}
				else{
					NetworkData.setLogged(true);
					NetworkData.setUser(UserHome.getInstance().findById(1l));
					System.out.println("Sign on");
					System.out.println(NetworkData.getUser().getFirstName());
				}
			}
			break;
		default:
			break;
		}

		// Ver ainda http://devlinslab.blogspot.pt/2007/10/input-handling-keypress-with-repeat.html para implementação com delay
		InputManager.tick();
	}

	@Override
	public int getID() {
		return stateID;
	}

	/**
	 * Input Listener
	 */
	@Override
	public void controllerButtonPressed(int controller, int button) {
		InputManager.handleControllerInput(controller, "OMG Controller is Uping");
	}

	@Override
	public void controllerButtonReleased(int controller, int button) {
		InputManager.handleControllerInput(controller, "OMG Controller is Uping");
	}

	@Override
	public void controllerDownPressed(int controller) {
		super.controllerDownPressed(controller);
	}

	@Override
	public void controllerDownReleased(int controller) {
		super.controllerDownReleased(controller);
	}

	@Override
	public void controllerLeftPressed(int controller) {
		super.controllerLeftPressed(controller);
	}

	@Override
	public void controllerLeftReleased(int controller) {
		super.controllerLeftReleased(controller);
	}

	@Override
	public void controllerRightPressed(int controller) {
		super.controllerRightPressed(controller);
	}

	@Override
	public void controllerRightReleased(int controller) {
		super.controllerRightReleased(controller);
	}

	@Override
	public void controllerUpPressed(int controller) {
		InputManager.handleControllerInput(controller, "OMG Controller is Uping");
	}

	@Override
	public void controllerUpReleased(int controller) {
		super.controllerUpReleased(controller);
	}

	@Override
	public void inputEnded() {
		System.out.println("Input has ended");
	}

	@Override
	public void inputStarted() {
		System.out.println("Input has started");
	}

	@Override
	public void keyPressed(int key, char c) {
		InputManager.handleKeyInput(key, 0);
	}

	@Override
	public void keyReleased(int key, char c) {
		InputManager.handleKeyInput(key, 1);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		//InputManager.handleMouseInput(button, clickCount, 0);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		InputManager.handleMouseInput(button, -1, 1);
	}

	@Override
	public void mouseWheelMoved(int wheelValue) {
		InputManager.handleMouseWheelInput(wheelValue);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		InputManager.handleMouseMoveInput(oldx, oldy, newx, newy, 0);
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		InputManager.handleMouseInput(button, -1, 0);
	}

}
