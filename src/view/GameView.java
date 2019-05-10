package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

import controller.Command;
import controller.Context;
import controller.GameController;
import controller.Level1;
import controller.ObjectFactory;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.GameObject;

public class GameView {
	private MainMenu main = new MainMenu();
	private Context context;
	private GameController controller = new GameController();
	private Pane root;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private ImageView backgroundView;
	private double mouseX, mouseY;
	private List<GameObject> fruits = new ArrayList();
	private int speed = 2, minutes, seconds;
	private Label score = new Label();
	private Label timer = new Label();
	private Label lives = new Label();

	private int scoreCount = 0;
	private AnimationTimer aTimer;
	private File af;
	private Media mf;
	private MediaPlayer mp;
	private int livesCount = 3;
	Command command;
	
	public Scene start() {
		BufferedImage background = null;
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try {
			 background = ImageIO.read(classLoader.getResource("background.jpg"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Image image = SwingFXUtils.toFXImage(background, null);
		backgroundView = new ImageView(image);
		
		root = new Pane();
		scene = new Scene(root, 750, 500);
		canvas = new Canvas(750, 500);
		gc = canvas.getGraphicsContext2D();
		
		play(750);
		
		root.getChildren().addAll(backgroundView, canvas);
		
		return scene; 

	}

	private void play(int speed) {
		lives.setText("Remaining Lives: "+ livesCount);
		lives.setTextFill(Color.ANTIQUEWHITE);
		lives.setFont(Font.font(18));
		score.setText("Score: "+ scoreCount);
		score.setTextFill(Color.ANTIQUEWHITE);
		score.setFont(Font.font(18));
		root.getChildren().addAll(score, lives);
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
		updateLabels();
		gc.clearRect(0, 0, 750, 500);
		if(livesCount == 0) {
			// TODO lose game
		}
		Iterator<GameObject> iterator = fruits.iterator();
		while(iterator.hasNext()) {
			controller.mouseListener(scene);
			GameObject object = iterator.next();
			if(mouseIntersects(object)) {
				iterator.remove();
				scoreCount++;
			}
			else if(object.hasMovedOffScreen()){
				iterator.remove();
				livesCount--;
			}
			else {
				object.move(speed);
				object.render(gc);
			}
				
		}
	}

	private boolean mouseIntersects(GameObject gameObject) {
		mouseX = controller.getMouseX();
		mouseY = controller.getMouseY();
		Rectangle2D mouseBoundaries = new Rectangle2D(mouseX, mouseY, 5, 5);
		return (gameObject.getBoundaries().intersects(mouseBoundaries));
	}
	
	private void updateLabels() {
		lives.setText("Remaining Lives: "+livesCount);
		score.setText("Score: "+ scoreCount);
	}
}
