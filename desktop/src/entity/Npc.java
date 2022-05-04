package entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Npc extends Entity{
	public float speed;
	public int damage;
	public float ShootingRadius;
	public Npc(Body body,int health, float speed, int damage, float ShootingRadius) {
		super(body,health,damage);
		this.speed = speed;
		this.damage = damage;
		this.ShootingRadius = ShootingRadius;
	}
	public void attack(Entity e) {
		if (this instanceof Ranged) {
			Ranged a = (Ranged) this;
			a.shoot(e);
		}else {
			Melee a = (Melee) this;
			a.attack(e);
		}
	}
}
