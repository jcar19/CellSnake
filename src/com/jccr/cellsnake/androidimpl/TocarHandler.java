package com.jccr.cellsnake.androidimpl;

import java.util.List;

import com.jccr.cellsnake.Input.TouchEvent;

import android.view.View.OnTouchListener;

public interface TocarHandler extends OnTouchListener {
	
	public boolean isTouchDown(int pointer);
	
	public int getTouchX(int pointer);
	
	public int getTouchY(int pointer);
	
	public List<TouchEvent> getTouchEvents();
	
}
