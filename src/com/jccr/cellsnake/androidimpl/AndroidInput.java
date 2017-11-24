package com.jccr.cellsnake.androidimpl;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.jccr.cellsnake.Input;

public class AndroidInput implements Input {
	AcelerometroHandler acelHandler;
	TecladoHandler teclaHandler;
	TocarHandler tocaHandler;
	
	public AndroidInput(Context context, View view, float scaleX, float scaleY){
		acelHandler = new AcelerometroHandler(context);
		teclaHandler = new TecladoHandler(view);
		if(Integer.parseInt(VERSION.SDK) < 5)
			tocaHandler = new SingleTouchHandler(view, scaleX, scaleY);
		else
			tocaHandler = new MultiTouchHandler(view, scaleX, scaleY);
	}

	@Override
	public boolean isKeyPressed(int keyCode) {
		return teclaHandler.isKeyPressed(keyCode);
	}
	@Override
	public boolean isTouchDown(int pointer) {
		return tocaHandler.isTouchDown(pointer);
	}
//	@Override
//	public boolean isTouchEvent(int pointer) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public int getTouchX(int pointer) {
		return tocaHandler.getTouchX(pointer);
	}

	@Override
	public int getTouchY(int pointer) {
		return tocaHandler.getTouchY(pointer);
	}

	@Override
	public float getAccelX() {
		return acelHandler.getAccelX();
	}

	@Override
	public float getAccelY() {
		return acelHandler.getAccelY();
	}

	@Override
	public float getAccelZ() {
		return acelHandler.getAccelZ();
	}
	@Override
	public List<TouchEvent> getTouchEvents() {
		return tocaHandler.getTouchEvents();
	}
	@Override
	public List<KeyEvent> getKeyEvents() {
		return teclaHandler.getKeyEvents();
	}
	
}
