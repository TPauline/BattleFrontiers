package controllers;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FirstSceneController {
	public Button exitButton;
	public Button instructionButton;
	public Button startButton;
	public Button signInButton;
	public Button playersButton;
	public Button bottonBar;
	public Pane windowPane;
	
	public void handleExitButtonClick() {
		System.out.println("Exit Button Clicked");
		//okButton.setText("yay!!");
		System.exit(0);
	}
	
	public void handleStartButtonClick() {
		System.out.println("Start Button Clicked");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../ThemeScene.fxml"));
			Scene scene = new Scene(root,400,900);
			
			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//okButton.setText("yay!!");
	}
	
	public void handleSignInButtonClick() {
		System.out.println("SignIn Button Clicked");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../SignInScene.fxml"));
			Scene scene = new Scene(root,400,900);
			
			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	}
	
	public void handleInstructionButtonClick() {
		System.out.println("Instruction Button Clicked");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../InstructionsScene.fxml"));
			Scene scene = new Scene(root,400,900);
			
			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void handlePlayersButtonClick() {
		System.out.println("Players Button Clicked");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../LeaderBoardScene.fxml"));
			Scene scene = new Scene(root,400,900);
			
			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	}
	
	
	
}
