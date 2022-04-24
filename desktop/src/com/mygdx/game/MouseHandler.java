package com.mygdx.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{
	float clickedX;
	float clickedY;
	boolean clicked = false;
	public float getTargetX() {
		return clickedX;
	}
	public float getTargetY() {
		return clickedY;
	}
	public boolean isClicked() {
		return clicked;
	}
	public void resetClicked() {
		clicked = false;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		clickedX = e.getX();
		clickedY = e.getY();
		clicked = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
