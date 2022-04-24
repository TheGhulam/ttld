package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;




import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.*;
import static utils.Constants.PPM;

import entity.Npc;
import entity.Projectile;
import entity.Tower;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;



public class Application extends ApplicationAdapter{
	private boolean DEBUG = false;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	protected World world;
	private Body player;
	
	public static long one = System.currentTimeMillis();
	public static int h=0;
	private Body box;
	private Body tower1;
	private ArrayList<Npc> npcs = new ArrayList<Npc>();
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public Application() {
		
	}
	
	
	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w/2,h/2);
		
		world = new World(new Vector2(0,0f),false);
		b2dr = new Box2DDebugRenderer();
		player = createBox(0,0,32,32,false,false);
		npcs.add (createBox(-300,-300,8,8,false,true));
		npcs.add (createBox(300,300,8,8,false,true));
		
		npcs.add (createBox(0,-300,8,8,false,true));
		npcs.add (createBox(0,300,8,8,false,true));
		npcs.add (createBox(300,0,8,8,false,true));
		npcs.add (createBox(-300,300,8,8,false,true));
		npcs.add (createBox(120,-100,8,8,false,true));
		npcs.add (createBox(80,130,8,8,false,true));
		npcs.add (createBox(40,-120,8,8,false,true));
		npcs.add (createBox(220,-45,8,8,false,true));
		npcs.add (createBox(180,200,8,8,false,true));
		npcs.add (createBox(-40,-200,8,8,false,true));
		npcs.add (createBox(-200,-45,8,8,false,true));
		box = createBox(1,2,40,40,true,false);
		tower1 = createBox(120,120,20,20,true,true);
		Body tower2 = createBox(-120,-120,20,20,true,false);
		towers.add(tower1);
		towers.add(tower2);
//		for(Body body: npcs) {
//			markAsSensor(body);
//		}
	}
	@Override
	public void render() {
		//
		
		if(System.nanoTime()-one<=1000)
		{
			h+=1; 
		}
		else {
			one = System.nanoTime();
			System.out.println("FPS : " + h);
			h=0;
		}
		
		//
		update(Gdx.graphics.getDeltaTime());
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		b2dr.render(world, camera.combined.scl(PPM));
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		world.dispose();
		b2dr.dispose();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		camera.setToOrtho(false,width/2,height/2);
	}
	public void update(float delta) {
		world.step(1/60f, 6, 2);
		inputUpdate(delta);
		followerUpdate(delta,npcs, towers);
		towerUpdate(delta);
		cameraUpdate(delta);
	}
	public void followerUpdate(float delta, ArrayList<Body> npcs, ArrayList<Body>towers) {
		boolean targetToTower = false;
	
		Vector2 targetposition = player.getPosition();
		for(Body npc : npcs) {
			Vector2 followerposition = npc.getPosition();
			try {
			for(Body tower : towers) {
				Vector2 towerposition = tower.getPosition();
				float vectorX =(tower.getPosition().x-npc.getPosition().x);
				float vectorY = (tower.getPosition().y-npc.getPosition().y);
				double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
				float towerdistance = towerposition.dst2(followerposition);
				if(towerdistance < 10) {
					
					Vector2 vectorT = new Vector2(0.3f*(tower.getPosition().x-npc.getPosition().x)/(float)(unitDivisor),0.3f*(tower.getPosition().y-npc.getPosition().y)/(float)(unitDivisor));
					if(towerdistance > 0.8) {
						targetToTower = true;
						npc.setLinearVelocity(vectorT);
						
					}else {
						targetToTower = true;
						npc.setLinearVelocity(0,0);
//						towers.remove(tower);
//						
//						Array<Fixture> fixtures = tower.getFixtureList();
//						for (int i = 0; i < fixtures.size; i++) {
//							tower.destroyFixture(fixtures.get(i));
//						}
					
					}
				}
			}
			}catch(ConcurrentModificationException e) {
				break;
			}
			if(!targetToTower) {
				Vector2 followerposition2 = npc.getPosition();
				float targetX = targetposition.x;
				float targetY = targetposition.y;
				float followerX = followerposition2.x;
				float followerY = followerposition2.y;
				float vectorX =(targetX -followerX);
				float vectorY = (targetY - followerY);
				double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
				float distance = targetposition.dst2(followerposition2);
				Vector2 vector = new Vector2((targetX-followerX)/(float)unitDivisor,(targetY-followerY)/(float)unitDivisor);
				
				
				if(distance > 2) {
					npc.setLinearVelocity(vector);
					
				
				}else if(distance<2 ) {
					npc.setLinearVelocity(0,0);
				}
			}
			targetToTower = false;
		
		}
		
		
		
	}
	
	
	public void inputUpdate(float delta) {
		int horizontalforce = 0;
		int verticalforce = 0;
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			horizontalforce -= 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			horizontalforce += 1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			verticalforce +=1;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			verticalforce -=1;
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
			for(Body npc : npcs) {
				Vector2 forceVector = npc.getLinearVelocity();
				
				float angle =player.getPosition().sub(npc.getPosition()).angleRad();
				float x =  npc.getPosition().x;
				float y = npc.getPosition().y;
				float vectorX = -(player.getPosition().x -x);
				float vectorY = -(player.getPosition().y - y);
				double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
				Vector2 forceVector2 = new Vector2(100*vectorX/(float)(unitDivisor),100*vectorY/(float)(unitDivisor));
				npc.setTransform(npc.getPosition(),angle);
				npc.applyForceToCenter(forceVector2, true);
			}
			float angle2 = player.getPosition().sub(tower1.getPosition()).angleRad();
			tower1.setTransform(tower1.getPosition(),angle2);
		}
		player.setLinearVelocity(horizontalforce*5, verticalforce*5);
		
	}
	
	
	public void cameraUpdate(float delta) {
		Vector3 position = camera.position;
		position.x = player.getPosition().x*PPM;
		position.y = player.getPosition().y*PPM;
		camera.position.set(position);
		
		camera.update();
	}
	public void towerUpdate(float delta) {
		float angle2 = player.getPosition().sub(tower1.getPosition()).angleRad();
		tower1.setTransform(tower1.getPosition(),angle2);
	}
	
//	public Body createBox(int x,int y,int width,int height, boolean isStatic, boolean isNpc) {
//		Body pbody;
//		BodyDef	def = new BodyDef();
//		if(isStatic) {
//			def.type = BodyDef.BodyType.StaticBody;
//		}else {
//			def.type=BodyDef.BodyType.DynamicBody;
//		}
//		
//		def.position.set(x/PPM,y/PPM);
//		def.fixedRotation = true;
//		pbody = world.createBody(def);
//		if(isNpc) {
//			CircleShape shape2 = new CircleShape();
//			shape2.setRadius(8f/PPM);
//			
//			pbody.createFixture(shape2, 1.0f);
//			shape2.dispose();		
//			}else {
//			PolygonShape shape = new PolygonShape();
//			
//			shape.setAsBox(width/2 / PPM, height/2 / PPM);
//			pbody.createFixture(shape, 1.0f);
//			shape.dispose();
//			}
//		
//		
//		
//		
//		return pbody;
//		
//	}
	public void markAsSensor (Body character) {
		Array<Fixture> fixtures = character.getFixtureList();
		for (int i = 0; i < fixtures.size; i++) {
			fixtures.get(i).setSensor(true);
		}
	}
	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}
	public void setNpcs(ArrayList<Npc> npcs) {
		this.npcs = npcs;
	}

}

