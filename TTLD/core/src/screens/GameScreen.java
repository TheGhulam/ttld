package screens;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//import Scenes.Hud;
import com.ttld.game.ttld;
import gameObjects.*;
import levels.Level;

import static utils.Constants.PPM;

	public class GameScreen extends Screens {

		private Viewport gameport;
		//private Hud hud;

		private Box2DDebugRenderer b2dr;
		private OrthographicCamera camera;
		protected World world;
		private ArrayList<NPC> npcs = new ArrayList<NPC>();
		private ArrayList<Tower> towers = new ArrayList<Tower>();
		private Texture backgroundImage;
		private MouseHandler mH;
		private Player player;
		private Creator creator;
		private Base base;
		public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		public Level level;
		private NPC current;
		long elapsedTimeNpc;
		public Music gameplayMusic;

		//
		private Tower tower;
		private Boolean isClicked = false;
		private Table towersUI;
		private Table powerUpsUI;
		private Table towerTextTable;

		private Texture towerTexture;
		private Texture powerUp1Texture;
		private Texture powerUp2Texture;
		private Texture powerUp3Texture;
		private Texture powerUp4Texture;

		private TextButton towerText;
		private ImageButton tower1;
		private ImageButton powerUp1;
		private ImageButton powerUp2;
		private ImageButton powerUp3;
		private ImageButton powerUp4;

		//


		public GameScreen(ttld GameTTLD) {
			super(GameTTLD);
			gameplayMusic = Gdx.audio.newMusic(Gdx.files.internal("sfx/the-hunting-bm_menuMusic.wav"));
			gameplayMusic.setLooping(true);
			backgroundImage = new Texture("res/menu_background4.png");
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();
			camera = new OrthographicCamera();
			camera.setToOrtho(false,w/2,h/2);

			world = new World(new Vector2(0,0f),false);
			b2dr = new Box2DDebugRenderer();
			b2dr.setDrawBodies(false);

			this.world.setContactListener(new WorldContactListener());
			gameport= new FitViewport(ttld.width,ttld.height,camera);
			//hud = new Hud(app.batch);
			Gdx.input.setInputProcessor(mH);
			mH = new MouseHandler(this);
			player = new Player(this);
			creator = new Creator(this);
			base = creator.createBase();
			camera.position.set(gameport.getScreenWidth()/2, gameport.getScreenHeight()/2,0);
			//npcs.add(creator.createMelee(200, 50));


		}

		private void playBGM() {
			if(!gameplayMusic.isPlaying())
			{
				gameplayMusic.setVolume(ttldGame.menuScreen.bgm.getVolume());
				gameplayMusic.play();
			}
		}

		public void show() {

			super.show();
			stage.clear();

			towersUI = new Table();
			towersUI.setFillParent(true);

			powerUpsUI = new Table();
			powerUpsUI.setFillParent(true);

			towerTextTable = new Table();
			towerTextTable.setFillParent(true);

			loadUI(200,15);

			stage.addActor(towersUI);
			stage.addActor(powerUpsUI);
			stage.addActor(towerTextTable);

			playBGM();
		}

		private void loadUI(int length, int gapping){

			towerTexture = new Texture(Gdx.files.internal("res/base.png"));
			powerUp1Texture = new Texture(Gdx.files.internal("res/pngegg.png"));
			powerUp2Texture = new Texture(Gdx.files.internal("res/pngegg.png"));
			powerUp3Texture = new Texture(Gdx.files.internal("res/pngegg.png"));
			powerUp4Texture = new Texture(Gdx.files.internal("res/pngegg.png"));

			towerText = addTextButton("Choose Tower");

			tower1 = new ImageButton(
					new TextureRegionDrawable(new TextureRegion(towerTexture))
			);

			powerUp1 = new ImageButton(
					new TextureRegionDrawable(new TextureRegion(powerUp1Texture))
			);
			powerUp2 = new ImageButton(
					new TextureRegionDrawable(new TextureRegion(powerUp2Texture))
			);
			powerUp3 = new ImageButton(
					new TextureRegionDrawable(new TextureRegion(powerUp3Texture))
			);
			powerUp4 = new ImageButton(
					new TextureRegionDrawable(new TextureRegion(powerUp4Texture))
			);


			powerUp1.addListener(new ClickListener(){

			});

			powerUp2.addListener(new ClickListener(){

			});

			powerUp3.addListener(new ClickListener(){

			});

			powerUp4.addListener(new ClickListener(){

			});


			towerText.setTouchable(Touchable.disabled);

			towerTextTable.add(towerText);
			towersUI.add(tower1).width(length).padBottom(gapping);
			powerUpsUI.add(powerUp1);
			powerUpsUI.add(powerUp2);
			powerUpsUI.row();
			powerUpsUI.add(powerUp3);
			powerUpsUI.add(powerUp4);

			towerTextTable.setPosition(-500, -200);
			towersUI.setPosition(-500, -290);
			powerUpsUI.setPosition(- 340, -290);

		}
		public void update() {
			world.step(1/60f, 6, 2);

			cameraUpdate();
			playerUpdate();
			projectileUpdate();
			towerUpdate();
			npcUpdate();
			baseUpdate();

		}
		public void cameraUpdate() {
			float horizontalforce = 0;
			float verticalforce = 0;
			if(Gdx.input.isKeyPressed(Input.Keys.A)) {
				creator.createMelee(200, 10);

			}
			if(Gdx.input.isKeyPressed(Input.Keys.D)) {
				creator.createMelee(-200, -10);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.W)) {
				creator.createMelee(0, 200);
			}
			if(Gdx.input.isKeyPressed(Input.Keys.S)) {
				creator.createMelee(0,-200);
			}




		}
		@Override
		public void render(float delta) {
			Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			update();
			ttld.batch.begin();
			ttld.batch.draw(backgroundImage,0,0);
			if(base.health>0)
				ttld.batch.draw(base.getTexture(),635-base.getTexture().getWidth()/2,350-base.getTexture().getHeight()/2); //-base.getTexture().getHeight()/2
			for(NPC npc : npcs) {
				//System.out.println(npc.body.getPosition().x);
				//Melee NPC_M = (Melee) npc;
				ttld.batch.draw(npc.getCAnimation(),npc.body.getPosition().x*PPM+635,npc.body.getPosition().y*PPM+350);
			}
			ttld.batch.end();
			b2dr.render(world, camera.combined.cpy().scl(PPM));
			stage.draw();
		}

		@Override
		public void resize(int width, int height) {
			gameport.update(width, height);

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
		public ArrayList<NPC> getNpcs() {
			return npcs;
		}
		public ArrayList<Projectile> getProjectile() {
			return projectiles;
		}
		public Creator getCreator() {
			return creator;
		}
		public OrthographicCamera getCamera() {
			return camera;
		}
		public void baseUpdate() {
			NPC locked = null;
			try {

				if(base.isDead()) {

					Array<Fixture> fixtures = base.body.getFixtureList();
					for (int i = 0; i < fixtures.size; i++) {
						base.body.destroyFixture(fixtures.get(i));
					}
					return;

				}
				for(NPC npc: npcs) {
					if(locked == null) {
						Vector2 npcPosition = npc.body.getPosition();
						Vector2 towerPosition =base.body.getPosition();
						float distance = towerPosition.dst2(npcPosition);
						if(distance/PPM <= 41.9/PPM) {
							locked = npc;
							base.shoot(npc);
							if(npc.isDead()) {locked = null;}
						}
					}
				}


			}catch(ConcurrentModificationException e) {
				return;
			}

		}

		public void towerUpdate() {
			try {
				for(Tower tower: towers) {
					NPC locked = null;
					if(tower.isDead()) {
						towers.remove(tower);
						Array<Fixture> fixtures = tower.body.getFixtureList();
						for (int i = 0; i < fixtures.size; i++) {
							tower.body.destroyFixture(fixtures.get(i));
						}
						return;
					}

					for(NPC npc: npcs) {
						if(locked == null) {
							Vector2 npcPosition = npc.body.getPosition();
							Vector2 towerPosition = tower.body.getPosition();
							float distance = towerPosition.dst2(npcPosition);
							if(distance/PPM <= tower.shootingRadius/PPM) {
								locked = npc;
								tower.shoot(npc);
								if(npc.isDead()) {locked = null;}
							}
						}
					}

				}

			}catch(ConcurrentModificationException e) {
				return;
			}

		}

		public void npcUpdate() {

			Vector2 basePosition = base.body.getPosition();

			try {
				for(NPC npc: npcs) {
					elapsedTimeNpc = System.currentTimeMillis()-npc.time;
					current = npc;
					if(npc.isDead()) {
						npcs.remove(npc);
						Array<Fixture> fixtures = npc.body.getFixtureList();
						for (int i = 0; i < fixtures.size; i++) {
							npc.body.destroyFixture(fixtures.get(i));
						}
						return;
					}
					for(Tower tower: towers) {
						float towerdistance = tower.body.getPosition().dst2(npc.body.getPosition());
						if(towerdistance < 5 && npc.getNpcTarget() == null || towerdistance < 5 && tower.equals(npc.getNpcTarget())) {
							npc.setNpcTarget(tower);
							if(towerdistance >3) {
								npc.body.setLinearVelocity(targetToTower_(npc,tower));
								float angle = tower.body.getPosition().sub(npc.body.getPosition()).angleRad();
								npc.body.setTransform(npc.body.getPosition(), angle);

							}else {
								float angle = tower.body.getPosition().sub(npc.body.getPosition()).angleRad();
								npc.body.setTransform(npc.body.getPosition(), angle);
								npc.body.setLinearVelocity(0,0);

								if(elapsedTimeNpc > 2000) {
									npc.time = System.currentTimeMillis();
									npc.attack(tower);

								}
							}

						}
						try {
							if(npc.getNpcTarget().isDead()) {npc.resetNpcTarget();}
						}catch(NullPointerException e) {
							continue;
						}


					}
					if(npc.getNpcTarget() == null || npc.getNpcTarget().isDead()){
						npc.resetNpcTarget();
						Vector2 vector =targetToBase(npc);
						float distance = basePosition.dst2(npc.body.getPosition());


						if(distance/PPM> 4/PPM) {
							npc.body.setLinearVelocity(vector);
							float angle = base.body.getPosition().sub(npc.body.getPosition()).angleRad();
							npc.body.setTransform(npc.body.getPosition(), angle);
						}else {
							npc.body.setLinearVelocity(0,0);
							float angle = base.body.getPosition().sub(npc.body.getPosition()).angleRad();
							npc.body.setTransform(npc.body.getPosition(), angle);
							if(elapsedTimeNpc > 2000) {
								npc.time = System.currentTimeMillis();
								npc.attack(base);
							}
						}
					}
				}
			}catch(ConcurrentModificationException e) {

				return;
			}

		}



		public void projectileUpdate() {
			try {
				for(Projectile proj : projectiles) {
					if(proj.isShot) {
						proj.is_Shot();
						projectiles.remove(proj);
					}else if(proj.missed()){

						projectiles.remove(proj);
					}else {
						proj.shootToTarget();
					}

				}

			}catch(ConcurrentModificationException e) {
				return;
			}


		}

		float localX = 0;
		float localY = 0;

		public void playerUpdate() {


			tower1.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					isClicked = true;
					localX = x;
					localY = y;
					towerTextTable.removeActor(towerText);
					towerText = addTextButton("Put The Tower");
					towerTextTable.add(towerText);
				}
			});


			if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)   && isClicked == true) {

				Vector3 mousePosition = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
				camera.unproject(mousePosition);
				player.plantTower(mousePosition);
				isClicked = false;
				towerTextTable.removeActor(towerText);
				towerText = addTextButton("Choose Tower");
				towerTextTable.add(towerText);
			}

		}

		public Vector2 targetToBase(NPC e) {
			Vector2 basePosition = base.body.getPosition();
			float targetX = basePosition.x;
			float targetY = basePosition.y;
			float followerX = e.body.getPosition().x;
			float followerY = e.body.getPosition().y;
			float vectorX_ =(targetX -followerX);
			float vectorY_ = (targetY - followerY);
			double unitDivisor_ = Math.sqrt((double)(vectorX_*vectorX_) + (double)(vectorY_*vectorY_));

			Vector2 vector = new Vector2((vectorX_)/(float)unitDivisor_,(vectorY_)/(float)unitDivisor_);
			return vector;
		}

		public Vector2 targetToTower_(NPC npc, Tower tower) {

			float vectorX =(tower.body.getPosition().x-npc.body.getPosition().x);
			float vectorY = (tower.body.getPosition().y-npc.body.getPosition().y);
			double unitDivisor = Math.sqrt((double)(vectorX*vectorX) + (double)(vectorY*vectorY));
			Vector2 vectorT = new Vector2((tower.body.getPosition().x-npc.body.getPosition().x)/(float)(unitDivisor),(tower.body.getPosition().y-npc.body.getPosition().y)/(float)(unitDivisor));
			return vectorT;
		}

}
