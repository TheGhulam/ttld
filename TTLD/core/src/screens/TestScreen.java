package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ttld.game.ttld;

public class TestScreen extends Screens{

    public TestScreen(ttld ttldGame) {
        super(ttldGame);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); //Clears the screen
        ttld.batch.setProjectionMatrix(gameCam.combined);
        System.out.println("RENDER");
        update();
    }

    private void update() {
        inputUpdate();
    }

    private void inputUpdate() {
        if(Gdx.input.isButtonJustPressed(Input.Keys.SPACE))
            pause();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        System.out.println("PAUSED");
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
