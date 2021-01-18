package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


import application.Main;

public class CharacterSceneController implements Initializable {
	public Button exitButton;
	public Button continueButton;
	public Button backButton;
	public BorderPane windowRoot;

	public HBox buttonBarHBox,themeHbox, hRed, hBlue, hGreen;
	private Label l1 =  new Label("COMMING SOON");
	public ScrollPane scrollPane;
	public ImageView redTheme;
	public ImageView blueTheme;
	public ImageView greenTheme;
	
	public static String profileImg = "blue (1).PNG";
	

	public ImageView[] imageViewArray = new ImageView[11] ;
	//private int position = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		l1.setId("commingSoon");
		windowRoot.getStylesheets().add("themeScene.css");
		setButtonBar();
		//windowRoot.setCenter(l1);

		for(int i =0; i < imageViewArray.length; i++) {
			// green (1).PNG
			Image image = new Image(getClass().getClassLoader().getResource("blue ("+(i+1)+").PNG").toString());
			if (Main.colorTheme != null) {
				switch(Main.colorTheme){
				case RED :
					image = new Image(getClass().getClassLoader().getResource("red ("+(i+1)+").PNG").toString());
				break;
				case BLUE :
					image = new Image(getClass().getClassLoader().getResource("blue ("+(i+1)+").PNG").toString());
					break;
				case GREEN :
					image = new Image(getClass().getClassLoader().getResource("green ("+(i+1)+").PNG").toString());

					break;
				}
			HBox h = (HBox)themeHbox.getChildren().get(i);
			h.setId("theme"+i);

			imageViewArray[i] =(ImageView) h.getChildren().get(0) ;
			imageViewArray[i].setImage(image);
			
			
			h.setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("hovering over RED theme");
					h.setStyle("-fx-background-color:yellow");	
					System.out.println("Exited game from GamePlay");
								
				}

			
			});
			
			h.setOnMouseExited(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					System.out.println("stopped hovering over RED theme");
					h.setStyle("-fx-background-color:transparent");
					System.out.println("Exited _____GamePlay");
							
				}

			
			});
			int j = i;
			
			h.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (Main.colorTheme != null) {
						switch(Main.colorTheme){
						case RED :
							Main.profileImg = new String("red ("+(j+1)+").PNG");

						break;
						case BLUE :
							Main.profileImg = new String("blue ("+(j+1)+").PNG");

							break;
						case GREEN :
							Main.profileImg = new String("green ("+(j+1)+").PNG");

							break;
						}
					System.out.println("....y");
							
				}

				}
			});
			}
			}

	}
	
	int getPos(int i) {
		return i;
	}

	public void handleExitButtonClick() {
		System.out.println("ThemeExit Button Clicked");
		System.exit(0);
	}

	public void handleContinueButtonClick() throws Exception {
		System.out.println("CharacterStart Button Clicked");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../GamePlayScene.fxml"));
			Scene scene = new Scene(root,400,900);			
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			//primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		//okButton.setText("yay!!");
//		GamePlayM battleshipSquares = new GamePlayM();
//		Main.rootStage.close();
//		Main.rootStage = new Stage();
//		battleshipSquares.start(Main.rootStage);
	}
	public void handleBackButtonClick() {
		System.out.println("Theme  BackButton Clicked");
		//okButton.setText("yay!!");
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../ThemeScene.fxml"));
			Scene scene = new Scene(root,400,900);			
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			//primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	public void setButtonBar() {
		System.out.println("Theme  GreenTheme Clicked");

		if (Main.colorTheme != null) {
			switch(Main.colorTheme){
			case RED :
				buttonBarHBox.setStyle("-fx-background-color:red");	

			break;
			case BLUE :
				buttonBarHBox.setStyle("-fx-background-color:dodgerblue");	

				break;
			case GREEN :
				buttonBarHBox.setStyle("-fx-background-color :rgb(0,255,0) ");


				break;
			}
		}




	}

}
