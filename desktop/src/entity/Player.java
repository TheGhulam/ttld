package entity;
import java.awt.Color;
import java.awt.Graphics2D;

import com.mygdx.game.*;

public class Player extends Entity {
	Application app;
	MouseHandler mouseHandler;
	
	public Player(Application app, MouseHandler mH ) {
		this.app = app;
		mouseHandler = mH;
		setDefaultValues();
	}
	public void setDefaultValues() {
		x = 100;
		y= 100;
		speed = 1;
	}
	public void update() {
		if(mouseHandler.isClicked())
			if(x != mouseHandler.getTargetX() ) {
				if(x > mouseHandler.getTargetX()) {
					x -= speed;
				}
				else {x += speed;}
			}
			if(y != mouseHandler.getTargetY()) {
				if(y > mouseHandler.getTargetY()) {
					y -= speed;
				}
				else {y += speed;}
			}
			if(x == mouseHandler.getTargetX() && y == mouseHandler.getTargetY()) {
				mouseHandler.resetClicked();
			}
	}
	public void draw(Graphics2D g2) {
		
	}
}
	