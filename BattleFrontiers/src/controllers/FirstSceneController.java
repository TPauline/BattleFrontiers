package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FirstSceneController implements Initializable {
	public Button exitButton;
	public Button instructionButton;
	public Button startButton;
	public Button signInButton;
	public Button playersButton;
	public Pane windowPane;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private VBox labels;
	boolean ready = false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		buttonBar.setVisible(false);
		
		for (int i = 0; i < 6; i++) {
			Label h = (Label) labels.getChildren().get(i);
			h.setId("label" + i);
			h.setStyle("-fx-border-color: rgba(0, 100, 100); -fx-border-radius: 10; -fx-border-width:2.5;");
		//	h.setStyle("-fx-border-color: rgba(0, 100, 100, 0.5); -fx-border-radius: 10; -fx-border-width:2.5;");

			//h.setStyle("-fx-border-color: rgba(37,183,231); -fx-border-radius: 10; -fx-border-width:2.5;");
			
			h.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("hovering over RED theme");
					h.setStyle("-fx-border-color: rgba(37,183,231); -fx-border-radius: 10; -fx-border-width:2.5;");

				//	h.setStyle("-fx-border-color: rgba(0, 100, 100); -fx-border-radius: 10; -fx-border-width:2.5;");
					System.out.println("Exited game from GamePlay");

				}

			});

			h.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("stopped hovering over RED theme");
				//	h.setStyle("-fx-border-color:transparent");
					h.setStyle("-fx-border-color: rgba(0, 100, 100); -fx-border-radius: 10; -fx-border-width:2.5;");

					System.out.println("Exited _____GamePlay");

				}
			});

		}
	}
	
	public void handleExitButtonClick() {
		System.out.println("Exit Button Clicked");
		System.exit(0);
	}

	public void handleStartButtonClick() {
		System.out.println("Start Button Clicked");
	

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../Mode.fxml"));
			Scene scene = new Scene(root, 800, 400);
			Stage gameMode = new Stage();
		//	root.setBackground(Background.EMPTY);
         
          
			root.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 8;");

			gameMode.initStyle(StageStyle.UNDECORATED);
			gameMode.setScene(scene);
			gameMode.toFront();
			gameMode.initOwner(Main.rootStage);
			gameMode.initModality(Modality.WINDOW_MODAL);
			//primaryStage.setFullScreen(true);
			gameMode.show();
			exitButton.setVisible(false);
			instructionButton.setVisible(false);
			startButton.setVisible(false);
			signInButton.setVisible(false);
			playersButton.setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void handleSignInButtonClick() {
		System.out.println("SignIn Button Clicked");
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../SignInScene.fxml"));
			Scene scene = new Scene(root);

			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleInstructionButtonClick() {
		System.out.println("Instruction Button Clicked");
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../InstructionsScene.fxml"));
			Scene scene = new Scene(root);

			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handlePlayersButtonClick() {
		System.out.println("Players Button Clicked");
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../LeaderBoardScene.fxml"));
			Scene scene = new Scene(root/* ,400,900 */);

			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
