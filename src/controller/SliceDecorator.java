package controller;

import java.awt.image.BufferedImage;

import model.GameObject;

public class SliceDecorator extends ObjectDecorator {
	
	BufferedImage [][] images= new BufferedImage[2][2];

	public SliceDecorator(GameObject fruitDecorator) {
		super(fruitDecorator);
	
	}
	
	public GameObject Update () {
		return null;
		
	}

}
