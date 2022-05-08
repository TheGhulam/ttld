package gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

public class NPCBullet extends Projectile{
	{super.projectileSpeed = 3f;}
	public NPCBullet(Entity startingPoint, Body bullet, Entity target) {
		super(startingPoint, bullet, target);
		
	}

}
