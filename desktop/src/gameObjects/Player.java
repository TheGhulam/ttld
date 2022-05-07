package gameObjects;


import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.*;

public class Player {
	GameScreen app;
	MouseHandler mouseHandler;
	Creator creator;
	public Player(GameScreen app, MouseHandler mH ) {
		this.app = app;
		mouseHandler = mH;
		creator = new Creator(app);
	}
	public void plantTower(Vector3 vector) {
		creator.createDoomTower(vector.x, vector.y);
	}
	
	
}
	