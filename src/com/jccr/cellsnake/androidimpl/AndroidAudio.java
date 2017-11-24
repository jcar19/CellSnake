package com.jccr.cellsnake.androidimpl;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.jccr.cellsnake.Audio;
import com.jccr.cellsnake.Musica;
import com.jccr.cellsnake.Sonido;

public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;
	
	public AndroidAudio(Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);		
	}
	@Override
	public Musica nuevaMusica(String nombreArchivo) {
		// TODO Auto-generated method stub
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(nombreArchivo);
			return new AndroidMusica(assetDescriptor);
		} catch(IOException e){
			throw new RuntimeException("No se ha podido cargar archivo '"+nombreArchivo+"'");
			
		}
	}

	@Override
	public Sonido nuevoSonido(String fileName) {
		// TODO Auto-generated method stub
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(fileName);
			int sondId = soundPool.load(assetDescriptor, 0);
			return new AndroidSonido(soundPool,sondId);			
		} catch(IOException e){
			throw new RuntimeException("No se ha podido cargar archivo '"+fileName+"'");
		}
	}

}
