package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.utils.Array;
import screens.GameScreen;

import java.util.ArrayList;

public abstract class Entity  {
	public Body body;
	
	public int damage;
	public int health;

	public Entity(Body body, int health, int damage) {
		this.body = body;
		this.health = health;
		this.damage = damage;
		//createSpriteArrayLs();
	}
	
	public boolean isDead() {

		boolean isdead = false;
		if(health <=0) {
			isdead = true;
			Array<Fixture> fixtures = body.getFixtureList();
			for (int i = 0; i < fixtures.size; i++) {
				body.destroyFixture(fixtures.get(i));
			}
		}
		return isdead;
	}

	public abstract Texture getCAnimation();
/**
	protected void createSpriteArrayLs() {
		spriteSheetAttack = new ArrayList<Texture>();
		spriteSheetAttackMirror = new ArrayList<Texture>();
		spriteSheetWalk = new ArrayList<Texture>();
		spriteSheetWalkMirror = new ArrayList<Texture>();
	}
 */
	public void setHealth(int health) {
		this.health = health;
	}
	public int getHealth() {
		return health;
	}
	public int getDamage() {
		return damage;
	}
}
