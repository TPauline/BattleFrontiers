package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import com.gluonhq.charm.down.Platform;

import application.Main;

public class TypeController implements Initializable {
	public Button exitButton;
	public Button continueButton;
	public Button backButton;
	public BorderPane windowRoot;

	public HBox buttonBarHBox, themeHbox, hRed, hBlue, hGreen;
	private Label l1 = new Label("COMMING SOON");
	public ScrollPane scrollPane;
	public ImageView redTheme;
	public ImageView blueTheme;
	public ImageView greenTheme;

	public ImageView[] imageViewArray = new ImageView[] { redTheme, blueTheme, greenTheme };

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		l1.setId("commingSoon");
		windowRoot.getStylesheets().add("themeScene.css");
		redTheme.getStyleClass().add("redTheme");
		blueTheme.getStyleClass().add("blueTheme");
		greenTheme.getStyleClass().add("greenTheme");
		scrollPane.setStyle("-fx-background-color: black; -fx-background-radius: 10;");
		
		for (int i = 0; i < 1; i++) {
			Image image = new Image(getClass().getClassLoader().getResource("type" + i + ".png").toString());
			VBox h = (VBox) themeHbox.getChildren().get(i);
			h.setId("mode" + i);

			imageViewArray[i] = (ImageView) h.getChildren().get(0);
			imageViewArray[i].setImage(image);
			h.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("hovering over RED theme");
					h.setStyle("-fx-border-color: rgba(0, 100, 100, 0.5); -fx-border-width:2.5;");
					System.out.println("Exited game from GamePlay");

				}

			});

			h.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("stopped hovering over RED theme");
					h.setStyle("-fx-border-color:transparent");
					System.out.println("Exited _____GamePlay");

				}
			});

		}

	}

	public void handleRedMouseEntered() {
		System.out.println("hovering over RED theme");
		hRed.setStyle("-fx-background-color:pink");
		
	}

	public void handleRedMouseExited() {
		System.out.println("stopped hovering over RED theme");
		hRed.setStyle("-fx-background-color:transparent");
	}

	public void handleBlueMouseEntered() {

		System.out.println("hovering over BLUE theme");
		hBlue.setStyle("-fx-background-color:dodgerblue");
	}

	public void handleBlueMouseExited() {
		System.out.println("stopped hovering over BLUE theme");

		hBlue.setStyle("-fx-background-color:transparent");
	}

	public void handleGreenMouseEntered() {
		System.out.println("hovering over GREEN theme");
		hGreen.setStyle("-fx-background-color:greenYellow");
	}

	public void handleGreenMouseExited() {
		System.out.println("stopped hovering over GREEN theme");

		hGreen.setStyle("-fx-background-color:transparent");
	}

	public void handleExitButtonClick() {
		System.out.println("ThemeExit Button Clicked");
		System.exit(0);
	}

	public void handleContinueButtonClick() throws Exception {
		System.out.println("ThemeStart Button Clicked");
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../CharacterScene.fxml"));

			Scene scene = new Scene(root/* ,400,900 */);

			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleBackButtonClick() {
		System.out.println("Theme  BackButton Clicked");
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 Stage s = (Stage) windowRoot.getScene().getWindow();
		
		 s.close();
}

	   @FXML
	    void handleMode1Clicked(MouseEvent event) {
		   Main.gameMode = ((VBox) event.getSource()).getId();
		   System.out.println(Main.gameMode+" Clicked");

		Main.colorTheme = Main.Theme.RED;
	//	buttonBarHBox.setStyle("-fx-background-color:red");
		typeScene();

	}

	   @FXML
	    void handleMode2Clicked(MouseEvent event) {
		   Main.gameMode = ((VBox) event.getSource()).getId();

		   System.out.println(Main.gameMode+" Clicked");
		Main.colorTheme = Main.Theme.BLUE;

	//	buttonBarHBox.setStyle("-fx-background-color:dodgerblue");
		typeScene();

	}

	   @FXML
	    void handleMode3Clicked(MouseEvent event) {
		   Main.gameMode = ((VBox) event.getSource()).getId();

		   System.out.println(Main.gameMode+" Clicked");

		Main.colorTheme = Main.Theme.GREEN;
		//buttonBarHBox.setStyle("-fx-background-color :rgb(0,255,0) ");
		typeScene();
	   }
	   
	   private void typeScene() {
		 
		   try {
				BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../ThemeScene.fxml"));
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
