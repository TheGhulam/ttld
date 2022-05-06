package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;






import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

import gameObjects.Npc;
import gameObjects.Projectile;
import gameObjects.Tower;

import com.badlogic.gdx.*;
import static utils.Constants.PPM;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;



public class Application extends Game{
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public SpriteBatch batch;
	public Application() {
		
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		
		Level1 level = new Level1();
		GameScreen gameScreen = new GameScreen();
		setScreen(gameScreen);
	}
	
	@Override
	public void render() {
		super.render();
	}
	
//	@Override
//	public void create() {
//		float w = Gdx.graphics.getWidth();
//		float h = Gdx.graphics.getHeight();
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false,w/2,h/2);
//		
//		world = new World(new Vector2(0,0f),false);
//		b2dr = new Box2DDebugRenderer();
//		
//	}
//	@Override
//	public void render() {
//		//
//		
//		if(System.nanoTime()-one<=1000)
//		{
//			h+=1; 
//		}
//		else {
//			one = System.nanoTime();
//			System.out.println("FPS : " + h);
//			h=0;
//		}
//		
//		//
//		update(Gdx.graphics.getDeltaTime());
//		
//		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		b2dr.render(world, camera.combined.scl(PPM));
//		
//	}
	
//	public void pause() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void resume() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void dispose() {
//		world.dispose();
//		b2dr.dispose();
//	}
//
//	@Override
//	public void resize(int width, int height) {
//		// TODO Auto-generated method stub
//		camera.setToOrtho(false,width/2,height/2);
//	}
//	public void update(float delta) {
//		world.step(1/60f, 6, 2);
//		inputUpdate(delta);
//		followerUpdate(delta,npcs, towers);
//		towerUpdate(delta);
//		cameraUpdate(delta);
//	}
//	public void followerUpdate(float delta, ArrayList<Body> npcs, ArrayList<Body>towers) {
//		boolean targetToTower = false;
//	
//		Vector2 targetposition = player.getPosition();
//		for(Body npc : npcs) {
//			Vector2 followerposition = npc.getPosition();
//			try {
//			for(Body tower : towers) {
//				Vector2 towerposition = tower.getPosition();
//				float vectorX =(tower.getPosition().x-npc.getPosition().x);
//				float vectorY = (tower.getPosition().y-npc.getPosition().y);
//				double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
//				float towerdistance = towerposition.dst2(followerposition);
//				if(towerdistance < 10) {
//					
//					Vector2 vectorT = new Vector2(0.3f*(tower.getPosition().x-npc.getPosition().x)/(float)(unitDivisor),0.3f*(tower.getPosition().y-npc.getPosition().y)/(float)(unitDivisor));
//					if(towerdistance > 0.8) {
//						targetToTower = true;
//						npc.setLinearVelocity(vectorT);
//						
//					}else {
//						targetToTower = true;
//						npc.setLinearVelocity(0,0);
////						towers.remove(tower);
////						
////						Array<Fixture> fixtures = tower.getFixtureList();
////						for (int i = 0; i < fixtures.size; i++) {
////							tower.destroyFixture(fixtures.get(i));
////						}
//					
//					}
//				}
//			}
//			}catch(ConcurrentModificationException e) {
//				break;
//			}
//			if(!targetToTower) {
//				Vector2 followerposition2 = npc.getPosition();
//				float targetX = targetposition.x;
//				float targetY = targetposition.y;
//				float followerX = followerposition2.x;
//				float followerY = followerposition2.y;
//				float vectorX =(targetX -followerX);
//				float vectorY = (targetY - followerY);
//				double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
//				float distance = targetposition.dst2(followerposition2);
//				Vector2 vector = new Vector2((targetX-followerX)/(float)unitDivisor,(targetY-followerY)/(float)unitDivisor);
//				
//				
//				if(distance > 2) {
//					npc.setLinearVelocity(vector);
//					
//				
//				}else if(distance<2 ) {
//					npc.setLinearVelocity(0,0);
//				}
//			}
//			targetToTower = false;
//		
//		}
//		
//		
//		
//	}
//	
//	
//	public void inputUpdate(float delta) {
//		int horizontalforce = 0;
//		int verticalforce = 0;
//		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
//			horizontalforce -= 1;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
//			horizontalforce += 1;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
//			verticalforce +=1;
//		}
//		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
//			verticalforce -=1;
//		}
//		if(Gdx.input.isKeyJustPressed(Input.Keys.F)) {
//			for(Body npc : npcs) {
//				Vector2 forceVector = npc.getLinearVelocity();
//				
//				float angle =player.getPosition().sub(npc.getPosition()).angleRad();
//				float x =  npc.getPosition().x;
//				float y = npc.getPosition().y;
//				float vectorX = -(player.getPosition().x -x);
//				float vectorY = -(player.getPosition().y - y);
//				double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
//				Vector2 forceVector2 = new Vector2(100*vectorX/(float)(unitDivisor),100*vectorY/(float)(unitDivisor));
//				npc.setTransform(npc.getPosition(),angle);
//				npc.applyForceToCenter(forceVector2, true);
//			}
//			float angle2 = player.getPosition().sub(tower1.getPosition()).angleRad();
//			tower1.setTransform(tower1.getPosition(),angle2);
//		}
//		player.setLinearVelocity(horizontalforce*5, verticalforce*5);
//		
//	}
//	
//	
//	public void cameraUpdate(float delta) {
//		Vector3 position = camera.position;
//		position.x = player.getPosition().x*PPM;
//		position.y = player.getPosition().y*PPM;
//		camera.position.set(position);
//		
//		camera.update();
//	}
//	public void towerUpdate(float delta) {
//		float angle2 = player.getPosition().sub(tower1.getPosition()).angleRad();
//		tower1.setTransform(tower1.getPosition(),angle2);
//	}
//	
//
}

