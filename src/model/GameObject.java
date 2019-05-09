package model;

import java.awt.image.BufferedImage;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface GameObject {
	public Enum getObject();
	public int getXlocation();
	public void setYlocation(int Y);
	public void setXlocation(int X);
	public int getYlocation();
	public int getMaxHeight();
	public int getInitialVelocity();
	public int getFallingVelocity();
	public boolean isSliced();
	public boolean hasMovedOffScreen();
	public void slice();
	public void move(double time);
	public BufferedImage getBufferedImages();
	public void render(GraphicsContext gc);
	public Rectangle2D getBoundaries();
}
