package com.jccr.cellsnake.juego;

import java.util.ArrayList;
import java.util.List;

public class JollyRoger {
	public static final int ARRIBA = 0;
	public static final int IZQUIERDA = 1;
	public static final int ABAJO = 2;
	public static final int DERECHA = 3;
	
	public List<Tripulacion> partes = new ArrayList<Tripulacion>();
	public int direccion;
	
	public JollyRoger() {
		direccion = ARRIBA;
		partes.add(new Tripulacion(5, 6));
		partes.add(new Tripulacion(5, 7));
		partes.add(new Tripulacion(5, 8));
	}
	
	public void girarIzquierda(){
		direccion += 1;
		if(direccion > DERECHA)
			direccion = ARRIBA;
	}
	
	public void girarDerecha(){
		direccion -= 1;
		if(direccion < ARRIBA)
			direccion = DERECHA;
	}
	
	public void expulsion(){
		Tripulacion end = partes.get(partes.size()-1);
		partes.add(new Tripulacion(end.x, end.y));
	}
	
	public void avance(){
		Tripulacion cell = partes.get(0);
		
		int len = partes.size()-1;
		for(int i = len; i > 0; i--){
			Tripulacion antes = partes.get(i-1);
			Tripulacion parte = partes.get(i);
			parte.x = antes.x;
			parte.y = antes.y;
		}
		if( direccion == ARRIBA)
			cell.y -= 1;
		if(direccion == IZQUIERDA)
			cell.x -= 1;
		if(direccion == ABAJO)
			cell.y += 1;
		if(direccion == DERECHA)
			cell.x +=1;
		
		if(cell.x < 0)
			cell.x = 9;
		if(cell.x > 9)
			cell.x = 0;
		if(cell.y < 0)
			cell.y = 12;
		if(cell.y > 12)
			cell.y = 0;		
	}
	
	public boolean comprobarChoque(){
		int len = partes.size();
		Tripulacion cell = partes.get(0);
		for(int i = 1; i < len; i++){
			Tripulacion parte = partes.get(i);
			if(parte.x == cell.x && parte.y == cell.y)
				return true;
		}
		return false;
	}
}
