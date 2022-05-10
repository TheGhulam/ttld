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

public class WinScreen extends Screens{

    public Sound effect;
    public float effectVolume;
    private Table uiElements;

    public TextButton newGame, mainMenu, gameEndText;
    public Texture backgroundImage;

    Music bgm;

    public WinScreen(ttld ttldGame) {
        super(ttldGame);
        backgroundImage = new Texture("res/backgrounds/serdar0SM09.png");
        effect = Gdx.audio.newSound(Gdx.files.internal("sfx/rollOverSoundEff.wav"));
        bgm = Gdx.audio.newMusic(Gdx.files.internal("sfx/BGM_WIN.wav"));
        font.getData().setScale(3, 3);
        bgm.setVolume(ttldGame.menuScreen.bgmVolume);
        effectVolume = ttldGame.menuScreen.effectVolume;
    }

    private WinScreen callClass() {
        return this;
    }


    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 1);
        ttld.batch.setProjectionMatrix(gameCam.combined);

        // Background Image
        ttld.batch.begin();
        ttld.batch.draw(backgroundImage, 0, 0);
        ttld.batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void playBGM() {
        if(!bgm.isPlaying())
            bgm.play();
    }

    @Override
    public void show() {
        super.show();
        stage.clear();

        uiElements = new Table();
        uiElements.setFillParent(true);
        loadUI(300, 15);
        stage.addActor(uiElements);
        playBGM();
    }


    private void loadUI(int length, int gapping) {


        gameEndText = addTextButton("YOU WON!");
        newGame = addTextButton("REPLAY");
        mainMenu = addTextButton("RETURN TO MAIN MENU");

        newGame.addListener(new ClickListener() {
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

        mainMenu.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                effect.play(effectVolume);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(ttldGame.menuScreen);
                bgm.stop();
                stage.clear();
            }
        });
        gameEndText.setTouchable(Touchable.disabled);
        gameEndText.setColor(Color.BLUE);
        uiElements.add(gameEndText).width(length).pad(4*gapping);
        uiElements.row();
        uiElements.add(newGame).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(mainMenu).width(length).padBottom(gapping);
        uiElements.row();
    }

    //Done
    @Override
    public void resize(int width, int height) {
        port.update(width, height);
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
        effect.dispose();
    }
}
