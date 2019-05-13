package controller;

import model.GameObject;

public class Context implements GameActions{
	GameActions gameActions;

	public Context(GameActions gameActions) {
		this.gameActions=gameActions;
	}
	
	public GameObject createGameObject () {
		return gameActions.createGameObject();
	}
	public void updateObjectsLocations () {
		
	}
	public void sliceObjects () {
		
	}
	public void saveGame () {
		
	}
	public void loadGame () {
		
	}
	public void ResetGame () {
		
	}

	@Override
	public int getPhaseObjects() {
		return gameActions.getPhaseObjects();
	}
	}
