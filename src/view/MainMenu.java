package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MainMenu {
	private Pane root = new Pane();
	private Button sound = new Button();
	private Button newGameB = new Button();
	private Button Quit = new Button();
	private Button arcade = new Button();
	private ImageView newGameView,maskView,logoView,ninjaView,deskView,arcadeGameview,quitGameView,soundGameView;
	
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
		try {
			homeMask= ImageIO.read(classLoader.getResource("home-mask.png"));
			logo= ImageIO.read(classLoader.getResource("logo.png"));
			ninja= ImageIO.read(classLoader.getResource("ninja.png"));
			homeDesk= ImageIO.read(classLoader.getResource("home-desc.png"));
			newGame = ImageIO.read(classLoader.getResource("new-game.png"));
			quitGame = ImageIO.read(classLoader.getResource("quit.png"));
			arcadeGame = ImageIO.read(classLoader.getResource("arcade.png"));
			soundGame=ImageIO.read(classLoader.getResource("Sound-on-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
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
		Image soundG = SwingFXUtils.toFXImage(soundGame, null);
		soundGameView = new ImageView(soundG);
		soundGameView.setFitWidth(40);
		soundGameView.setFitHeight(40);
		sound.setGraphic(soundGameView);
		sound.setLayoutX(680);
		sound.setLayoutY(30);
		sound.setBackground(null);
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
		}
	
	public Pane mainMenu() {
		setButtonsActions();
		root.getChildren().addAll(newGameB,maskView,logoView,ninjaView,deskView,arcade,Quit,sound);
		return root;
	}
}
