package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

public class NPC extends Entity{
	public float speed;
	public int damage;
	public float ShootingRadius;
	protected boolean lockedToTarget = false;
	public long time;

	protected ArrayList<Texture> spriteSheetAttack;
	protected ArrayList<Texture> spriteSheetAttackMirror;
	protected ArrayList<Texture> spriteSheetWalk;
	protected ArrayList<Texture> spriteSheetWalkMirror;

	private final Vector2 stopVector = new Vector2(0,0);

	private Entity npcTarget;
	public NPC(Body body, int health, float speed, int damage, float ShootingRadius) {
		super(body,health,damage);
		this.speed = speed;
		this.damage = damage;
		this.ShootingRadius = ShootingRadius;
		createSpriteArrayLs();
	}

	public boolean isAttacking() {
		return body.getLinearVelocity().equals(stopVector);
	}

	protected void createSpriteArrayLs() {
		spriteSheetAttack = new ArrayList<Texture>();
		spriteSheetAttackMirror = new ArrayList<Texture>();
		spriteSheetWalk = new ArrayList<Texture>();
		spriteSheetWalkMirror = new ArrayList<Texture>();
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
	public void setLocked(boolean flag) {
		lockedToTarget = flag;
	}
	public void setNpcTarget(Entity e) {
		npcTarget = e;
	}
	public void resetNpcTarget() {
		npcTarget = null;
	}
	public Entity getNpcTarget() {
		return npcTarget;
	}
	public boolean isAttacking(){
		Vector2 zero = new Vector2(0,0);
		if(body.getLinearVelocity().equals(zero)){
			return true;
		}else {return false;}
	}
	@Override
	public Texture getCAnimation() {
		return null;
	}
}
