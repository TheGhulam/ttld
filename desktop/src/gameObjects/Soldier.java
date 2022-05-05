package gameObjects;




import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.GameScreen;

public class Soldier extends Ranged {
	private GameScreen game;
	
	public Soldier(Body body, int health, float speed, float shootingSpeed, float range, int damage, GameScreen game) {
		super(body, health, speed, shootingSpeed, range, damage);
		this.game = game;
		time = System.currentTimeMillis();
	}
	public void shoot(Entity t) {
		
	
	
	Entity k = this;
	
	
	
			
			if(!t.isDead() && !lockedToTarget) {
				float angle2 = t.body.getPosition().sub(k.body.getPosition()).angleRad();
				body.setTransform(k.body.getPosition(),angle2);
				
				game.getCreator().createNpcBullet(k,t);
				setLocked(true);
			}
		
	


	}
}
