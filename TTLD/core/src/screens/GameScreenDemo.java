package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ttld.game.ttld;

public class GameScreenDemo extends Screens {

    public GameScreenDemo(ttld ttldGame) {
        super(ttldGame);
    }

    private Texture img;

    @Override
    public void show() { // Called when the program is opened for the first time
        super.show();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); //Clears the screen
        ttld.batch.setProjectionMatrix(gameCam.combined); // Tell the game batch where the camera is and render only what camera can see
        stage.act(Gdx.graphics.getDeltaTime());
        ttld.batch.begin();
        ttld.batch.draw(img,0,0);
        ttld.batch.end();
        stage.draw(); //Draw the specified UI
    }

    @Override
    public void resize(int width, int height) {

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

        // Called when you get rid of the screen
        //ttld.batch.dispose();
        //img.dispose();

    }
}
