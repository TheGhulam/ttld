package entity;




import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.GameScreen;

public class Soldier extends Ranged {
	private GameScreen game;
	public Soldier(Body body, int health, float speed, float shootingSpeed, float range, int damage, GameScreen game) {
		super(body, health, speed, shootingSpeed, range, damage);
		this.game = game;
	}
	public void shoot(Entity t) {
		
	
	
	Entity k = this;
	
	
	com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {

		@Override
		public void run() {
			if(!t.isDead()) {
				float angle2 = t.get_Position().sub(k.get_Position()).angleRad();
				body.setTransform(k.get_Position(),angle2);
				
				game.projectiles.add(game.getCreator().createBullet(k,t));
			}
		}
			
		
		
	}, 2);

	}
}
