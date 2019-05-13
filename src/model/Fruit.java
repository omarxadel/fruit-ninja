package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Fruit implements GameObject{

	private enum fruits{
		Strawberry,
		Banana,
		Peach,
		Apple,
		Watermelon;
	}
	
	private fruits fruitType;
	private boolean falling = false;
	private int locX, locY;
	private final int maxH = 120;
	private final double initialVel = 2.7;
	private final double fallingVel = 1.5;
	private boolean isSliced = false;
	private BufferedImage image;
	
	public Fruit(int fruitType) {
		setObject(fruitType);
		locX = (int) (Math.random()* 500);
		locY = 450;
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
		return (int) initialVel;
	}

	@Override
	public int getFallingVelocity() {
		return (int) fallingVel;
	}

	@Override
	public boolean isSliced() {
		return isSliced;
	}

	@Override
	public boolean hasMovedOffScreen() {
		return (locY > 500);
	}

	@Override
	public void slice() {
		isSliced = true;
	}

	@Override
	public void move(double time) {
		if(locY > maxH && !falling) {
			locY-=(time*initialVel);
			locX += Math.random()*5;
		}
		else {
			falling = true;
			locY+=(time*fallingVel);
			locX += Math.random()*5;
		}
	}

	@Override
	public BufferedImage getBufferedImages() {
		return image;
	}

	@Override
	public void render(GraphicsContext gc) {
	    Image image1 = SwingFXUtils.toFXImage(image, null);
		gc.drawImage(image1, getXlocation(), getYlocation());
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
	public Rectangle2D getBoundaries() {
		return new Rectangle2D(locX, locY, image.getWidth(), image.getHeight());
	}

}
