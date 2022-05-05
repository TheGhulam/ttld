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
		Vector2 targetposition = target.body.getPosition();
		Vector2 bulletPosition = bullet.getPosition();
		
		float vectorX =(targetposition.x -bulletPosition.x);
		float vectorY = (targetposition.y - bulletPosition.y);
		double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
		Vector2 vector = new Vector2(projectileSpeed* vectorX/(float)unitDivisor,projectileSpeed*vectorY/(float)unitDivisor);
		bullet.setLinearVelocity(vector);
	}
	public void is_Shot() {
		if(isShot) {
		Vector2 targetposition = target.body.getPosition();
		
		
		Array<Fixture> fixtures = bullet.getFixtureList();
		for (int i = 0; i < fixtures.size; i++) {
			bullet.destroyFixture(fixtures.get(i));
		}
		target.setHealth(target.getHealth()-startingPoint.getDamage());
		}
		isShot = false;
	
	}
	public void setShot() {
		isShot = true;
	}
	public Entity getTarget() {
		return target;
	}
	public Entity getStart() {
		return startingPoint;
	}
	public boolean missed() {
		boolean missed = false;
		if(target.isDead()) {
			missed = true;
			if(startingPoint instanceof Tower) {
				Tower a = (Tower) startingPoint;
				a.setLocked(false);
				Array<Fixture> fixtures = bullet.getFixtureList();
				for (int i = 0; i < fixtures.size; i++) {
					bullet.destroyFixture(fixtures.get(i));
				}
			}else {
				Npc b = (Npc) startingPoint;
				b.setLocked(false);
				Array<Fixture> fixtures = bullet.getFixtureList();
				for (int i = 0; i < fixtures.size; i++) {
					bullet.destroyFixture(fixtures.get(i));
				}
			}
		}
		return missed;
	}
}