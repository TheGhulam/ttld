package entity;

import static utils.Constants.PPM;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.mygdx.game.Application;

public class Creator extends Application{
	
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
		
		tbody.createFixture(shape2, 1.0f);
		shape2.dispose();		
		
		DoomTower dt = new DoomTower(tbody, 2000, 10, 0.3f, 25);
		
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
		shape2.setRadius(45f/PPM);
		
		tbody.createFixture(shape2, 1.0f);
		shape2.dispose();		
		
		Base base = new Base(tbody, 7000, 20, 0.1f, 50);
		
		return base;
		
		
		
		
	}
	public Bullet createBullet(Entity startingPoint ,Entity Target) {
		float x = startingPoint.get_Position().x;
		float y = startingPoint.get_Position().y;
		Body bbody;
		BodyDef	def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x/PPM,y/PPM);
		def.fixedRotation = true;
		bbody = world.createBody(def);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(1f/PPM);
		
		bbody.createFixture(shape2, 1.0f);
		shape2.dispose();		
		Bullet bullet = new Bullet(startingPoint, bbody,Target);
		return bullet;
	}
	public NpcBullet createNpcBullet(Entity startingPoint ,Entity Target) {
		float x = startingPoint.get_Position().x;
		float y = startingPoint.get_Position().y;
		Body bbody;
		BodyDef	def = new BodyDef();
		def.type = BodyDef.BodyType.DynamicBody;
		def.position.set(x/PPM,y/PPM);
		def.fixedRotation = true;
		bbody = world.createBody(def);
		CircleShape shape2 = new CircleShape();
		shape2.setRadius(0.6f/PPM);
		
		bbody.createFixture(shape2, 1.0f);
		shape2.dispose();		
		NpcBullet Npcbullet = new NpcBullet(startingPoint, bbody,Target);
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
		shape2.setRadius(8f/PPM);
		
		bbody.createFixture(shape2, 1.0f);
		shape2.dispose();		
		Soldier soldier = new Soldier(bbody,100, 10, 0.8f, 3, 20);
		return soldier;
	}

}
