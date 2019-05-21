package model;

import java.util.ArrayList;
import java.util.List;

public class Scores {
	private int scores;
	private String names;
	private String level;
	
	public Scores(String names, int scores, String level) {
		this.scores = scores;
		this.level = level;
		this.names = names;
	}
	
	public Scores() {
		
	}
	
	
	public int getScores() {
		return scores;
	}
	
	public String getNames() {
		return names;
	}
	
	public String getLevels() {
		return level;
	}
}
