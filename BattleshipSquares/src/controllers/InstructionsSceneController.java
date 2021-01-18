package controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;

public class InstructionsSceneController implements Initializable {
	public Button exitButton;
	public Button continueButton;
	public Button homeButton;
	public BorderPane windowRoot;
	public HBox themeHbox;
	private Label l1 =  new Label("COMMING SOON");
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		l1.setId("commingSoon");
		windowRoot.getStylesheets().add("instructionsScene.css");
		windowRoot.setCenter(l1);
		
		
		
	}
	
	
	public void handleExitButtonClick() {
		System.out.println("Instructions Exit Button Clicked");
		System.exit(0);
	}
	
	public void handleContinueButtonClick() throws Exception {
		System.out.println("Instructions Start Button Clicked");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../CharacterScene.fxml"));
			
			Scene scene = new Scene(root,400,900);	
			
			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public void handleHomeButtonClick() {
		System.out.println("Instructions  BackButton Clicked");
		//okButton.setText("yay!!");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../FirstScene.fxml"));
			Scene scene = new Scene(root,400,900);			
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			//primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
}
