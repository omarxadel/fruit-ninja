package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import controller.Context;
import controller.GameController;
import controller.Level1;
import controller.ObjectFactory;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.GameObject;

public class GameView {
	private Context context;
	private GameController controller = new GameController();
	private Pane root;
	private Scene scene;
	private ImageView backgroundView;
	private Canvas canvas;
	private GraphicsContext gc;
	private double mouseX, mouseY;
	private ObjectFactory object = new ObjectFactory();
	private List<GameObject> fruits = new ArrayList();
	private int speed = 4, minutes, seconds;
	private Label score = new Label();
	private Label timer = new Label();
	private int scoreCount = 0;
	private AnimationTimer aTimer;
	
	
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
	
	private void hideMenu() {
		// TODO Hide the menu buttons & images
	}
	
	private void showMenu() {
		// TODO Show the menu buttons & images
	}
	
	private void setButtonsActions() {
		// TODO Set the actions for every button using the controller
	}

	private void play(int speed) {
		score.setText("Score: "+ scoreCount);
		score.setTextFill(Color.ANTIQUEWHITE);
		score.setFont(Font.font(18));
		root.getChildren().add(score);
		score.setLayoutX(10);
		score.setLayoutY(10);
		context = new Context(new Level1());
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(speed), event -> {
            GameObject object = context.createGameObject();
			if(object != null)
				fruits.add(object);
		}));
		timeline.setCycleCount(500);
		timeline.play();
		
		aTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				gameUpdate();
				controller.clearMousePositions();
			}

		
			
		};
		aTimer.start();
		
		timer();
		
	}	
	
	private void timer() {
		minutes = 0;
		seconds = 0;
		Timeline timecounter = new Timeline(new KeyFrame(Duration.millis(1000), event-> {
			seconds++;
			if(seconds >= 60) {
				minutes += seconds/60;
				seconds = seconds%60;
			}
			timer.setText("Time: 0" + minutes + ":" + seconds);
		}));
		timecounter.setCycleCount(500);
		timecounter.play();
		timer.setFont(Font.font(18));
		timer.setTextFill(Color.ANTIQUEWHITE);
		root.getChildren().add(timer);
		timer.setLayoutX(650);
		timer.setLayoutY(10);
	}	
	
	private void gameUpdate() {
		score.setText("Score: "+ scoreCount);
		controller.mouseListener(scene);
		mouseX = controller.getMouseX();
		mouseY = controller.getMouseY();
		gc.clearRect(0, 0, 750, 500);
		for(int i = 0 ; i < fruits.size() ; i++) {
			if(mouseIntersects(fruits.get(i))) {
				fruits.remove(i);
				scoreCount++;
			}
			else if(fruits.get(i).hasMovedOffScreen()){
				fruits.remove(i);
			}
			else {
				fruits.get(i).setYlocation(fruits.get(i).getYlocation() - speed - fruits.get(i).getYlocation()/150);
				fruits.get(i).render(gc);
			}
				
		}
	}

	private boolean mouseIntersects(GameObject gameObject) {
		return ((gameObject.getXlocation()<=mouseX  && (gameObject.getYlocation() <= mouseY)));
	}
}
