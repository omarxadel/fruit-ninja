package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import view.GameView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import view.MainMenu;


public class Main extends Application {
	private MainMenu view;
	
	@Override
	public void start(Stage primaryStage) {
			view = new MainMenu();
			primaryStage.setScene(view.mainMenu());
			primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);	

	}	
	}

