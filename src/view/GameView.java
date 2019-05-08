package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

public class GameView {
	
	public Scene start() {
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		
		Canvas canvas = new Canvas(600, 400);
		root.getChildren().add(canvas);
		
		return scene;
	}
}
