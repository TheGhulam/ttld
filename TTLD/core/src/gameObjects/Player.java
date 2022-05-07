package gameObjects;


import com.badlogic.gdx.math.Vector3;
import screens.*;

public class Player {
	GameScreen app;
	Creator creator;
	public Player(GameScreen app) {
		this.app = app;
		creator = new Creator(app);
	}
	public void plantTower(Vector3 vector) {
		creator.createDoomTower(vector.x, vector.y);
	}
	
	
}
	