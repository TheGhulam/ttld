package Scene;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Level;

import gameObjects.Npc;
import gameObjects.Projectile;
import gameObjects.Tower;

public class GameScreen extends Screens {
	
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	protected World world;
	private ArrayList<Npc> npcs = new ArrayList<Npc>();
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	public Level level;
	
	public GameScreen(Box2DDebugRenderer b2dr, OrthographicCamera camera,World world, Level level) {
		this.b2dr = b2dr;
		this.camera = camera;
		this.world = world;
		this.level = level;
	}
	
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	public void TowerUpdate() {
		
	}
	
	public void NPCupdate() {
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	public World getWorld() {
		return world;
	}
	public ArrayList<Tower> getTowers() {
		return towers;
	}
	public ArrayList<Npc> getNpcs() {
		return npcs;
	}
	public ArrayList<Projectile> getProjectile() {
		return projectiles;
	}
}
