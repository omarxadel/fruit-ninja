package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.GameView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private GameView view;
	
	@Override
	public void start(Stage primaryStage) {
			view = new GameView();
			primaryStage.setScene(view.start());
			primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
