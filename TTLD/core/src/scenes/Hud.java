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

    private Integer worldTimer;

    private Integer currency;
    private float  timeCount;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel;
    Label currencyLabel;
    Label timeLabel;
    Label worldLabel;
    Label moneyLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        port = new FitViewport(ttld.width,ttld.height);
        stage = new Stage(port, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        currencyLabel = new Label(""+ this.currency , new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        countDownLabel = new Label(String.format("%03d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d  ",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        moneyLabel  = new Label("GOLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldLabel  = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        //table.add(worldLabel).expandX().padTop(10);
        table.add(moneyLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(currencyLabel).expandX().padTop(10);
        table.add(countDownLabel).expandX();

        //table.add(scoreLabel).expandX();


        stage.addActor(table);
    }

    public Stage getStage(){ return stage; }
    public void dispose(){
        stage.dispose();
    }
    public void setCurrency(int newCurrency){
        this.currency = newCurrency;
    }
    public int getCurrency(){
        return this.currency;
    }
}
