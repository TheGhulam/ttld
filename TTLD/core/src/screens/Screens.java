package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ttld.game.ttld;

public abstract class Screens implements Screen {

    protected ttld ttldGame;
    protected Viewport port;
    protected OrthographicCamera gameCam;

    protected Skin skin;
    protected Stage stage;
    protected BitmapFont font;


    public Screens(ttld ttldGame) {
        font = new BitmapFont();
        this.ttldGame = ttldGame;
        gameCam = new OrthographicCamera();
        port = new FitViewport(ttld.width,ttld.height,gameCam);
        stage = new Stage(port);
        skin = new Skin(Gdx.files.internal("flat-earth-ui.json"));

    }

    @Override
    public void show() {
        setInputProcessor();


    }

    protected void setInputProcessor() {
        Gdx.input.setInputProcessor(stage);
    }

    protected TextButton addTextButton(String name) {
        TextButton button = new TextButton(name,skin);
        //stage.addActor(button); //(IDK if this is necessary ?)
        return button;
    }

}
