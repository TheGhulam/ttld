package Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

//Credit to : Coding Dutchman. (2020, August 20). Gamedev with libGDX | E06 Game Screen [Video]. YouTube. https://www.youtube.com/watch?v=3_PArk1yudQ

public abstract class Screens implements Screen {
	
	private Stage stage; // Displays user interface (responsible for holding buttons, labels, texts, etc.)
	private Viewport viewport; // responsible for adjusting the elements within the game in different resolutions
	private Skin skin;
	
	@Override
	public void show() // it is called once (like create method)
	{
		viewport = new ExtendViewport(1280,720); // parameters(x,y) sets the screen in such a way that there are at least x tiles in length
										     	 // or, at least 9 tiles in height.
		stage = new Stage(viewport);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(.1f, .1f, .15f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) { // called when the screen is opened the first time and when you resize your window
		viewport.update(width, height); // when the resolutions change, viewport adjusts them in a way that is preferable
	}
	
	private TextButton addButton(String name) {
		TextButton button = new TextButton(name, )
	}

}
