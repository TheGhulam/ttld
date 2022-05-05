package gameObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;

public class Projectile {
	public float projectileSpeed;
	public Entity startingPoint;
	public Entity target;
	
	public Body bullet;
	public boolean isShot =false;
	
	public Projectile(Entity startingPoint, Body bullet, Entity target) {
		this.startingPoint = startingPoint;
		this.bullet = bullet;
		this.target = target;
	}
	
	public void shootToTarget() {
		Vector2 targetposition = target.get_Position();
		Vector2 bulletPosition = bullet.getPosition();
		
		float vectorX =(targetposition.x -bulletPosition.x);
		float vectorY = (targetposition.y - bulletPosition.y);
		double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
		Vector2 vector = new Vector2(projectileSpeed* vectorX/(float)unitDivisor,projectileSpeed*vectorY/(float)unitDivisor);
		bullet.setLinearVelocity(vector);
	}
	public boolean is_Shot() {
		Vector2 targetposition = target.get_Position();
		float distance = targetposition.dst2(bullet.getPosition());
		if(distance < 1 ) {
			isShot = true;
			Array<Fixture> fixtures = bullet.getFixtureList();
			for (int i = 0; i < fixtures.size; i++) {
				bullet.destroyFixture(fixtures.get(i));
			}
			target.setHealth(target.getHealth()-startingPoint.getDamage());
		}
		
		return isShot;
	}
}