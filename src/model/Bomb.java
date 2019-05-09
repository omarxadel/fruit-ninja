package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bomb implements GameObject{

	private enum bombs{
		Fatal,
		Live;
		}
	
	private bombs bombType;
	
	private int locX, locY, maxH, initialVel, fallingVel;
	private boolean isSliced = false;
	private BufferedImage image;
	
	public Bomb(int bombType) {
		setObject(bombType);
		locX = (int) (Math.random()* 650);
		locY = 450;
	}
	
	public void setObject(int type) {
		String filename = null;
		
		switch(type) {
		case 1:
			bombType = bombs.Fatal;
			filename = "fatal.png";
			break;
		case 2:
			bombType = bombs.Live;
			filename = "livebomb.png";
			break;
		default:
			bombType = null;
			break;
		}
		
		
		if(bombType != null) {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			try {
				image = ImageIO.read(classLoader.getResource(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public bombs getObject() {
		return bombType;
	}

	@Override
	public int getXlocation() {
		return locX;
	}

	@Override
	public int getYlocation() {
		return locY;
	}

	@Override
	public int getMaxHeight() {
		return maxH;
	}

	@Override
	public int getInitialVelocity() {
		return initialVel;
	}

	@Override
	public int getFallingVelocity() {
		return fallingVel;
	}

	@Override
	public boolean isSliced() {
		return isSliced;
	}

	@Override
	public boolean hasMovedOffScreen() {
		return (locY>500);
	}

	@Override
	public void slice() {
		isSliced = true;
	}

	@Override
	public void move(double time) {
		// TODO Implement the movement logic by changing the locations
		// 		according to the time given as a parameter.
		
	}

	@Override
	public BufferedImage getBufferedImages() {
		return image;
	}

	@Override
	public void setYlocation(int Y) {
		this.locY = Y;
	}

	@Override
	public void setXlocation(int X) {
		this.locX = X;
	}

	@Override
	public void render(GraphicsContext gc) {
	    Image image1 = SwingFXUtils.toFXImage(image, null);
		gc.drawImage(image1, getXlocation(), getYlocation());
	}

	@Override
	public Rectangle2D getBoundaries() {
		return new Rectangle2D(locX, locY, this.getBufferedImages().getWidth(), this.getBufferedImages().getHeight());
	}

}
