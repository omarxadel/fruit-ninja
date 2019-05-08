package controller;

import model.Fruit;
import model.GameObject;

public abstract class ObjectDecorator implements GameObject {
	
	private GameObject FruitDecorator;

	public ObjectDecorator(GameObject FruitDecorator) {
		super();
		this.FruitDecorator=FruitDecorator;
	}
	
	

}
