package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;

public class ThemeSceneController implements Initializable {
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
	// private int position = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		l1.setId("commingSoon");
		windowRoot.getStylesheets().add("themeScene.css");
		redTheme.getStyleClass().add("redTheme");
		blueTheme.getStyleClass().add("blueTheme");
		greenTheme.getStyleClass().add("greenTheme");

		// windowRoot.setCenter(l1);

		for (int i = 0; i < imageViewArray.length; i++) {
			// position = i;
			/// BattleshipSquares/theme0.png
			/// BattleshipSquares/src/controllers/ThemeSceneController.java
			// input = new FileInputStream("../../../theme"+i+".png");

			Image image = new Image(getClass().getClassLoader().getResource("theme" + i + ".png").toString());
//			Reflection r = new Reflection();
//			r.setFraction(0.6);
//			Rectangle rectangle = new Rectangle(0, 0, (5 + image.getWidth()),( 5 + image.getHeight()));
//			rectangle.setFill(Color.GREENYELLOW);
			HBox h = (HBox) themeHbox.getChildren().get(i);
			h.setId("theme" + i);

			imageViewArray[i] = (ImageView) h.getChildren().get(0);
			imageViewArray[i].setImage(image);

			// imageViewArray[i].setEffect(r);

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
		// okButton.setText("yay!!");
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../FirstScene.fxml"));
			Scene scene = new Scene(root/* ,400,900 */);
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			// primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void handleRedTheme() {
		System.out.println("Theme  redTheme Clicked");

		Main.colorTheme = Main.Theme.RED;
		buttonBarHBox.setStyle("-fx-background-color:red");

	}

	public void handleBlueTheme() {

		System.out.println("Theme  blueTheme Clicked");
		Main.colorTheme = Main.Theme.BLUE;

		buttonBarHBox.setStyle("-fx-background-color:dodgerblue");

	}

	public void handleGreenTheme() {
		System.out.println("Theme  GreenTheme Clicked");

		Main.colorTheme = Main.Theme.GREEN;
		buttonBarHBox.setStyle("-fx-background-color :rgb(0,255,0) ");

	}

}
