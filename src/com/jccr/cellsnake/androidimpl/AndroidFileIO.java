package com.jccr.cellsnake.androidimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.res.AssetManager;
import android.os.Environment;

import com.jccr.cellsnake.FileIO;

public class AndroidFileIO implements FileIO {
	AssetManager assets;
	String rutaAlmacenamiento;
	
	public AndroidFileIO(AssetManager assets){
		this.assets = assets;
		this.rutaAlmacenamiento = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
	}
	
	@Override
	public InputStream leerAsset(String nombreArchivo) throws IOException {
		// TODO Auto-generated method stub
		return assets.open(nombreArchivo);
	}

	@Override
	public InputStream leerArcchivo(String nombreArchivo) throws IOException {
		// TODO Auto-generated method stub
		return new FileInputStream(rutaAlmacenamiento + nombreArchivo);
	}

	@Override
	public OutputStream escribirArchivo(String nombreArchivo)
			throws IOException {
		// TODO Auto-generated method stub
		return new FileOutputStream(rutaAlmacenamiento + nombreArchivo);
	}
	
}
