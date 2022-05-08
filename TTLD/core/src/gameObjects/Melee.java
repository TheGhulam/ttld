package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class Melee extends NPC {
	public final float range = 2.f;
	private final float delay = 10f;
	private int frameCounter;
	int animationIndex;
	public float attackSpeed;
	public float ShootingRadius;
	private Texture CAnimation;
	public Melee(Body body, int health, float speed, float attackSpeed, int damage, float ShootingRadius) {
		super(body, health, speed, damage,ShootingRadius);
		this.attackSpeed = attackSpeed;
		this.ShootingRadius = ShootingRadius;
		fillSpriteArrayL();
		animationIndex = 0;
		frameCounter = 0;
	}

	public void fillSpriteArrayL() {
		//Create CAnimation?
		//Attack
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack1.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack2.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack3.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack4.png"));
		spriteSheetAttack.add(new Texture("res/animations/spider/attack/attack5.png"));
		//AttackMirror
		spriteSheetAttackMirror.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror1.png"));
		spriteSheetAttackMirror.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror2.png"));
		spriteSheetAttackMirror.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror3.png"));
		spriteSheetAttackMirror.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror4.png"));
		spriteSheetAttackMirror.add(new Texture("res/animations/spider/attack/attackMirror/attackMirror5.png"));
		//Walk
		spriteSheetWalk.add(new Texture("res/animations/spider/walk/walk1.png"));
		spriteSheetWalk.add(new Texture("res/animations/spider/walk/walk2.png"));
		spriteSheetWalk.add(new Texture("res/animations/spider/walk/walk3.png"));
		spriteSheetWalk.add(new Texture("res/animations/spider/walk/walk4.png"));
		//WalkMirror
		spriteSheetWalkMirror.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror1.png"));
		spriteSheetWalkMirror.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror2.png"));
		spriteSheetWalkMirror.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror3.png"));
		spriteSheetWalkMirror.add(new Texture("res/animations/spider/walk/walkMirror/walkMirror4.png"));
	}

	public void updateCAnimation() {
		//We need two different paths (and each will require again two different paths)

			//One for attacking

				//Default attacking and its mirror

			//Other one is for walking

		//I am only writing for the walking part now
		if(frameCounter<=delay*1)
			CAnimation = spriteSheetWalk.get(0);
		else if(frameCounter<=delay*2)
			CAnimation = spriteSheetWalk.get(1);
		else if(frameCounter<=delay*3)
			CAnimation = spriteSheetWalk.get(2);
		else if(frameCounter<=delay*4)
			CAnimation = spriteSheetWalk.get(3);

		frameCounter++;
		if(frameCounter>=delay*4)
			frameCounter = 0;

				////Default walking and its mirror
	}

	@Override
	public Texture getCAnimation() {
		updateCAnimation();
		return CAnimation;
	}

	
	public void attack(Entity e) {
		e.setHealth(e.getHealth()-damage);
	}
}
