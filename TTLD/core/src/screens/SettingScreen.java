package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ttld.game.ttld;

public class SettingScreen extends Screens{

    private MenuScreen menu;
    private Texture backgroundImage;
    private TextButton increaseBGM,decreaseBGM,increaseSFX,decreaseSFX,back;
    private Table uiElements;

    public SettingScreen(ttld ttldGame) {
        super(ttldGame);
    }

    public SettingScreen(ttld ttldGame,MenuScreen menu) {
        super(ttldGame);
        this.menu = menu;
    }

    @Override
    public void show() {
        super.show();
        stage.clear();
        uiElements = new Table(); // As far as I am concerned this is something similar to JPanel
        uiElements.setFillParent(true); // Explanation is pretty clear just hover over it in IntelliJ
        loadUI(300,15);
        stage.addActor(uiElements); // stage is the class that handles UI elements
        backgroundImage = new Texture("res/menu_background4.png");
    }

    private void loadUI(int length, int gapping) {
        //Initializing text buttons

        increaseBGM = addTextButton("+");
        decreaseBGM = addTextButton("-");
        increaseSFX = addTextButton("+");
        decreaseSFX = addTextButton("-");
        back = addTextButton("BACK");

        //Adding listeners

        increaseBGM.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menu.effect.play(menu.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menu.bgmVolume += 0.01f;
                menu.bgm.setVolume(menu.bgmVolume);
            }
        });
        decreaseBGM.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menu.effect.play(menu.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(menu.bgmVolume>0.00f)
                    menu.bgmVolume -= 0.01f;
                menu.bgm.setVolume(menu.bgmVolume);
            }
        });
        increaseSFX.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menu.effect.play(menu.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                menu.effectVolume += 0.01f;
            }
        });
        decreaseSFX.addListener(new ClickListener() {
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menu.effect.play(menu.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(menu.effectVolume>0.00f)
                {
                    menu.effectVolume -= 0.01f;
                }
            }
        });
        back.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                menu.effect.play(menu.effectVolume);
            }
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ttldGame.setScreen(menu);
            }
        });

        //Adding them to the table

        uiElements.add(increaseBGM).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(decreaseBGM).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(increaseSFX).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(decreaseSFX).width(length).padBottom(gapping);
        uiElements.row();
        uiElements.add(back).width(length).padBottom(gapping);

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
        //Dispose ları yaz sonra
        backgroundImage.dispose();
    }
}
