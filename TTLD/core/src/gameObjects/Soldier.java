package gameObjects;

import com.badlogic.gdx.physics.box2d.Body;
import screens.GameScreen;

public class Soldier extends Ranged {
	private final GameScreen game;
	
	public Soldier(Body body, int health, float speed, float shootingSpeed, float range, int damage, GameScreen game) {
		super(body, health, speed, shootingSpeed, range, damage);
		this.game = game;
	}
	public void shoot(Entity t) {
		if(!t.isDead()) {
			game.getCreator().createNpcBullet(this, t);
		}

	}
}
