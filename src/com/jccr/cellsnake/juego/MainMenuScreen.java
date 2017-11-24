package com.jccr.cellsnake.juego;

import java.util.List;

import com.jccr.cellsnake.Graficos;
import com.jccr.cellsnake.Input.TouchEvent;
import com.jccr.cellsnake.Juego;
import com.jccr.cellsnake.Pantalla;

public class MainMenuScreen extends Pantalla {
	
	public MainMenuScreen(Juego juego) {
		super(juego);
		
	}

	@Override
	public void update(float deltaTime) {
		Graficos g = juego.getGraficos();
		List<TouchEvent> touchEvent = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		int len = touchEvent.size();
		for(int i=0; i<len; i++){
			TouchEvent event = touchEvent.get(i);
			if(event.type == TouchEvent.TOUCH__UP){
				if(inBounds(event,0,g.getHeight() - 64,64,64)){
					Configuraciones.soundEnable = !Configuraciones.soundEnable;
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
				}
				if(inBounds(event, 64, 220, 192, 44)){
					juego.setScreen(new PantallaJuego(juego));
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					return;
				}
				if(inBounds(event, 64, 220+44, 192, 36)){
					juego.setScreen(new PantallaMaximaPuntuaciones(juego));
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					return;
				}
				if(inBounds(event, 64, 264+36, 192, 32)){
					juego.setScreen(new PantallaAcercaDe(juego));
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					return;
				}
				if(inBounds(event, 64, 300 + 32, 192, 32)){
					juego.setScreen(new PantallaAyuda(juego));
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					return;
				}
			}
		}
			
		
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
		if(event.x > x && event.x < x + width - 1 &&
				event.y > y && event.y < y + height - 1)
			return true;
		else return false;
	}

	@Override
	public void present(float deltaTime) {
		Graficos g = juego.getGraficos();
		
		g.drawPixmap(Assets.fondo, 0, 0);
		g.drawPixmap(Assets.logo, 32, 20);
		g.drawPixmap(Assets.menuprincipal, 64, 220);
		if(Configuraciones.soundEnable)
			g.drawPixmap(Assets.botones, 0, 416, 0, 0, 64, 64);
		else
			g.drawPixmap(Assets.botones, 0, 416, 64, 0, 64, 64);
	}

	@Override
	public void pause() {
		Configuraciones.save(juego.getFileIO());
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
