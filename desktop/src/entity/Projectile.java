package entity;

import com.badlogic.gdx.math.Vector2;
import static utils.Constants.PPM;
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
	public void is_Shot() {
		if(isShot) {
		Vector2 targetposition = target.get_Position();
		
		isShot = true;
		Array<Fixture> fixtures = bullet.getFixtureList();
		for (int i = 0; i < fixtures.size; i++) {
			bullet.destroyFixture(fixtures.get(i));
		}
		target.setHealth(target.getHealth()-startingPoint.getDamage());
		}
		
	
	}
	public void setShot() {
		isShot = true;
	}
	public Entity getTarget() {
		return target;
	}
	public boolean missed() {
		boolean missed = false;
		Vector2 currentPosition = bullet.getPosition();
		Vector2 startingPosition = startingPoint.get_Position();
		float distance = startingPosition.dst2(currentPosition);
		if(distance/PPM > 50/PPM) {
			Array<Fixture> fixtures = bullet.getFixtureList();
			for (int i = 0; i < fixtures.size; i++) {
				bullet.destroyFixture(fixtures.get(i));
			}
			missed = true;
		}
		return missed;
	}
}