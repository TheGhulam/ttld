package gameObjects;

import com.badlogic.gdx.physics.box2d.Body;

import  com.mygdx.game.GameScreen;


public class DoomTower extends Tower{
	private GameScreen game;
	
	public DoomTower(Body body, int health, float shootingRadius, float shootingSpeed, int damage, GameScreen game) {
		super(body, health, shootingRadius, shootingSpeed, damage);
		this.game = game;
		// TODO Auto-generated constructor stub
	}
	public void shoot(Entity e) {
		try {
			Thread.sleep((long) (3000*shootingSpeed));
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			return;
		}
		if(!e.isDead()) {
			float angle2 = e.body.getPosition().sub(this.body.getPosition()).angleRad();
			body.setTransform(this.body.getPosition(),angle2);
			game.projectiles.add(game.getCreator().createBullet(this,e));
	
		}

	}
}