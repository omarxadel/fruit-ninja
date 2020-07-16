package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu {
	private Pane root = new Pane();
	private Button sound = new Button();
	private Button newGameB = new Button();
	private Button Quit = new Button();
	private Button arcade = new Button();
	private ImageView newGameView,maskView,logoView,ninjaView,deskView,arcadeGameView,quitGameView,soundGameView,backgroundView;
	private Scene scene,gameViewScene;
	private GameView view;
	private Stage window,stage;
	 
	public MainMenu() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		BufferedImage homeMask = null;
		BufferedImage homeDesk = null;
		BufferedImage logo = null;
		BufferedImage ninja = null;
		BufferedImage newGame = null;
		BufferedImage quitGame = null;
		BufferedImage arcadeGame = null;
		BufferedImage soundGame = null;
		BufferedImage background = null;
		try {
			homeMask= ImageIO.read(classLoader.getResource("home-mask.png"));
			logo= ImageIO.read(classLoader.getResource("logo.png"));
			ninja= ImageIO.read(classLoader.getResource("ninja.png"));
			homeDesk= ImageIO.read(classLoader.getResource("home-desc.png"));
			newGame = ImageIO.read(classLoader.getResource("new-game.png"));
			quitGame = ImageIO.read(classLoader.getResource("quit.png"));
			arcadeGame = ImageIO.read(classLoader.getResource("arcade.png"));
			soundGame=ImageIO.read(classLoader.getResource("Sound-on-icon.png"));
			background = ImageIO.read(classLoader.getResource("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

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
		Duration duration = Duration.millis(2500);
		RotateTransition rotateTransition = new RotateTransition(duration, newGameB);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(Animation.INDEFINITE);
	    rotateTransition.play();
		
		
		Image arcadeG = SwingFXUtils.toFXImage(arcadeGame, null);
		arcadeGameView = new ImageView(arcadeG);
		arcade.setGraphic(arcadeGameView);
		arcade.setLayoutX(300);
		arcade.setLayoutY(250);
		arcade.setBackground(null);
		Duration duration2 = Duration.millis(2500);
		RotateTransition rotateTransition2 = new RotateTransition(duration2, arcade);
		rotateTransition2.setByAngle(360);
		rotateTransition2.setCycleCount(Animation.INDEFINITE);
	    rotateTransition2.play();
		
		
		Image quitG = SwingFXUtils.toFXImage(quitGame, null);
		quitGameView = new ImageView(quitG);
		Quit.setGraphic(quitGameView);
		Quit.setLayoutX(530);
		Quit.setLayoutY(270);
		Quit.setBackground(null);
		Duration duration3 = Duration.millis(2500);
		RotateTransition rotateTransition3 = new RotateTransition(duration3, Quit);
		rotateTransition3.setByAngle(360);
		rotateTransition3.setCycleCount(Animation.INDEFINITE);
	    rotateTransition3.play();
		
		
		Image soundG = SwingFXUtils.toFXImage(soundGame, null);
		soundGameView = new ImageView(soundG);
		soundGameView.setFitWidth(40);
		soundGameView.setFitHeight(40);
		sound.setGraphic(soundGameView);
		sound.setLayoutX(680);
		sound.setLayoutY(30);
		sound.setBackground(null);
		Image image = SwingFXUtils.toFXImage(background, null);
		backgroundView = new ImageView(image);
	 }
	
	private void showMenu() {
		root.setVisible(true);
	}
	
	private void hideMenu() {
		root.setVisible(false);
	}

	
	private void setButtonsActions() {
		Quit.setOnMouseDragged(e-> {
				System.exit(0);
		});
		
		sound.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		
			}
		});

		newGameB.setOnMouseDragged(e-> {
		       });

		}
		
	public Scene mainMenu() {
		setButtonsActions();
		root.getChildren().addAll(backgroundView,newGameB,maskView,logoView,ninjaView,deskView,arcade,Quit,sound);
		scene = new Scene(root, 750, 500);
		return scene;
	}
}
