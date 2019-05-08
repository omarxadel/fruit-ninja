package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		locX = (int) (Math.random()* 750);
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

}
