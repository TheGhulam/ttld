package entity;


import com.mygdx.game.*;

public class Player {
	Application app;
	MouseHandler mouseHandler;
	Creator creator = new Creator();
	public Player(Application app, MouseHandler mH ) {
		this.app = app;
		mouseHandler = mH;
		
	}
	public void plantTower() {
		if(mouseHandler.isClicked()) {
			int x = (int)mouseHandler.getTargetX();
			int y = (int)mouseHandler.getTargetY();
			if(creator.isAvailable(x, y)) {
				creator.createDoomTower(x, y);
				
			}
		}
		mouseHandler.resetClicked();
	}
	
	
}
	