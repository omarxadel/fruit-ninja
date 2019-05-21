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
import controller.Level2;
import controller.Level3;
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
import model.Fruit;
import model.GameObject;

public class GameView {
	private MainMenu main = new MainMenu();
	private GameController controller = new GameController();     
	private Pane root;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private ImageView backgroundView;
	private ImageView gameOverView;
	private double mouseX, mouseY;
	private List<GameObject> fruits = new ArrayList();
	private List<GameObject> bombs = new ArrayList();
	private List<GameObject> slices = new ArrayList();
	private int speed = 2, minutes, seconds;
	private Label score = new Label();
	private Label timer = new Label();
	private Label lives = new Label();
	private Button save= new Button();
	private Button back= new Button("back");
	private AnimationTimer aTimer;
	private File af;
	private Media mf;
	private MediaPlayer mp;
	
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
		save.setLayoutX(500);
	
		

		root.getChildren().addAll(backgroundView, canvas, levelsMenu(),save,back);
		back.setVisible(false);
		//LEVEL 1 SPEED 1000
		//play("LEVEL1");
		saveAction();
		gameUpdate();

		return scene;
	}
	
	private void saveAction() {
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			// Save in the file
			}
			
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			//back to MainMenu
			}
			
		});
		
	}

	private void play(String level) {
		if(level.equalsIgnoreCase("LEVEL1")) {
			controller.newGame(new Context(new Level1()));
		}
		else if(level.equalsIgnoreCase("LEVEL2")) {
			controller.newGame(new Context(new Level2()));
		}
		else {
			controller.newGame(new Context(new Level3()));
		}
		
		lives.setText("Remaining Lives: "+ controller.getLivesCount());
		lives.setTextFill(Color.ANTIQUEWHITE);
		lives.setFont(Font.font(18));
		score.setText("Score: "+ controller.getScoreCount());
		score.setTextFill(Color.ANTIQUEWHITE);
		score.setFont(Font.font(18));
		root.getChildren().addAll(score, lives);
		score.setLayoutX(10);
		score.setLayoutY(10);
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(controller.getContext().getSpeed()), event -> {
            for(int objects=0; objects < controller.getContext().getPhaseObjects();objects++) {
				GameObject object = controller.createGameObject();
				if(object instanceof Fruit && object != null)
					fruits.add(object);
				else if(object != null)
					bombs.add(object);
            }
		}));
		timeline.setCycleCount(500);
		timeline.play();
		
		aTimer = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				gameUpdate();
			}

		
			
		};
		aTimer.start();
		
		timer();
		
		
		
		
	}	
	
	private Pane levelsMenu() {
		Pane home = new Pane();
		Button level1 = new Button();
		Button level2 = new Button();
		Button level3 = new Button();
		Button arcade = new Button();
		Button back = new Button();
		BufferedImage i1,i2,i3,a1,b1;
		ImageView l1,l2,l3,ar,b;
		Image l1i,l2i,l3i,ari,bi;
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		try {
			i1 = ImageIO.read(classLoader.getResource("LEVEL1.png"));
			l1i = SwingFXUtils.toFXImage(i1, null);
			l1 = new ImageView(l1i);
			level1.setGraphic(l1);
			level1.setBackground(null);
			
			i2 = ImageIO.read(classLoader.getResource("LEVEL2.png"));
			l2i = SwingFXUtils.toFXImage(i2, null);
			l2 = new ImageView(l2i);
			level2.setGraphic(l2);
			level2.setBackground(null);
			
			i3 = ImageIO.read(classLoader.getResource("LEVEL3.png"));
			l3i = SwingFXUtils.toFXImage(i3, null);
			l3 = new ImageView(l3i);
			level3.setGraphic(l3);
			level3.setBackground(null);
			
			b1 = ImageIO.read(classLoader.getResource("BACK.png"));
			bi = SwingFXUtils.toFXImage(b1, null);
			b = new ImageView(bi);
			back.setGraphic(b);
			back.setBackground(null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		home.getChildren().addAll(level1,level2,level3,back);
		level1.setLayoutX(270);
		level2.setLayoutX(270);
		level3.setLayoutX(270);
		back.setLayoutX(270);
		level1.setLayoutY(130);
		level2.setLayoutY(200);
		level3.setLayoutY(270);
		back.setLayoutY(340);
		
		level1.setOnAction(e->{
			play("Level1");
			home.setVisible(false);
		});
		
		level2.setOnAction(e->{
			play("Level2");
			home.setVisible(false);
		});
		
		level3.setOnAction(e->{
			play("Level3");
			home.setVisible(false);
		});
		return home;
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
		if(controller.getLivesCount()!=0)
		controller.controlGameObjects(fruits, bombs, slices , scene, gc);
		else if(controller.getLivesCount() == 0) {
			BufferedImage gameOver = null;
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			try {
				 gameOver = ImageIO.read(classLoader.getResource("game-over.png"));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			back.setLayoutX(100);
			back.setLayoutY(100);
			Image image = SwingFXUtils.toFXImage(gameOver, null);
			gameOverView = new ImageView(image);
			root.getChildren().addAll(gameOverView);
			gameOverView.setLayoutX(125);
			gameOverView.setLayoutY(200);
			gameOverView.setVisible(true);	
			back.setVisible(true);
			
		}
		
	}

	

	
	private void updateLabels() {
		lives.setText("Remaining Lives: "+controller.getLivesCount());
		score.setText("Score: "+ controller.getScoreCount());
	}
	

}
