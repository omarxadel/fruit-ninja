package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

import controller.ArcadeMode;
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
	private GamePlay player = new GamePlay();
	private GameController controller = new GameController();     
	private Pane root;
	private Scene scene;
	private Canvas canvas;
	private GraphicsContext gc;
	private ImageView backgroundView;
	private double mouseX, mouseY;
	private List<GameObject> fruits = new ArrayList();
	private List<GameObject> bombs = new ArrayList();
	private List<GameObject> slices = new ArrayList();
	private Label score = new Label();
	private Label timer = new Label();
	private Label Dtimer = new Label();
	private Button save= new Button("B");
	private Button back2= new Button();
	private AnimationTimer aTimer;
	
	
	
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
	
		root.getChildren().addAll(backgroundView, canvas, levelsMenu(),Dtimer, player.labels());
		Dtimer.setVisible(false);
		//LEVEL 1 SPEED 1000
		//play("LEVEL1");
		saveAction();

		return scene;
	}
	
	
	private void saveAction() {
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			// Save in the file
			}
			
		});
		
		back2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		
				//back to main menu
			
			}
			
		});
		
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
			player.play("Level1",gc,scene);
			home.setVisible(false);
		});
		
		level2.setOnAction(e->{
			player.play("Level2",gc,scene);
			home.setVisible(false);
		});
		
		level3.setOnAction(e->{
			player.play("Level3",gc,scene);
			home.setVisible(false);
		});

		back.setOnAction(e->{
			player.play("ARCADE", gc, scene);
			home.setVisible(false);
		});
		return home;
	}	
}
