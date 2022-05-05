package gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

public class NpcBullet extends Projectile{
	{super.projectileSpeed = 1.5f;}
	public NpcBullet(Entity startingPoint, Body bullet, Entity target) {
		super(startingPoint, bullet, target);
		
	}

}
