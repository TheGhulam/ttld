// Credits to : HollowBit YouTube Channel
// Link to his playlist: https://www.youtube.com/playlist?list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf

package com.ttld.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import screens.MenuScreen;

public class ttld extends Game {

	public static final int width = 1280;
	public static final int height = 720;

	public static SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render(); //calls the render method of the active screens
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
