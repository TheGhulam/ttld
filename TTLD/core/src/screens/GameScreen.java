package screens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import Scenes.Hud;
import com.ttld.game.ttld;
import gameObjects.*;

import levels.Level;
import scenes.Hud;

import static utils.Constants.PPM;

public class GameScreen extends Screens {

	private Viewport gameport;
	//private Hud hud;
	private Box2DDebugRenderer b2dr;
	private OrthographicCamera camera;
	protected World world;
	public ArrayList<NPC> npcs = new ArrayList<NPC>();
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	private Texture backgroundImage;
	private MouseHandler mH;
	private Player player;
	private Creator creator;
	private Base base;
	public ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private NPC current;
	long elapsedTimeNpc;
	long elapsedTime;
	public Music gameplayMusic;
	long initialTime;
	long timer;
	long currency = 1000;
	long npcKillReward = 150;
	long towerPrice = 500;
	long powerupPrice = 250;

	//
	private Boolean isClicked = false;
	private Table towersUI;
	private Table powerUpsUI;
	private Table currencyUI;

	private Table currencyTextTable;
	private ImageButton tower1;
	private ImageButton airStrike;
	private ImageButton boomingEconomy;
	private ImageButton healthPotion;
	private ImageButton towerUpgrade;
	private TextButton currencyText;
	private ImageButton currencyImage;
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
		//hud = new Hud(ttld.batch);
		Gdx.input.setInputProcessor(mH);
		mH = new MouseHandler(this);
		player = new Player(this);
		creator = new Creator(this);
		base = creator.createBase();
		camera.position.set(gameport.getScreenWidth()/2, gameport.getScreenHeight()/2,0);
		initialTime = System.currentTimeMillis();

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

		currencyUI = new Table();
		currencyUI.setFillParent(true);

		currencyTextTable = new Table();
		currencyTextTable.setFillParent(true);

		loadUI(200,15);

		stage.addActor(towersUI);
		stage.addActor(powerUpsUI);
		stage.addActor(currencyUI);
		stage.addActor(currencyTextTable);

