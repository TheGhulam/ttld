package gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

public class Melee extends NPC {
	public final float range = 2.f;
	public float attackSpeed;
	public float ShootingRadius;
	public Melee(Body body, int health, float speed, float attackSpeed, int damage, float ShootingRadius) {
		super(body, health, speed, damage,ShootingRadius);
		this.attackSpeed = attackSpeed;
		this.ShootingRadius = ShootingRadius;
		loadSpriteSheet("res/melee animations/Spider.png","res/melee animations/Spider.png","res/melee animations/SpiderMirror.png");
	}
	
	public void attack(Entity e) {
		e.setHealth(e.getHealth()-damage);
	}
}
