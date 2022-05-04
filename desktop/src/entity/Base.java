package entity;




import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import com.mygdx.game.GameScreen;



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
	public void shoot(Entity e) {
		
		Vector2 thisPosition = this.get_Position();
		Entity t = this;
		
		
		com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {

			@Override
			public void run() {
				if(!e.isDead()) {
					float angle2 = e.get_Position().sub(t.get_Position()).angleRad();
					body.setTransform(t.get_Position(),angle2);
					
					game.projectiles.add(game.getCreator().createBullet(t,e));
				}
			}
				
			
			
		}, 0.2f);
		
		
			
	}
}
