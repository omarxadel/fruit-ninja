package controller;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import model.GameObject;

public class GameController {
	private Context context;
	private double mouseX, mouseY;
	
	public void clearMousePositions() {
		mouseX = 0;
		mouseY = 0;
	}
	
	public void mouseListener(Scene scene) {
		scene.setOnMouseDragged(e->{
			mouseX = e.getX();
			mouseY = e.getY();
		});
	}
	
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
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
	
}
