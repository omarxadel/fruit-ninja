package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fruit implements GameObject{

	private enum fruits{
		Strawberry,
		Banana,
		Peach,
		Apple,
		Watermelon;
	}
	
	private fruits fruitType;
	
	private int locX, locY, maxH, initialVel, fallingVel;
	private boolean isSliced = false;
	private BufferedImage image;
	
	public Fruit(int fruitType) {
		setObject(fruitType);
	}
	
	public void setObject(int type) {
		String filename = null;
		
		switch(type) {
		case 1:
			fruitType = fruits.Strawberry;
			filename = "strawberry.png";
			break;
		case 2:
			fruitType = fruits.Banana;
			filename = "banana.png";
			break;
		case 3:
			fruitType = fruits.Apple;
			filename = "apple.png";
			break;
		case 4:
			fruitType = fruits.Peach;
			filename = "peach.png";
			break;
		case 5:
			fruitType = fruits.Watermelon;
			filename = "watermelon.png";
			break;
		default:
			fruitType = null;
			break;
		}
		
		
		if(fruitType != null) {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			try {
				image = ImageIO.read(classLoader.getResource(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public fruits getObject() {
		return fruitType;
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
		return (locX>750);
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