		playBGM();

	}

	private void loadUI(int length, int gapping){


		tower1 = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/B18_smaller.png"))))
		);

		airStrike = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/airstrike.png"))))
		);
		boomingEconomy = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/economy.png"))))
		);
		healthPotion = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/health.png"))))
		);
		towerUpgrade= new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/upgrade.png"))))
		);

		currencyText = addTextButton(Long.toString(currency));
		currencyImage = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/coin.png"))))
		);

		currencyText.setTouchable(Touchable.disabled);

		towersUI.add(tower1);
		powerUpsUI.add(airStrike);
		powerUpsUI.add(boomingEconomy);
		powerUpsUI.row();
		powerUpsUI.add(healthPotion);
		powerUpsUI.add(towerUpgrade);
		currencyUI.add(currencyImage);
		currencyTextTable.add(currencyText);

		towersUI.setPosition(-500, -290);
		powerUpsUI.setPosition(- 340, -290);
		currencyUI.setPosition(-500, 300);
		currencyTextTable.setPosition(-420,300);
	}

	public void update() {
		world.step(1/60f, 6, 2);

		cameraUpdate();
		playerUpdate();
		projectileUpdate();
		towerUpdate();
		npcUpdate();
		baseUpdate();
		spawnUpdate();
		powerupUpdate();

	}
	public void cameraUpdate() {
		float horizontalforce = 0;
		float verticalforce = 0;
	}

	public void powerupUpdate(){
		airStrike.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				powerupAirStrike();
			}
		});

		boomingEconomy.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				powerupBoomingEconomy();
			}
		});

		healthPotion.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				powerupHealthPotion();
			}
		});

		towerUpgrade.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				powerupTowerUpgrade();
			}
		});
	}

	public boolean isGameOver() {
		return base.health<=0;
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			ttldGame.setScreen(ttldGame.pauseScreen);
		}
		if(isGameOver()) {
			ttldGame.gameScreen = new GameScreen(super.ttldGame);
			ttldGame.setScreen(new EndScreen(super.ttldGame));
			this.dispose();
		}
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		ttld.batch.begin();
		ttld.batch.draw(backgroundImage,0,0);
		//ttld.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		if(base.health > 0) {
			ttld.batch.draw(base.getTexture(), 645 - base.getTexture().getWidth() / 4, 360 - base.getTexture().getHeight() / 4,base.getTexture().getWidth()/2,base.getTexture().getHeight()/2);//-base.getTexture().getHeight()/2
		}
		for(Tower tower : towers) {
			ttld.batch.draw(tower.getTex(),tower.body.getPosition().x*PPM+635-tower.getTex().getWidth()/4,tower.body.getPosition().y*PPM+350-tower.getTex().getHeight()/4,tower.getTex().getWidth()/2,tower.getTex().getHeight()/2);
		}
		for(NPC npc : npcs) {
			//System.out.println(npc.body.getPosition().x);
			//Melee NPC_M = (Melee) npc;
			ttld.batch.draw(npc.getCAnimation(),npc.body.getPosition().x*PPM+635,npc.body.getPosition().y*PPM+350);
		}
		for(Projectile pr : projectiles) {
			ttld.batch.draw(pr.getTex(),pr.bullet.getPosition().x*PPM+635,pr.bullet.getPosition().y*PPM+350);
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
		backgroundImage.dispose();
		gameplayMusic.dispose();
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
						if(npc.isDead()) {
							//////////
							locked = null;

						}
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
				ArrayList<NPC> npcs_ = npcs;
				Collections.shuffle(npcs_);
				for(NPC npc: npcs_) {
					if(locked == null) {

						Vector2 npcPosition = npc.body.getPosition();
						Vector2 towerPosition = tower.body.getPosition();
						float distance = towerPosition.dst2(npcPosition);
						if(distance/PPM <= tower.shootingRadius/PPM) {
							locked = npc;
							tower.shoot(npc);
							if(npc.isDead()) {
								////////////////
								locked = null;


							}
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
					currency += 150;

					currencyTextTable.removeActor(currencyText);
					currencyText = addTextButton("" + currency);
					currencyTextTable.add(currencyText);

					if(currency >= towerPrice){

						towersUI.removeActor(tower1);

						tower1 = new ImageButton(
								new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/B18.png"))))
						);

						towersUI.add(tower1);
						towersUI.setPosition(-500, -290);
					}

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

				towersUI.removeActor(tower1);
				tower1 = new ImageButton(
						new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/B18.png"))))
				);

				towersUI.add(tower1);
				towersUI.setPosition(-500, -290);

			}
		});


		if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)   && isClicked == true) {
			if (currency >= towerPrice){
				Vector3 mousePosition = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
				camera.unproject(mousePosition);
				player.plantTower(mousePosition);
				currency -= towerPrice;

				currencyTextTable.removeActor(currencyText);
				currencyText = addTextButton("" + currency);
				currencyTextTable.add(currencyText);

				isClicked = false;

				towersUI.removeActor(tower1);
				if(currency>= towerPrice){
					tower1 = new ImageButton(
							new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/B18.png"))))
					);
				}
				else if(currency < towerPrice){
					tower1 = new ImageButton(
							new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("res/B18.png"))))
					);
				}
				towersUI.add(tower1);
				towersUI.setPosition(-500, -290);

			} else{
				// Maybe also show a warning that we don't have enough money
				isClicked = false;

			}

		}
		try{
			elapsedTime = System.currentTimeMillis()-initialTime;
			if(base.health > 0 && npcs.size() < 100 && elapsedTime<1000*60){
				/**
				 Random rand = new Random();
				 int xCord = rand.nextInt(150);
				 int yCord = rand.nextInt(150);
				 boolean randomX = rand.nextBoolean();
				 boolean randomY = rand.nextBoolean();
				 int signX = 1, signY = 1;
				 if(randomX)
				 signX = -1;
				 if(randomY)
				 signY = -1;

				 creator.createMelee(signX * (225 + xCord), signY * (225 + yCord));
				 */
			}
		}

		catch(ConcurrentModificationException e){
			return;
		}
	}

	private void spawnUpdate() {
		elapsedTime = System.currentTimeMillis()-initialTime;
		if(elapsedTime>=2000) {
			initialTime = System.currentTimeMillis();
			creator.createMelee(-640,360);
			creator.createMelee(-640,288);
			creator.createMelee(-640,216);
			//creator.createMelee(-640,144);
			//creator.createMelee(-640,72);
			//creator.createMelee(-640,0);
			//creator.createMelee(-640,-360);
			//creator.createMelee(-640,-288);
			//creator.createMelee(-640,-216);
			//creator.createMelee(-640,-144);
			//creator.createMelee(-640,-72);
			//creator.createMelee(-640,0);
			//creator.createMelee(-640,-360);
			//creator.createMelee(-640,0);
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

	/**
	 * Powerups
	 */
	public void powerupHealthPotion(){
		if (currency >= powerupPrice){
			currency -= powerupPrice;
			try {
				base.setHealth(7000);
				for (Tower tower: towers){
					tower.setHealth(2000);
				}
			}catch(ConcurrentModificationException e){
				return;
			}
		}
	}

	public void powerupAirStrike(){
		if (currency >= powerupPrice) {
			Vector2 basePosition = base.body.getPosition();
			currency -= powerupPrice;
			try {
				for (NPC npc : npcs) {
					float distance = basePosition.dst2(npc.body.getPosition());

					if (distance / PPM < 256 / PPM) {
						npcs.remove(npc);
						currency += npcKillReward;
					}
				}
			} catch (ConcurrentModificationException e) {
				return;
			}
		}
	}

	public void powerupBoomingEconomy(){
		if (currency >= powerupPrice){
			currency -= powerupPrice;
			npcKillReward += 50;
		}
	}

	public void powerupTowerUpgrade(){
		if (currency >= powerupPrice){
			currency -= powerupPrice;
			try {
				for (Tower tower: towers){
					tower.setHealth((int)(tower.getHealth()*1.1));
					tower.setDamage((int)(tower.getDamage()*1.1));
				}
			}catch(ConcurrentModificationException e){
				return;
			}
		}
	}
}
