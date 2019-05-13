package view;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AlertBox {
	static boolean isOk;
	
	
    public static void display(String Message)
    {

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label text=new Label();
        Label value=new Label();
        text.setText(Message);
        text.setFont(new Font("Arial", 20));


        VBox layout=new VBox(10);

        layout.getChildren().addAll(text,value);
        layout.setAlignment(Pos.CENTER);
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
    	delay.setOnFinished(e -> window.hide());
    	

        window.setMinWidth(350);
        Scene scene=new Scene(layout);

        window.setScene(scene);
        window.show();
        delay.play();
    	
    }
    
    public static boolean displayDialogue(String Message, String button1, String button2)
    {

        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
       	isOk = false;
        Label text=new Label();
        Button ok = new Button(button1);
        Button no = new Button(button2);
        text.setText(Message);
        text.setFont(new Font("Arial", 20));

        ok.setOnAction(e->{
        	isOk = true;
        	window.close();
        });
        
        no.setOnAction(e->{
        	isOk = false;
        	window.close();
        });
        
        HBox buttons = new HBox(50);
        VBox layout=new VBox(10);

        buttons.getChildren().addAll(ok,no);
        layout.getChildren().addAll(text,buttons);
        layout.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        window.setMinWidth(350);
        Scene scene=new Scene(layout);

        window.setScene(scene);
        window.showAndWait();
    	return isOk;
    }
}

