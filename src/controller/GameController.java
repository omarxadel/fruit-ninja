package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import model.Fruit;
import model.GameObject;
import view.AlertBox;

public class GameController {
	private Context context;
	private double mouseX, mouseY;
	private boolean mouseReleased = true;
	private int comboCounter = 0;
	private int livesCount = 3;
	private int scoreCount = 0;
	private List slices = new ArrayList();
	private ObjectDecorator slicer;
	
		public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}
	
	// MOUSE LISTENER:
		
	public void mouseListener(Scene scene) {
		scene.setOnMouseDragged(e->{
			mouseX = e.getX();
			mouseY = e.getY();
			setMouseReleased(false);
		});
		scene.setOnMouseReleased(e->{
			mouseX = 0;
			mouseY = 0;
			setMouseReleased(true);
		});
	}
	
	public double getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public boolean isMouseReleased() {
		return mouseReleased;
	}

	public void setMouseReleased(boolean mouseReleased) {
		this.mouseReleased = mouseReleased;
	}
	
	// GAME ACTIONS
	
	public void newGame(Context context) {
		setContext(context);
	}
	
	public boolean mouseIntersects(GameObject gameObject) {
		mouseX = getMouseX();
		mouseY = getMouseY();
		Rectangle2D mouseBoundaries = new Rectangle2D(mouseX, mouseY, 5, 5);
		return (gameObject.getBoundaries().intersects(mouseBoundaries));
	}

	public void comboCount() {
		comboCounter ++;		
	}
	
	public int getComboCounter() {
		return comboCounter;
	}
	public void resetCombo() {
		this.comboCounter=0;
	}
	
	public int comboCheck() {
		int scoreCount = 0;
		if(isMouseReleased()) {
			if(getComboCounter() > 4) {
			scoreCount+=getComboCounter();
				AlertBox.display("COMBO " + getComboCounter());
			}
			resetCombo();
			}
		return scoreCount;
	}
	
	public GameObject createGameObject() {
		return context.createGameObject();
	}
	
	public void controlGameObjects(List fruits, List bombs, List slices1, Scene scene, GraphicsContext gc) {
		mouseListener(scene);
		Iterator<GameObject> iterator = fruits.iterator();
		while(iterator.hasNext()) {
			setScoreCount(getScoreCount() + comboCheck());
			GameObject object = iterator.next();
			if(mouseIntersects(object)) {
				try {
					showSlices(object, gc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!isMouseReleased()) {
					comboCount();
				}			
				iterator.remove();
				setScoreCount(getScoreCount() + 1);
			}
			else if(object.hasMovedOffScreen()){
				iterator.remove();
				setLivesCount(getLivesCount() - 1);
			}
			else {
				object.move(3);
				object.render(gc);
			}		
	}
		iterator = bombs.iterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.next();
			if(mouseIntersects(object)) {
				iterator.remove();
				setLivesCount(getLivesCount() - 1);
			}
			else if(object.hasMovedOffScreen()){
					iterator.remove();
			}
			else {
				object.move(3);
				object.render(gc);
			}
			}
		iterator = slices.iterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.next();
			
			if(object.hasMovedOffScreen()){
					iterator.remove();
			}
			else {
				object.move(3);
				object.render(gc);
			}
			}
	}

	public int getLivesCount() {
		return livesCount;
	}

	public void setLivesCount(int livesCount) {
		this.livesCount = livesCount;
	}

	public int getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}
	
	public void showSlices(GameObject object, GraphicsContext gc) throws IOException {
		if(object instanceof Fruit) {
			slicer = new FruitDecorator(object);
		}
		slicer.setXlocation(object.getXlocation());
		slicer.setYlocation(object.getYlocation());
		slicer.render(gc);
		slices.add(slicer);
	}
}
