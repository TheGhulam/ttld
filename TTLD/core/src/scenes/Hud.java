package scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ttld.game.ttld;

public class Hud {

    public Stage stage;
    private FitViewport port;

    //private Integer worldTimer;
    private static Integer currency;
    //private float timeCount;
    private static Integer score;

    private Label countDownLabel;
    private static Label scoreLabel;
    private Label scoreTextLabel;
    private static Label currencyLabel;

    //Label timeLabel;
    //Label worldLabel;
    private static Label moneyLabel;

    public Hud(SpriteBatch sb) {

        currency = 1000;
        score = 0;

        port = new FitViewport(ttld.width,ttld.height);
        stage = new Stage(port, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        currencyLabel = new Label(""+ currency , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d  ",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        moneyLabel  = new Label("GOLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreTextLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(moneyLabel).expandX().padTop(10);
        table.add(scoreTextLabel).expandX().padTop(10);
        table.row();
        table.add(currencyLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }

    public Stage getStage(){ return stage; }
    public void dispose(){
        stage.dispose();
    }

    public static void addScore(int value,int gold){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
        currency += gold;
        currencyLabel.setText("" + currency);
    }

    public static void setCurrency(int value){
        currency = value;
        currencyLabel.setText("" + currency);
    }
    public static int getCurrency(){
        return currency;
    }
}
