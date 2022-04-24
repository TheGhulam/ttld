package gameObjects;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Base extends Tower {

	public Base(Body body, int health, float shootingRadius, float shootingSpeed, int damage) {
		super(body,  health, shootingRadius, shootingSpeed, damage);
		// TODO Auto-generated constructor stub
	}
	
	public boolean isGameOver() {
		if(health <= 0) {
			return true;
		}else {
			return false;
		}
	}
	public void shoot(Entity e) {
		try {
			Thread.sleep((long) (3000*shootingSpeed));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			return;
		}
		if(!e.isDead()) {
			float angle2 = e.get_Position().sub(this.get_Position()).angleRad();
			body.setTransform(this.get_Position(),angle2);
			
	
		}

	}
}
