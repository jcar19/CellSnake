package com.jccr.cellsnake.juego;

import java.util.List;

import com.jccr.cellsnake.Graficos;
import com.jccr.cellsnake.Juego;
import com.jccr.cellsnake.Pantalla;
import com.jccr.cellsnake.Input.TouchEvent;

public class PantallaAcercaDe extends Pantalla {

	public PantallaAcercaDe(Juego juego) {
		super(juego);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvent = juego.getInput().getTouchEvents();
		juego.getInput().getKeyEvents();
		
		int len = touchEvent.size();
		for(int i = 0; i < len; i++){
			TouchEvent event = touchEvent.get(i);
			if(event.type == TouchEvent.TOUCH__UP){
				if(event.x > 256 && event.y > 416){
					juego.setScreen(new MainMenuScreen(juego));
					if(Configuraciones.soundEnable)
						Assets.pulsar.play(1);
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graficos g = juego.getGraficos();
		g.drawPixmap(Assets.fondo, 0, 0);
		g.drawPixmap(Assets.acercade, 0, 0);
		g.drawPixmap(Assets.botones, 256, 416, 0, 128, 64, 64);
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

}
