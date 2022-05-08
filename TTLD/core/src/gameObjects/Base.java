package gameObjects;






import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import screens.GameScreen;


public class Base extends Tower {
	private GameScreen game;
	public Base(Body body, int health, float shootingRadius, float shootingSpeed, int damage, GameScreen game) {
		super(body,  health, shootingRadius, shootingSpeed, damage);
		this.game = game;
	}
	
	public boolean isGameOver() {
		if(health <= 0) {
			return true;
		}else {
			return false;
		}
	}
	public void shoot(final Entity e) {
		
		Vector2 thisPosition = this.body.getPosition();
		final Entity t = this;
		
		
		com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {

			@Override
			public void run() {
				if(!e.isDead() && !lockedToTarget) {
					float angle2 = e.body.getPosition().sub(t.body.getPosition()).angleRad();
					body.setTransform(t.body.getPosition(),angle2);
					game.getCreator().createBullet(t,e);
					setLocked(true);
				}
			}
				
			
			
		}, 0.5f);
	}
}
