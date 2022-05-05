package gameObjects;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;

public class Tower extends Entity implements Shooter{
	public float shootingRadius;
	public float shootingSpeed;
	protected boolean lockedToTarget = false;
	
	
	public Tower(Body body, int health, float shootingRadius, float shootingSpeed, int damage) {
		super(body,health,damage);
		
		this.shootingRadius = shootingRadius;
		this.shootingSpeed = shootingSpeed;
		this.damage = damage;
		
		
		
	}

	@Override
	public void shoot(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRange(float range) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setShootingSpeed(float shootingSpeed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDamage(int damage) {
		// TODO Auto-generated method stub
		
	}
	public void setLocked(boolean flag) {
		lockedToTarget = flag;
	}
}
