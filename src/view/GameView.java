package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import controller.ObjectFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import model.GameObject;

public class GameView {
	private Pane root;
	private Scene scene;
	private ImageView backgroundView;
	private Canvas canvas;
	private GraphicsContext gc;
	private double mouseX, mouseY;
	private ObjectFactory object = new ObjectFactory();
	private List<GameObject> fruits = new ArrayList();
	private int speed;
	
	
	public Scene start() {
		root = new Pane();
		scene = new Scene(root, 750, 500);
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		BufferedImage background = null;
		try {
			background = ImageIO.read(classLoader.getResource("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = SwingFXUtils.toFXImage(background, null);
		backgroundView = new ImageView(image);
		canvas = new Canvas(750, 500);
		gc = canvas.getGraphicsContext2D();
		root.getChildren().addAll(backgroundView, canvas);
		play(750);
		return scene;
	}
	
	private void play(int speed) {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed), event -> {
            for(int i = 0 ; i < 3 ; i++) {
            	GameObject obj = null;
            	int shows = (int) (Math.random()*4);
            	switch(shows) {
            	case 1 : 
            		obj = apple();
            		break;
            	case 2 :
            		obj = banana();
            		break;
            	}
            	if(obj != null)
            		gc.drawImage(getImage(obj), obj.getXlocation(), obj.getYlocation());
            }
		}));
		timeline.setCycleCount(500);
		timeline.play();
	}
	
	private GameObject apple() {
		return object.getObject("Apple");
	}
	
	private GameObject banana() {
		return object.getObject("Banana");
	}
	
	private GameObject bomb() {
		return object.getObject("fatalbomb");
	}

	private Image getImage(GameObject object) {
        return SwingFXUtils.toFXImage(object.getBufferedImages(), null);
	}
}
