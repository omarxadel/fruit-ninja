package controller;

public class Caretaker {
	Memento memento = null;
	
	public void addMemento(Memento m) {
		memento = m;
	}
	
	public Memento getMemento() {
		return memento;
	}
	
}
