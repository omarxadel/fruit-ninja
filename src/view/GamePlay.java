package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import controller.ArcadeMode;
import controller.Context;
import controller.GameController;
import controller.Level1;
import controller.Level2;
import controller.Level3;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
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

public class GamePlay {
	private GameController controller = new GameController();
	private Label lives = new Label();
	private Label score = new Label();
	private Label timer = new Label();
	private Label Dtimer = new Label();
	private Pane root = new Pane();	
	private Button back2 = new Button();
	private AnimationTimer aTimer;
	private List fruits = new ArrayList();
	private List slices = new ArrayList();
	private List bombs = new ArrayList();
	private List specialFruits = new ArrayList();
	private int speed = 2, minutes, seconds;
	private String path = new File ("res/over.mp3").getAbsolutePath();
	private Media mediafile= new Media (new File(path).toURI().toString());
	private MediaPlayer mediaplayer= new MediaPlayer(mediafile);
	private ImageView gameOverView,backView;
	
	
	public Pane labels() {
		//root.getChildren().addAll(lives, score, timer, Dtimer);
		root.setVisible(false);
		return root;
	}

	public void play(String level, GraphicsContext gc, Scene scene) {
		root.setVisible(true);
		if(level.equalsIgnoreCase("ARCADE")) {
			controller.newGame(new Context(new ArcadeMode()));
			score.setText("Score: "+ controller.getScoreCount());
			score.setTextFill(Color.ANTIQUEWHITE);
			score.setFont(Font.font(18));
			root.getChildren().addAll(score,back2,Dtimer);
			back2.setVisible(false);
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
					updateLabels();
					gc.clearRect(0, 0, 750, 500);
					controller.controlGameObjects(fruits, bombs, slices , scene, gc);
				}	
			};
			aTimer.start();
			arcadeTimer(gc,scene);
		}
		else {
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
			root.getChildren().addAll(score, lives,back2);
			score.setLayoutX(10);
			score.setLayoutY(10);
			back2.setVisible(false);
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
					gameUpdate(gc, scene);
				}
	
				
			};
			aTimer.start();
			timer();
		}
		
		
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


		private void gameUpdate(GraphicsContext gc, Scene scene) {
			updateLabels();
				gc.clearRect(0, 0, 750, 500);
				if(controller.getLivesCount()!=0) {
				controller.controlGameObjects(fruits, bombs, slices , scene, gc);
				mediaplayer.play();
				}
				else if(controller.getLivesCount() == 0) {
					BufferedImage gameOver = null;
					BufferedImage BackB = null;
					ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					try {
						 gameOver = ImageIO.read(classLoader.getResource("game-over.png"));
						 BackB=ImageIO.read(classLoader.getResource("BACK.png"));
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					Image image = SwingFXUtils.toFXImage(gameOver, null);
					gameOverView = new ImageView(image);
					Image image2 = SwingFXUtils.toFXImage(BackB, null);
					backView = new ImageView(image2);
					back2.setGraphic(backView);
					root.getChildren().addAll(gameOverView);
					gameOverView.setLayoutX(125);
					gameOverView.setLayoutY(200);
					gameOverView.setVisible(true);	
					back2.setVisible(true);
					back2.setBackground(null);
					back2.setLayoutX(250);
					back2.setLayoutY(300);
					saveAction();
					
				}
			}
			
		private void arcadeTimer(GraphicsContext gc, Scene scene) {
			seconds = 60;
			Timeline timecounter = new Timeline(new KeyFrame(Duration.millis(1000), event-> {
				seconds--;
				if(seconds>=0) {
				controller.controlGameObjects(fruits, bombs, slices , scene, gc);
				Dtimer.setText("00:" + seconds);
				}
				else {
					BufferedImage gameOver = null;
					BufferedImage BackB = null;
					ClassLoader classLoader = ClassLoader.getSystemClassLoader();
					try {
						 gameOver = ImageIO.read(classLoader.getResource("game-over.png"));
						 BackB=ImageIO.read(classLoader.getResource("BACK.png"));
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					Image image = SwingFXUtils.toFXImage(gameOver, null);
					gameOverView = new ImageView(image);
					Image image2 = SwingFXUtils.toFXImage(BackB, null);
					backView = new ImageView(image2);
					back2.setGraphic(backView);
					root.getChildren().addAll(gameOverView);
					gameOverView.setLayoutX(125);
					gameOverView.setLayoutY(200);
					gameOverView.setVisible(true);	
					back2.setVisible(true);
					back2.setBackground(null);
					back2.setLayoutX(250);
					back2.setLayoutY(300);
					saveAction();
				}
					
			}));
			timecounter.setCycleCount(500);
			timecounter.play();
			Dtimer.setFont(Font.font(18));
			Dtimer.setTextFill(Color.ANTIQUEWHITE);
			Dtimer.setVisible(true);
			Dtimer.setLayoutX(600);
			lives.setVisible(false);
		}
		
		private void saveAction() {
			// TODO Auto-generated method stub
			
		}


			public void updateLabels() {
				lives.setText("Remaining Lives: "+controller.getLivesCount());
				score.setText("Score: "+ controller.getScoreCount());
				score.setLayoutY(40);
			}
}
