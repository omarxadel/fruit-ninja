package controller;

public interface Subject {
	
	public void addObserver(Observer o);
	public void NotifyObservers();

}
