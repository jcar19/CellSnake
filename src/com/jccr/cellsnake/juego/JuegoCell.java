package com.jccr.cellsnake.juego;

import com.jccr.cellsnake.Pantalla;
import com.jccr.cellsnake.androidimpl.AndroidJuego;

//import android.app.Activity;

public class JuegoCell extends AndroidJuego {
	@Override
	public Pantalla getStartScren() {
		return new LoadingScreen(this);
	}
}
