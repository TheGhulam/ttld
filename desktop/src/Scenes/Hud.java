package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;

public class Hud {
	public Stage stage;
	private Viewport viewport;
	private Integer worldtimer;
	private float timeCount;
	private int coinCount;
	Label countdownLabel;
	Label coinlabel;
	Label timeLabel;
	Label levelLabel;
	
	public Hud(SpriteBatch sbatch) {
		worldtimer = 300;
		timeCount = 0;
		coinCount = 0;
		viewport = new FitViewport(Application.V_WIDTH,Application.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport,sbatch);
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		countdownLabel = new Label(String.format("%03d", worldtimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		coinlabel = new Label(String.format("%06d",coinCount ), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		
		table.add(levelLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		table.row();
		table.add(coinlabel).expandX();
		table.add(countdownLabel).expandX();
		
		stage.addActor(table);
		
		
		
		
	}
}
