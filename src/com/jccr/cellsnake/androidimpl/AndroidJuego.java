package com.jccr.cellsnake.androidimpl;

import com.jccr.cellsnake.Audio;
import com.jccr.cellsnake.FileIO;
import com.jccr.cellsnake.Graficos;
import com.jccr.cellsnake.Input;
import com.jccr.cellsnake.Juego;
import com.jccr.cellsnake.Pantalla;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class AndroidJuego extends Activity implements Juego {
	AndroidFastRenderView renderView;
	Graficos graficos;
	Audio audio;
	Input input;
	FileIO fileIO;
	Pantalla pantalla;
	WakeLock wakeLock;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
		int frameBufferWidth = isLandscape ? 480:320;
		int frameBufferHeight = isLandscape ? 320:480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
				frameBufferHeight,Config.RGB_565);
		
		float scaleX = (float) frameBufferWidth
				/ getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float) frameBufferHeight
				/ getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graficos = new AndroidGraficos(getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		pantalla = getStartScren();
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
		
	}
	
	@Override
	protected void onResume() {	
		super.onResume();
		wakeLock.acquire();
		pantalla.resume();
		renderView.resume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		wakeLock.release();
		renderView.pause();
		pantalla.pause();
		if(isFinishing())
			pantalla.dispose();
	}
	@Override
	public Input getInput() {
		return input;
	}
	@Override
	public FileIO getFileIO() {
		return fileIO;
	}
	@Override
	public Graficos getGraficos() {
		return graficos;
	}
	@Override
	public Audio getAudio() {
		return audio;
	}
	@Override
	public void setScreen(Pantalla pantalla) {
		if(pantalla == null)
			throw new IllegalArgumentException("Pantalla vacia");
		this.pantalla.pause();
		this.pantalla.dispose();
		pantalla.resume();
		pantalla.update(0);
		this.pantalla = pantalla;
	}
	@Override
	public Pantalla getCurrentScreen() {
		return pantalla;
	}
}
