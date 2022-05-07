package gameObjects;

import com.badlogic.gdx.math.Vector2;
import screens.*;
import com.badlogic.gdx.physics.box2d.Body;

public class Bullet extends Projectile {
	{super.projectileSpeed = 4f;}
	public Bullet(Entity startingPoint, Body bullet,Entity target) {
		super(startingPoint,bullet, target);
		
	}

}
