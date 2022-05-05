package gameObjects;

import screens.GameScreen;
import screens.MouseHandler;

public class Player {
	GameScreen app;
	MouseHandler mouseHandler;
	Creator creator;
	public Player(GameScreen app, MouseHandler mH ) {
		this.app = app;
		mouseHandler = mH;
		creator = new Creator(app);
	}
	public void plantTower() {
		if(mouseHandler.isClicked) {
			int x = (int)mouseHandler.pressedX;
			int y = (int)mouseHandler.pressedY;
			if(creator.isAvailable(x, y)) {
				creator.createDoomTower(x, y);
				
			}
		}
		mouseHandler.isClicked = false;
	}
	
	
}
	