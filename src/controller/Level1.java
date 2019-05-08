package controller;

import model.Fruit;
import model.GameObject;

public class Level1 implements GameActions{
	private ObjectFactory factory = new ObjectFactory();

	@Override
	public GameObject createGameObject() {     	
        	int shows = (int) (Math.random()*7);
        	switch(shows) {
        	case 1 : 
        		return factory.getObject("APPLE");
        	case 2 :
        		return factory.getObject("BANANA");
        	case 3 :
        		return factory.getObject("STRAWBERRY");
        	case 4 :
        		return factory.getObject("WATERMELON");
        	case 5 : 
        		return factory.getObject("PEACH");
        	case 6 :
        		return factory.getObject("FATALBOMB");
        	}
		return null;
	}

	@Override
	public void updateObjectsLocations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sliceObjects() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void loadGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ResetGame() {
		// TODO Auto-generated method stub
		
	}


}
