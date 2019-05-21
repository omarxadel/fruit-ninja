package controller;

import java.util.ArrayList;
import java.util.List;

import model.Scores;

public class Memento {
	// TEXT FILE MEMENTOs
	Scores[] scores;
	
	public Memento(Scores[] scores) {
		this.scores = scores;
	}
	
	public Scores[] getState(){
		return scores;
	}
	
	
	
}
