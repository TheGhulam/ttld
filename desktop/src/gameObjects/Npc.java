package entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Npc extends Entity{
	public float speed;
	public int damage;
	public Npc(Body body,int health, float speed, int damage) {
		super(body,health,damage);
		this.speed = speed;
		this.damage = damage;
	}
}
