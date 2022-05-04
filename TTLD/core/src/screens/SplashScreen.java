package screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ttld.game.ttld;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen extends Screens{

    private final  Image splashImage;
    public SplashScreen(ttld ttldGame) {
        super(ttldGame);
        splashImage = new Image(new Texture("res/intro.jpg"));
        stage.addActor(splashImage);
    }

    @Override
    public void show() {
        //System.out.println("SHOW");

        //Animation thanks to https://www.youtube.com/watch?v=D0b2mcq4PJA&list=PLD_bW3UTVsEkPsT2JfVcZmAjmWByIpRvT&index=5
        //splashImage.setPosition(stage.getWidth()/2-splashImage.getImageX()/2, stage.getHeight()/2-splashImage.getImageY()/2);
        splashImage.setFillParent(true);
        splashImage.addAction(sequence(alpha(0),fadeIn(3f),delay(1.5f),fadeOut(3f)));
        //splashImage.addAction(fadeOut(3f));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); //Clears the screen
        stage.act();
        stage.draw();
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

    }
}
