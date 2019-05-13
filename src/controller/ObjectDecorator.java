package controller;

import java.awt.image.BufferedImage;

import model.GameObject;

public abstract class ObjectDecorator implements GameObject {
	
	private GameObject FruitDecorator;

	public GameObject getFruitDecorator() {
		return FruitDecorator;
	}

	public void setFruitDecorator(GameObject fruitDecorator) {
		FruitDecorator = fruitDecorator;
	}
	
	public abstract BufferedImage[] getSlices();
	
	
	
	

}
