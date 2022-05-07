package gameObjects;

import static utils.Constants.PPM;

import screens.*;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

public class Creator {
	public GameScreen gameScreen;
	public World world;
	public Creator(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
		world =gameScreen.getWorld();
	}
	
	
	
	public boolean isAvailable(int x, int y) {
		return true;
	}
	
	
	public DoomTower createDoomTower(int x, int y) {
		Body tbody;
		BodyDef	def = new BodyDef();
		def.type = BodyDef.BodyType.StaticBody;
		def.position.set(x/PPM,y/PPM);
		def.fixedRotation = true;
		tbody = world.createBody(def);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(32f/PPM);
		
		Fixture fix = tbody.createFixture(shape2, 1.0f);
		fix.setUserData("Tower");
		shape2.dispose();		
		
		DoomTower dt = new DoomTower(tbody, 2000, 10, 0.3f, 25,gameScreen);
		tbody.setUserData(dt);
		gameScreen.getTowers().add(dt);
		return dt;
		
	}
	public Base createBase() {
		Body tbody;
		BodyDef	def = new BodyDef();
		def.type = BodyDef.BodyType.StaticBody;
		def.position.set(0,0);
		def.fixedRotation = true;
		tbody = world.createBody(def);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(20f/PPM);
		
		Fixture fix =tbody.createFixture(shape2, 1.0f);
		fix.setUserData("Tower");
		shape2.dispose();		
		
		Base base = new Base(tbody, 7000, 20, 0.1f, 100, gameScreen);
		tbody.setUserData(base);
		return base;
		
		
		
		
	}
	public Bullet createBullet(Entity startingPoint ,Entity Target) {
		float x = startingPoint.body.getPosition().x;
		float y = startingPoint.body.getPosition().y;
		Body bbody;
		BodyDef	def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x/PPM,y/PPM);
		def.fixedRotation = true;
		bbody = world.createBody(def);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(1f/PPM);
		
		Fixture fix =bbody.createFixture(shape2, 1.0f);
		fix.setUserData("Bullet");
		fix.setSensor(true);
		shape2.dispose();		
		Bullet bullet = new Bullet(startingPoint, bbody,Target);
		bbody.setUserData(bullet);
		gameScreen.getProjectile().add(bullet);
		return bullet;
	}
	public NpcBullet createNpcBullet(Entity startingPoint ,Entity Target) {
		float x = startingPoint.body.getPosition().x;
		float y = startingPoint.body.getPosition().y;
		Body bbody;
		BodyDef	def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x/PPM,y/PPM);
		def.fixedRotation = true;
		bbody = world.createBody(def);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(1f/PPM);
		
		Fixture fix =bbody.createFixture(shape2, 1.0f);
		fix.setUserData("NpcBullet");
		fix.setSensor(true);
		shape2.dispose();		
		NpcBullet Npcbullet = new NpcBullet(startingPoint, bbody,Target);
		bbody.setUserData(Npcbullet);
		gameScreen.getProjectile().add(Npcbullet);
		
		return Npcbullet;
	}
	public Soldier createSoldier(int x, int y) {
		
		Body bbody;
		BodyDef	def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x/PPM,y/PPM);
		def.fixedRotation = true;
		bbody = world.createBody(def);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(3f/PPM);
		
		Fixture fix =bbody.createFixture(shape2, 1.0f);
		fix.setUserData("Npc");
		
		shape2.dispose();		
		Soldier soldier = new Soldier(bbody,100, 10, 0.8f, 3, 20,gameScreen);
		bbody.setUserData(soldier);
		gameScreen.getNpcs().add(soldier);
		return soldier;
	}

}
