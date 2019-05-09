package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
	private Context context;
	private GameController controller = new GameController();
	private Pane root;
	private Scene scene;
	private ImageView backgroundView,newGameView,maskView,logoView,ninjaView,deskView,arcadeGameview,quitGameView;
	private Canvas canvas;
	private GraphicsContext gc;
	private double mouseX, mouseY;
	private ObjectFactory object = new ObjectFactory();
	private List<GameObject> fruits = new ArrayList();
	private int speed = 2, minutes, seconds;
	private Label score = new Label();
	private Label timer = new Label();
	private Label lives = new Label();
	private Button newGameB = new Button();
	private Button Quit = new Button();
	private Button arcade = new Button();
	private int scoreCount = 0;
	private AnimationTimer aTimer;
	private File af;
	private Media mf;
	private MediaPlayer mp;
	private int livesCount = 3;
	
	public GameView() {
		root = new Pane();
		initializeButtons();
	}
	
	public Scene start() {
		
		scene = new Scene(root, 750, 500);
		canvas = new Canvas(750, 500);
		gc = canvas.getGraphicsContext2D();
		
		root.getChildren().addAll(backgroundView, canvas);
		//root.getChildren().addAll(newGameB,maskView,logoView,ninjaView,deskView,arcade,Quit);
		play(750);
		return scene; 

	}
	
	private void initializeButtons() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		BufferedImage background = null;
		BufferedImage homeMask = null;
		BufferedImage homeDesk = null;
		BufferedImage logo = null;
		BufferedImage ninja = null;
		BufferedImage newGame = null;
		BufferedImage quitGame = null;
		BufferedImage arcadeGame = null;
		try {
			background = ImageIO.read(classLoader.getResource("background.jpg"));
			homeMask= ImageIO.read(classLoader.getResource("home-mask.png"));
			logo= ImageIO.read(classLoader.getResource("logo.png"));
			ninja= ImageIO.read(classLoader.getResource("ninja.png"));
			homeDesk= ImageIO.read(classLoader.getResource("home-desc.png"));
			newGame = ImageIO.read(classLoader.getResource("new-game.png"));
			quitGame = ImageIO.read(classLoader.getResource("quit.png"));
			arcadeGame = ImageIO.read(classLoader.getResource("arcade.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = SwingFXUtils.toFXImage(background, null);
		backgroundView = new ImageView(image);

		canvas = new Canvas(750, 500);
		gc = canvas.getGraphicsContext2D();
		
		/*File af=new File("C:\\Users\\zzz\\Desktop\\Fruit-Ninja-Theme-Song.mp3");
		Media mf=new Media(af.toURI().toString());
		MediaPlayer mp=new MediaPlayer(mf);
		mp.setAutoPlay(true);
		mp.setVolume(0.3);*/

		Image image1 = SwingFXUtils.toFXImage(homeMask, null);
		maskView = new ImageView(image1);
		maskView.setFitWidth(750);
		Image image2 = SwingFXUtils.toFXImage(logo, null);
		logoView = new ImageView(image2);
		logoView.setLayoutX(30);
		Image image3 = SwingFXUtils.toFXImage(ninja, null);
		ninjaView = new ImageView(image3);
		ninjaView.setLayoutX(350);
		ninjaView.setLayoutY(30);
		Image image4 = SwingFXUtils.toFXImage(homeDesk, null);
		deskView = new ImageView(image4);
		deskView.setLayoutX(30);
		deskView.setLayoutY(180);
		Image newG = SwingFXUtils.toFXImage(newGame, null);
		newGameView = new ImageView(newG);
		newGameB.setGraphic(newGameView);
		newGameB.setLayoutX(20);
		newGameB.setLayoutY(250);
		newGameB.setBackground(null);
		Image arcadeG = SwingFXUtils.toFXImage(arcadeGame, null);
		arcadeGameview = new ImageView(arcadeG);
		arcade.setGraphic(arcadeGameview);
		arcade.setLayoutX(300);
		arcade.setLayoutY(250);
		arcade.setBackground(null);
		Image quitG = SwingFXUtils.toFXImage(quitGame, null);
		quitGameView = new ImageView(quitG);
		Quit.setGraphic(quitGameView);
		Quit.setLayoutX(530);
		Quit.setLayoutY(270);
		Quit.setBackground(null);

	 }

	private void hideMenu() {
		// TODO Hide the menu buttons & images
	}
	
	private void showMenu() {
		newGameB.setVisible(true);
		arcade.setVisible(true);
		Quit.setVisible(true);
	}
	
	private void setButtonsActions() {
		// TODO Set the actions for every button using the controller
		
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
			System.out.println("YOU LOST");
		}
		Iterator<GameObject> iterator = fruits.iterator();
		while(iterator.hasNext()) {
			controller.mouseListener(scene);
			GameObject object = iterator.next();
			if(mouseIntersects(object)) {
				iterator.remove();
				scoreCount++;
				System.out.println("INTERSECTS");
			}
			else if(object.hasMovedOffScreen()){
				iterator.remove();
				livesCount--;
			}
			else {
				object.setYlocation(object.getYlocation() - speed - object.getYlocation()/150);
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
