package com.jccr.cellsnake.juego;

import java.util.List;

import com.jccr.cellsnake.Graficos;
import com.jccr.cellsnake.Input.TouchEvent;
import com.jccr.cellsnake.Juego;
import com.jccr.cellsnake.Pantalla;

public class PantallaMaximaPuntuaciones extends Pantalla {
	String lines[] = new String[5];
	
	public PantallaMaximaPuntuaciones(Juego juego) {
		super(juego);
		for(int i = 0; i < 5; i++){
			lines[i] = "" + (i + 1) + ". " + Configuraciones.highscores[i];
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		int len = touchEvents.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvents.get(i);
			if(event.type == TouchEvent.TOUCH__UP){
				if(event.x < 64 && event.y > 416){
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					juego.setScreen(new MainMenuScreen(juego));
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graficos g = juego.getGraficos();
		
		g.drawPixmap(Assets.fondo, 0, 0);
		g.drawPixmap(Assets.menuprincipal, 64, 20, 0, 42, 196, 40);
		
		int y = 100;
		for(int i = 0; i < 5; i++){
			dibujarTexto(g, lines[i], 20, y);
			y += 50;
		}
		
		g.drawPixmap(Assets.botones, 0, 416, 64, 64, 64, 64);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
	
	public void dibujarTexto(Graficos g, String line, int x, int y){
		int len = line.length();
		for (int i = 0; i < len; i++){
			char caracter = line.charAt(i);
			
			if(caracter == ' '){
				x += 20;
				continue;
			}
			
			int srcX = 0;
			int srcWith = 0;
			if(caracter == '.'){
				srcX = 200;
				srcWith = 10;				
			} else {
				srcX = (caracter - '0') * 20;
				srcWith = 20;
			}
			g.drawPixmap(Assets.numeros, x, y, srcX, 0, srcWith, 32);
			x += srcWith;
		}
	}
}
