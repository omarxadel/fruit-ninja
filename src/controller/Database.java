package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import model.Scores;

public class Database {
	private static Database instance;
	private Database() {
		//DO NOTHING
	}
	public static Database getInstance() {
		if (instance==null) {
			instance = new Database();
		}
		return instance;
	}

	// TODO THE TEXT FILE
	private FileWriter writer;
	private Scanner reader;
	private Scores [] scores = new Scores[11];
	private int scoresSize = 0;
	
	
	public void addNewScore(Scores score) {
		insertSorted(score);
	}
	
	private void insertSorted(Scores score) {int emptyVac = 0;
		while(scores[emptyVac] != null) {
			emptyVac++;
		}
		scores[emptyVac] = score;
		scoresSize = emptyVac+1;
		if(emptyVac == 0 ) {
			//DO NOTHING
		}
		else {
			for(int i=emptyVac ; i > 0 ; i ++) {
				if(scores[emptyVac-1].getScores() < scores[emptyVac].getScores()) {
					Scores temp = scores[emptyVac-1];
					scores[emptyVac-1] = scores[emptyVac];
					scores[emptyVac] = temp;
				}
				else break;
			}
		}		
	}

	public void getLeaderBoard() {
		int index = 0;
		int score;
		String name;
		String level;
		String filename = "txt.txt";
		InputStream in = this.getClass().getClassLoader().getResourceAsStream(filename);
		reader = new Scanner(in);
		if(reader != null) {
			while(reader.hasNext()) {
				score = reader.nextInt();
				name = reader.next();
				level = reader.next();
				Scores scoreAdd = new Scores(name,score,level);
				scores[index] = scoreAdd;
				index++;
			}
		}
		scoresSize = index;
	}
	
	public void setLeaderBoard() {
		if(scoresSize != 0 ) {
			try {
				writer = new FileWriter("txt.txt", false);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for(int i = 0 ; i < scoresSize ; i++) {
				try {
					writer.write(scores[i].getNames() + " " + scores[i].getScores() + " " + scores[i].getLevels());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// TODO THE XML FILE
}
