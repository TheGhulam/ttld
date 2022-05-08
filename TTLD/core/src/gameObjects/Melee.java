package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class Melee extends NPC {
	public final float range = 2.f;
	public float attackSpeed;
	public float ShootingRadius;
	private Texture CAnimation;
	public Melee(Body body, int health, float speed, float attackSpeed, int damage, float ShootingRadius) {
		super(body, health, speed, damage,ShootingRadius);
		this.attackSpeed = attackSpeed;
		this.ShootingRadius = ShootingRadius;
		fillSpriteArrayL();
	}

	public void fillSpriteArrayL() {
		//Attack
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack1.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack2.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack3.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack4.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack5.png"));
		//AttackMirror
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror1.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror2.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror3.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror4.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror5.png"));
		//Walk
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walk1.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walk2.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walk3.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walk4.png"));
		//WalkMirror
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror1.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror2.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror3.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror4.png"));
	}

	

	@Override
	public Texture getCAnimation() {
		return CAnimation;
	}

	
	public void attack(Entity e) {
		e.setHealth(e.getHealth()-damage);
	}
}
