package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameView {
	
	public Scene start() {
		Pane root = new Pane();
		Scene scene = new Scene(root, 750, 500);
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		BufferedImage background = null;
		try {
			background = ImageIO.read(classLoader.getResource("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = SwingFXUtils.toFXImage(background, null);
		ImageView backgroundView = new ImageView(image);
		Canvas canvas = new Canvas(600, 400);
		root.getChildren().addAll(backgroundView, canvas);
		
		return scene;
	}
}
