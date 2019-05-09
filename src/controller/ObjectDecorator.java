package controller;

import model.GameObject;

public abstract class ObjectDecorator implements GameObject {
	
	private GameObject FruitDecorator;

	public ObjectDecorator(GameObject FruitDecorator) {
		super();
		this.FruitDecorator=FruitDecorator;
	}

	public GameObject getFruitDecorator() {
		return FruitDecorator;
	}

	public void setFruitDecorator(GameObject fruitDecorator) {
		FruitDecorator = fruitDecorator;
	}
	
	
	
	

}
