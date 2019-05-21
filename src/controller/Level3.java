package controller;

import model.GameObject;

public class Level3  implements GameActions{
	private ObjectFactory factory = new ObjectFactory();
	private final int phase = 4;
	private final int speed = 1750;
	
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
        	case 6:
        		return factory.getObject("FATALBOMB");
        	default:
        		return rare(shows);
        	}
	}

	private GameObject rare(int shows) {
    	shows = (int) (Math.random()*7);
    	
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
    	case 6:
    		return factory.getObject("FATALBOMB");
    	default:
    		return null;
    	}
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

	@Override
	public int getPhaseObjects() {
		return phase;
	}

	public int getSpeed() {
		return speed;
	}

}
