package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ttld.game.ttld;

public class MenuScreen extends Screens{

        //CREATE A METHOD THAT CREATES BUTTONS IN SCREENS CLASS - DONE!

    //UI ELEMENTS
    private TextButton newGame,continueB,settings,credits,quit;
    private Table uiElements;
    //BACKGROUND IMAGE
    private Texture backgroundImage;
    //SOUND EFFECTS & MUSIC
    public Sound effect;
    public float effectVolume;
    public Music bgm;
    public float bgmVolume;
    private MenuScreen thisRefersTo;

    public MenuScreen(ttld ttldGame) {
        super(ttldGame);
        thisRefersTo = this;
        //hud = new Hud(ttld.batch);
    }

    @Override
    public void show() {

            // This is similar to the create method of the game class

        uiElements = new Table(); // As far as I am concerned this is something similar to JPanel
        uiElements.setFillParent(true); // Explanation is pretty clear just hover over it in IntelliJ
        loadUI(300,15);
        stage.addActor(uiElements); // stage is the class that handles UI elements

        backgroundImage = new Texture("menu_background3.png");

        effect = Gdx.audio.newSound(Gdx.files.internal("rollOverSoundEff.wav"));
        bgm = Gdx.audio.newMusic(Gdx.files.internal("fugue-rott_alternateMenuMusic.wav"));

        effectVolume = 0.03f;
        bgmVolume = 0.03f;
        bgm.setVolume(bgmVolume);
        bgm.setLooping(true);
        bgm.play();

    }

    @Override
    public void render(float delta) {

        // How many times render is called depends on the FPS
        // Therefore we need a delta time : (multiply anything that changing has x and y coordinates with Gdx.graphics.getDeltaTime())
        // For example : x = SPEED * Gdx.graphics.getDeltaTime()

        ScreenUtils.clear(0, 0, 0, 1); //Clears the screen
        ttld.batch.setProjectionMatrix(gameCam.combined); // Tell the game batch where the camera is and render only what camera can see

        // Background Image
        ttld.batch.begin();
        ttld.batch.draw(backgroundImage,0,0);
        ttld.batch.end();

        //Functional Buttons
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw(); //Draw the specified UI

    }

    @Override
    public void resize(int width, int height) {
        port.update(width,height);
    }

    private void loadUI(int length, int gapping) {

            //Initializing text buttons

        newGame = addTextButton("NEW GAME");
        continueB = addTextButton("CONTINUE");
        settings = addTextButton("SETTINGS");
        credits = addTextButton("GITHUB");
        quit = addTextButton("QUIT");

            //Adding listeners

        newGame.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(new GameScreen(ttldGame));
            }
        });
        continueB.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(new GameScreen(ttldGame));
            }
        });
        settings.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(new SettingScreen(ttldGame,thisRefersTo));
            }
        });
        credits.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        quit.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

            //Adding them to the table

        uiElements.add(newGame).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(continueB).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(settings).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(credits).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(quit).width(length).padBottom(gapping);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundImage.dispose();
        bgm.dispose();
        effect.dispose();
    }
}
