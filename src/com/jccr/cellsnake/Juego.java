package com.jccr.cellsnake;

public interface Juego {
	public Input getInput();
	public FileIO getFileIO();
	public Graficos getGraficos();
	public Audio getAudio();
	public void setScreen(Pantalla pantalla);
	public Pantalla getCurrentScreen();
	public Pantalla getStartScren();
	
}
