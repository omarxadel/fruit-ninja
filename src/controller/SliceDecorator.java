package controller;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.io.IOException;

import model.Fruit;
import model.GameObject;

public class SliceDecorator extends ObjectDecorator {
	
	
	private BufferedImage [] images= new BufferedImage[10];
	
	public SliceDecorator(GameObject FruitDecorator) {
		super(FruitDecorator);
		
	}
	 public GameObject getSlice(String object) throws IOException {
		
		 if (object==null) 
			 return null;
		 if (object.equalsIgnoreCase("STRAWBERRY")) {
			 images [0]=ImageIO.read(new File ("strawberry-1.png"));
			 images [1]=ImageIO.read(new File ("strawberry-2.png"));
			 
		 }
		 else if (object.equalsIgnoreCase("BANANA")) {
			 images [2]=ImageIO.read(new File ("banana-1.png"));
			 images [3]=ImageIO.read(new File ("banana-2.png"));
			 
		 }
		 else if (object.equalsIgnoreCase("APPLE")) {
			 images [4]=ImageIO.read(new File ("apple-1.png"));
			 images [5]=ImageIO.read(new File ("apple-2.png"));
			 
		 }
		 else if (object.equalsIgnoreCase("PEACH")) {
			 images [6]=ImageIO.read(new File ("peach-1.png"));
			 images [7]=ImageIO.read(new File ("peach-2.png"));
			 
		 }
		 else if (object.equalsIgnoreCase("WATERMELON")) {
			 images [8]=ImageIO.read(new File ("watermelon-1.png"));
			 images [9]=ImageIO.read(new File ("watermelon-2.png"));
			 
		 }
		 
		 
		 return null;
	 }
	@Override
	public Enum getObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getXlocation() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setYlocation(int Y) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setXlocation(int X) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getYlocation() {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void slice() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BufferedImage getBufferedImages() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Rectangle2D getBoundaries() {
		// TODO Auto-generated method stub
		return null;
	}
}