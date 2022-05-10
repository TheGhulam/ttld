package screens;

import com.badlogic.gdx.Gdx;
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

public class PauseSettingScreen extends Screens {
    private Texture backgroundImage;
    private TextButton musicLevel, increaseBGM,decreaseBGM,SFXLevel, increaseSFX,decreaseSFX,back;
    private Table uiElements;

    public PauseSettingScreen(ttld ttldGame) {
        super(ttldGame);
    }

    @Override
    public void show() {
        super.show();
        stage.clear();
        uiElements = new Table(); // As far as I am concerned this is something similar to JPanel
        uiElements.setFillParent(true); // Explanation is pretty clear just hover over it in IntelliJ
        loadUI(300,15);
        stage.addActor(uiElements); // stage is the class that handles UI elements
        backgroundImage = new Texture("res/backgrounds/menu_background4.png");
    }

    public void updateBGM() {
        ttldGame.menuScreen.bgm.setVolume(ttldGame.menuScreen.bgmVolume);
        ttldGame.gameScreen.gameplayMusic.setVolume(ttldGame.menuScreen.bgmVolume);
    }

    private void loadUI(int length, int gapping) {
        //Initializing text buttons

        musicLevel = addTextButton("Music Volume:");
        increaseBGM = addTextButton("+");
        decreaseBGM = addTextButton("-");

        SFXLevel = addTextButton("SFX Volume:");
        increaseSFX = addTextButton("+");
        decreaseSFX = addTextButton("-");
        back = addTextButton("BACK");

        //Adding listeners

        increaseBGM.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                ttldGame.menuScreen.effect.play(ttldGame.menuScreen.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.menuScreen.bgmVolume += 0.01f;
                updateBGM();
            }
        });
        decreaseBGM.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                ttldGame.menuScreen.effect.play(ttldGame.menuScreen.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Music
                if(ttldGame.menuScreen.bgmVolume>0.00f) {
                    ttldGame.menuScreen.bgmVolume -= 0.01f;
                }
                updateBGM();
            }
        });
        increaseSFX.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                ttldGame.menuScreen.effect.play(ttldGame.menuScreen.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.menuScreen.effectVolume += 0.01f;
            }

        });
        decreaseSFX.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                ttldGame.menuScreen.effect.play(ttldGame.menuScreen.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(ttldGame.menuScreen.effectVolume >= 0.01f)
                {
                    ttldGame.menuScreen.effectVolume -= 0.01f;
                }
            }
        });
        back.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                ttldGame.menuScreen.effect.play(ttldGame.menuScreen.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(ttldGame.pauseScreen);
            }
        });

        SFXLevel.setTouchable(Touchable.disabled);
        SFXLevel.setColor(Color.BLUE);
        musicLevel.setTouchable(Touchable.disabled);
        musicLevel.setColor(Color.BLUE);
        //Adding them to the table
        uiElements.add(musicLevel).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(increaseBGM).width(length).padBottom(gapping/2);
        uiElements.row();
        uiElements.add(decreaseBGM).width(length).padBottom(40);
        uiElements.row();
        uiElements.add(SFXLevel).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(increaseSFX).width(length).padBottom(gapping/2);
        uiElements.row();
        uiElements.add(decreaseSFX).width(length).padBottom(2*gapping);
        uiElements.row();
        uiElements.add(back).width(length);

    }

    @Override
    public void render(float delta) {

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
    }
}
