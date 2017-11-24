package com.jccr.cellsnake.juego;

import com.jccr.cellsnake.Graficos;
import com.jccr.cellsnake.Graficos.PixmapFormat;
import com.jccr.cellsnake.Juego;
import com.jccr.cellsnake.Pantalla;

public class LoadingScreen extends Pantalla {
	
	public LoadingScreen(Juego juego) {
		super(juego);
	}

	@Override
	public void update(float deltaTime) {
		Graficos g = juego.getGraficos();
		Assets.fondo = g.newPixmap("fondocell.png", PixmapFormat.RGB565);
		Assets.acercade = g.newPixmap("acercade.png", PixmapFormat.RGB565);
        Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
        Assets.menuprincipal = g.newPixmap("menuprincipal.png", PixmapFormat.ARGB4444);
        Assets.botones = g.newPixmap("botones.png", PixmapFormat.ARGB4444);
        Assets.ayuda1 = g.newPixmap("ayuda1.png", PixmapFormat.ARGB4444);
        Assets.ayuda2 = g.newPixmap("ayuda2.png", PixmapFormat.ARGB4444);
        Assets.ayuda3 = g.newPixmap("ayuda3.png", PixmapFormat.ARGB4444);
        Assets.numeros = g.newPixmap("numeros.png", PixmapFormat.ARGB4444);
        Assets.preparado = g.newPixmap("preparado.png", PixmapFormat.ARGB4444);
        Assets.menupausa = g.newPixmap("menupausa.png", PixmapFormat.ARGB4444);
        Assets.finjuego = g.newPixmap("finjuego.png", PixmapFormat.ARGB4444);
        Assets.barcoarriba = g.newPixmap("cell.png", PixmapFormat.ARGB4444);
        Assets.barcoizquierda = g.newPixmap("cell.png", PixmapFormat.ARGB4444);
        Assets.barcoabajo = g.newPixmap("cell.png", PixmapFormat.ARGB4444);
        Assets.barcoderecha = g.newPixmap("cell.png", PixmapFormat.ARGB4444);
        Assets.tripulacion = g.newPixmap("celljr.png", PixmapFormat.ARGB4444);
        Assets.botin1 = g.newPixmap("botin1.png", PixmapFormat.ARGB4444);
        Assets.botin2 = g.newPixmap("botin2.png", PixmapFormat.ARGB4444);
        Assets.botin3 = g.newPixmap("botin3.png", PixmapFormat.ARGB4444);        
        Assets.pulsar = juego.getAudio().nuevoSonido("pulsar.ogg");
        Assets.ataque = juego.getAudio().nuevoSonido("suck.ogg");
        Assets.derrota = juego.getAudio().nuevoSonido("derrota.ogg");
        
        Configuraciones.load(juego.getFileIO());
        juego.setScreen(new MainMenuScreen(juego));
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub

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
