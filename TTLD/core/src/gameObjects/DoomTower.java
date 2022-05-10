package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import  screens.GameScreen;

public class DoomTower extends Tower{
	private GameScreen game;
	
	public DoomTower(Body body, int health, float shootingRadius, float shootingSpeed, int damage, GameScreen game) {
		super(body, health, shootingRadius, shootingSpeed, damage);
		this.game = game;
		tex = new Texture("res/gameObjects/B18.png");
	
	}
	public void shoot(final Entity e) {
		
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