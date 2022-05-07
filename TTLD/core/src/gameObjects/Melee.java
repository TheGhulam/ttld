package gameObjects;

import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;

public class Melee extends Npc {
	public final float range = 2.f;
	public float attackSpeed;
	public float ShootingRadius;
	public Melee(Body body, int health, float speed, float attackSpeed,int damage,float ShootingRadius) {
		super(body, health, speed, damage,ShootingRadius);
		this.attackSpeed = attackSpeed;
		this.ShootingRadius = ShootingRadius;
	}
	
	public void attack(Entity e) {
		
	}
}
