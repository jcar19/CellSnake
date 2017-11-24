package com.jccr.cellsnake.juego;

import java.util.List;

import android.graphics.Color;

import com.jccr.cellsnake.Graficos;
import com.jccr.cellsnake.Input.TouchEvent;
import com.jccr.cellsnake.Juego;
import com.jccr.cellsnake.Pantalla;
import com.jccr.cellsnake.Pixmap;

public class PantallaJuego extends Pantalla {
	enum EstadoJuego{
		Preparado,
		Ejecutandose,
		Pausado,
		FinJuego
	}
	
	EstadoJuego estado = EstadoJuego.Preparado;
	Mundo mundo;
	int antiguaPuntuacion = 0;
	String puntuacion = "0";
	
	
	public PantallaJuego(Juego juego) {
		super(juego);
		mundo = new Mundo();
	}
	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		if(estado == EstadoJuego.Preparado)
			updateReady(touchEvents);
		if(estado == EstadoJuego.Ejecutandose)
			updateRunning(touchEvents,deltaTime);
		if(estado == EstadoJuego.Pausado)
			updatePaused(touchEvents);
		if(estado == EstadoJuego.FinJuego)
			updateGameOver(touchEvents);
	}
	
	private void updateReady(List<TouchEvent> touchEvents) {
		if(touchEvents.size()>0)
			estado = EstadoJuego.Ejecutandose;
	}
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH__UP){
				if(event.x < 64 && event.y < 64){
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					estado = EstadoJuego.Pausado;
					return;
				}
			}
			if(event.type == TouchEvent.TOUCH_DOWN){
				if(event.x < 64 && event.y > 416){
					mundo.jolliRoger.girarIzquierda();
				}
				if(event.x > 256 && event.y > 416){
					mundo.jolliRoger.girarDerecha();
				}
			}
		}
		
		mundo.update(deltaTime);
		if(mundo.finaJuego){
			if(Configuraciones.soundEnable)
				Assets.derrota.play(1);
			estado = EstadoJuego.FinJuego;
		}
		if(antiguaPuntuacion != mundo.puntuacion){
			antiguaPuntuacion = mundo.puntuacion;
			puntuacion = "" + antiguaPuntuacion;
			if(Configuraciones.soundEnable)
				Assets.ataque.play(1); //.suck.play(1);
		}
		
	}
	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH__UP){
				if(event.x > 80 && event.x <= 240){
					if(event.y > 100 && event.y <= 148){
						if(Configuraciones.soundEnable)
							Assets.pulsar.play(1);
						estado = EstadoJuego.Ejecutandose;
						return;
					}
					if(event.y > 148 && event.y < 196){
						if(Configuraciones.soundEnable)
							Assets.pulsar.play(1);
						juego.setScreen(new MainMenuScreen(juego));
						return;
					}
				}
			}
		}
	}
	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH__UP){
				if(event.x >= 128 && event.x <= 192 &&
						event.y >= 200 && event.y <= 264){
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					juego.setScreen(new MainMenuScreen(juego));
					return;
				}
			}
		}
	}
	
	@Override
	public void present(float deltaTime) {
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.fondo, 0, 0);
		draWorld(mundo); // !!
		if(estado == EstadoJuego.Preparado)
			drawReadyUI();
		if(estado == EstadoJuego.Ejecutandose)
			draRunningUI();
		if(estado == EstadoJuego.Pausado)
			drawPausedUI();
		if(estado == EstadoJuego.FinJuego)
			drawGameOverUI();
		drawText(g,puntuacion,g.getWidth()/2-puntuacion.length()*20/2,
				g.getHeight()-42);
	}
	
	private void draWorld(Mundo mundo) {
		Graficos g = juego.getGraficos();
		JollyRoger jollyRoger = mundo.jolliRoger;
		Tripulacion head = jollyRoger.partes.get(0);
		Botin botin = mundo.botin;
		
		Pixmap stainPixmap = null;
		if(botin.tipo == Botin.TIPO_1)
			stainPixmap = Assets.botin1;
		if(botin.tipo == Botin.TIPO_2)
			stainPixmap = Assets.botin2;
		if(botin.tipo == Botin.TIPO_3)
			stainPixmap = Assets.botin3;
		int x = botin.x * 32;
		int y = botin.y * 32;
		g.drawPixmap(stainPixmap, x, y);
		
		int len = jollyRoger.partes.size();
        for(int i = 1; i < len; i++) {
            Tripulacion part = jollyRoger.partes.get(i);
            x = part.x * 32;
            y = part.y * 32;
            g.drawPixmap(Assets.tripulacion, x, y);
        }
        
		Pixmap headPixmap = null;
		if(jollyRoger.direccion == JollyRoger.ARRIBA)
			headPixmap = Assets.barcoarriba;
		if(jollyRoger.direccion == JollyRoger.IZQUIERDA)
			headPixmap = Assets.barcoizquierda;
		if(jollyRoger.direccion == JollyRoger.ABAJO )
			headPixmap = Assets.barcoabajo;
		if(jollyRoger.direccion == JollyRoger.DERECHA )
			headPixmap = Assets.barcoderecha;
		x = head.x * 32 + 16;
		y = head.y * 32 + 16;
		g.drawPixmap(headPixmap, x-headPixmap.getWidht()/2, 
				y-headPixmap.getHeight()/2);		
	}
	private void drawReadyUI() {
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.preparado, 47, 100);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
		
	}
	private void draRunningUI() {
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.botones, 0, 0, 64, 128, 64, 64);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
		g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);
		g.drawPixmap(Assets.botones, 256, 416, 0, 64, 64, 64);
	}
	
	private void drawPausedUI() {
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.menupausa, 80, 100);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
	}
	private void drawGameOverUI() {
		Graficos g = juego.getGraficos();
		
		g.drawPixmap(Assets.finjuego, 62, 100);
		g.drawPixmap(Assets.botones, 128, 200, 0, 128, 64, 64);
		g.drawLine(0, 416, 480, 416, Color.BLACK);
	}
	private void drawText(Graficos g, String line, int x, int y) {
		int len = line.length();
		for(int i = 0; i < len; i++){
			char character = line.charAt(i);
			if(character == ' '){
				x += 20;
				continue;
			}
			int srcX = 0;
			int srcWidth = 0;
			if(character == '.'){
				srcX = 200;
				srcWidth = 10;
			} else {
				srcX = (character - '0')*20;
				srcWidth = 20;
			}
			g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWidth, 32);
			x += srcWidth;
		}
	}
	
	@Override
	public void pause() {
		if(estado == EstadoJuego.Ejecutandose)
			estado = EstadoJuego.Pausado;
		if(mundo.finaJuego){
			Configuraciones.addScore(mundo.puntuacion);
			Configuraciones.save(juego.getFileIO());
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
