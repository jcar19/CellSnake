package com.jccr.cellsnake;

import com.jccr.cellsnake.Graficos.PixmapFormat;

public interface Pixmap {
	public int getWidht();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
