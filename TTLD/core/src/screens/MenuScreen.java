package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ttld.game.ttld;

public class MenuScreen extends Screens{

        //CREATE A METHOD THAT CREATES BUTTONS IN SCREENS CLASS - DONE!

    //UI ELEMENTS
    private TextButton title,play,settings,credits,quit;
    private Table uiElements;
    //BACKGROUND IMAGE
    private Texture backgroundImage;
    //SOUND EFFECTS & MUSIC
    public Sound effect;
    public float effectVolume;
    public Music bgm;
    public float bgmVolume;

    public MenuScreen(ttld ttldGame) {
        super(ttldGame);
        backgroundImage = new Texture("res/backgrounds/menu_background4.png");
        effect = Gdx.audio.newSound(Gdx.files.internal("sfx/rollOverSoundEff.wav"));
        bgm = Gdx.audio.newMusic(Gdx.files.internal("sfx/fugue-rott_alternateMenuMusic.wav"));
        font.getData().setScale(3,3);
        effectVolume = 0.07f;
        bgmVolume = 0.07f;
        bgm.setVolume(bgmVolume);
        bgm.setLooping(true);
    }

    private MenuScreen callClass() {
        return this;
    }

    private void playBGM() {
        if(!bgm.isPlaying())
            bgm.play();
    }

    @Override
    public void show() {
        super.show();
        stage.clear();

            // This is similar to the create method of the game class

        uiElements = new Table(); // As far as I am concerned this is something similar to JPanel
        uiElements.setFillParent(true); // Explanation is pretty clear just hover over it in IntelliJ
        loadUI(300,15);
        stage.addActor(uiElements); // stage is the class that handles UI elements
        playBGM();
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
        //TITLE
        //font.draw(ttld.batch,"TTLD",100,callClass().stage.getHeight()-100*font.getScaleY());
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

        title = addTextButton("TILL THE LAST DROP");
        play = addTextButton("PLAY");
        settings = addTextButton("SETTINGS");
        credits = addTextButton("GITHUB");
        quit = addTextButton("QUIT");

            //Adding listeners

        play.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(ttldGame.gameScreen);
                bgm.stop();
                stage.clear();
            }
        });
        settings.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(new SettingScreen(ttldGame,callClass()));
            }
        });
        credits.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://github.com/gahme/ttld");
                //Gdx.net.openURI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            }
        });
        quit.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        title.setTouchable(Touchable.disabled);
        title.setColor(Color.BLUE);

        //Adding them to the table

        uiElements.add(title).padBottom(40);
        uiElements.row();
        uiElements.add(play).width(length).padBottom(gapping);
        uiElements.row();
        //uiElements.add(continueB).width(length).padBottom(gapping);
        //uiElements.row();
        uiElements.add(settings).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(credits).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(quit).width(length).padBottom(gapping);

    }

    public float getBGMVolume() {
        return bgmVolume;
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
