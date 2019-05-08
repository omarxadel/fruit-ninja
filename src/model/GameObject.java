package model;

import java.awt.image.BufferedImage;

public interface GameObject {
	public Enum getObject();
	public int getXlocation();
	public int getYlocation();
	public int getMaxHeight();
	public int getInitialVelocity();
	public int getFallingVelocity();
	public boolean isSliced();
	public boolean hasMovedOffScreen();
	public void slice();
	public void move(double time);
	public BufferedImage getBufferedImages();
}
