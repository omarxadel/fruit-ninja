package controller;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.IOException;
import model.GameObject;

public class FruitDecorator extends ObjectDecorator {
	
	private GameObject slicedFruit;
	private BufferedImage [] images= new BufferedImage[2];
	private int X1,Y1, X2,Y2;
	private final int maxH = 120;
	private final double initialVel = 2.7;
	private final double fallingVel = 2;
	
	public FruitDecorator(GameObject slicedFruit) throws IOException {
		String filename = null;
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		this.slicedFruit = slicedFruit;
		if(this.slicedFruit.getObject().name().equalsIgnoreCase("STRAWBERRY")) {
			filename = "strawberry-1.png";
			images [0]=ImageIO.read(classLoader.getResource(filename));
			filename = "strawberry-2.png";
			images [1]=ImageIO.read(classLoader.getResource(filename));
		}
		else if(this.slicedFruit.getObject().name().equalsIgnoreCase("BANANA")) {
			filename = "banana-2.png";
			images [0]=ImageIO.read(classLoader.getResource(filename));
			filename = "banana-1.png";
			images [1]=ImageIO.read(classLoader.getResource(filename));
		}
		else if(this.slicedFruit.getObject().name().equalsIgnoreCase("APPLE")) {
			filename = "apple-1.png";
			images [0]=ImageIO.read((classLoader.getResource(filename)));
			filename = "apple-2.png";
			images [1]=ImageIO.read(classLoader.getResource(filename));
		}
		else if(this.slicedFruit.getObject().name().equalsIgnoreCase("WATERMELON")) {
			filename = "watermelon-1.png";
			images [0]=ImageIO.read((classLoader.getResource(filename)));
			filename = "watermelon-2.png";
			images [1]=ImageIO.read(classLoader.getResource(filename));
		}
		else if(this.slicedFruit.getObject().name().equalsIgnoreCase("PEACH")) {
			filename = "peach-1.png";
			images [0]=ImageIO.read(classLoader.getResource(filename));
			filename = "peach-2.png";
			images [1]=ImageIO.read(classLoader.getResource(filename));
		}
	}
	
	public BufferedImage[] getSlices() {
		return images;
	}

	@Override
	public Enum getObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getXlocation() {
		return X1;
	}
	@Override
	public void setYlocation(int Y) {
		this.Y1 = Y;
		this.Y2 = Y;
	}
	@Override
	public void setXlocation(int X) {
		this.X1 = X;
		this.X2 = X;
		
	}
	@Override
	public int getYlocation() {
		return Y1;
	}
	@Override
	public int getMaxHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getInitialVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getFallingVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isSliced() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean hasMovedOffScreen() {
		return this.getYlocation() > 500;
	}
	@Override
	public void slice() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(double time) {
		Y1+=(time*fallingVel);
		Y2+=(time*fallingVel);
		X1-=fallingVel;
		X2+=fallingVel;
	}
	@Override
	public BufferedImage getBufferedImages() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void render(GraphicsContext gc) {

	    Image image1 = SwingFXUtils.toFXImage(images[0], null);
		gc.drawImage(image1, X1, Y1);
		image1 = SwingFXUtils.toFXImage(images[1], null);
		gc.drawImage(image1, X2, Y2);
		
	}
	@Override
	public Rectangle2D getBoundaries() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getX2() {
		return X2;
	}

	public void setX2(int x2) {
		X2 = x2;
	}

	public int getY1() {
		return Y1;
	}

	public void setY1(int y1) {
		Y1 = y1;
	}

	public int getY2() {
		return Y2;
	}

	public void setY2(int y2) {
		Y2 = y2;
	}

	public int getX1() {
		return X1;
	}

	public void setX1(int x1) {
		X1 = x1;
	}
	
}