package application;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	public static Stage rootStage;

	public static  MediaPlayer mediaPlayer;
	//public static int boardSize = 11;
	public static String gameMode = null;
	public static enum Theme {
		RED("red"), BLUE("blue"), GREEN("green");

		private String color;

		private Theme(final String color) {
			this.color = color;
		}

		public String getColor() {
			return color;
		}
	}

	public static Theme colorTheme = null;
	public static String profileImg = "blue (1).PNG";

	@Override
	public void start(Stage primaryStage) {
		//Font.getFamilies().forEach(System.out::println);

		String battleshipSquaresTheme = "battleshipSquaresTheme.wav";
		Media sound = new Media(Paths.get(battleshipSquaresTheme).toUri().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
	       public void run() {
		    	   mediaPlayer.seek(Duration.ZERO);
		       }
		   });
		mediaPlayer.play();

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../FirstScene.fxml"));
			Scene scene = new Scene(root,1200,800);
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			System.out.println("::::: "+scene.getWidth());
			
			primaryStage.toFront();
			primaryStage.show();
			rootStage = primaryStage;
			rootStage.setMinWidth(primaryStage.getWidth() - 10);
			rootStage.setMinHeight(primaryStage.getHeight()-10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
