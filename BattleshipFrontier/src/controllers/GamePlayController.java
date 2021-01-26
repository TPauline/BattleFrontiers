package controllers;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.Board;
import application.ShipPart;
import application.Main;
import application.Ship;

public class GamePlayController implements Initializable {

	@FXML
	private BorderPane windowRoot;

	@FXML
	private HBox buttonBarHBox;

	@FXML
	private Button homeButton;

	@FXML
	private Button exitButton;

	@FXML
	private ImageView playerImage;

	@FXML
	private ImageView opponentImage;

	@FXML
	private VBox opponentArmada;

	@FXML
	private HBox oppcarrier;

	@FXML
	private HBox oppbattleship;

	@FXML
	private HBox oppcruiser;

	@FXML
	private HBox oppsubmarine;

	@FXML
	private HBox oppdestroyer;

	@FXML
	private VBox PlayerArmada;

	@FXML
	private HBox carrier;

	@FXML
	private HBox battleship;

	@FXML
	private HBox cruiser;

	@FXML
	private HBox submarine;

	@FXML
	private HBox destroyer;

	@FXML
	private Label label1;

	@FXML
	private Label label2;

	@FXML
	private Button resetBoardButton;

	public Button continueButton;

	private HBox selectedShip = null;
	private MouseButton mouseButton = null;

	// private double xOffset = 0;
	// private double yOffset = 0; //staring pos forn window drags

	private boolean running = false;
	private Board computerBoard, playerBoard;

	private int shipsToPlace = 5;

	private boolean computerTurn = false;

	private Random random = new Random();

	private ArrayList<Integer> noMultiples = new ArrayList<Integer>();

	int shipSize = 0;

	Stage gameStage;
	BoxBlur blur = new BoxBlur();
	//MediaPlayer mediaPlayer;
	
	Image miss = new Image(getClass().getClassLoader().getResource("miss.png").toString());
	Image hit = new Image(getClass().getClassLoader().getResource("hit.png").toString());
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set up background music
		String battleshipSquaresTheme = "battleshipSquaresTheme.wav";

		Media sound = new Media(Paths.get(battleshipSquaresTheme).toUri().toString());

		windowRoot.setStyle("-fx-background-color: black;");
		windowRoot.getStylesheets().add("GamePlayScene.css");

		setButtonBar();
		setTopArea();

		playerBoard = new Board(false, event -> {
			if (running)
				return;

			ShipPart shipPart = (ShipPart) event.getSource();
			// shipPart.setFill(Color.PINK);
			if (!running) {
				playerBoard.putShowInPlace(new Ship(shipSize, mouseButton), shipPart.x, shipPart.y);

			}
			shipPart.setOnDragExited(new EventHandler<DragEvent>() {
				public void handle(DragEvent event) {

					if (!running) {

						playerBoard.removeShipFromPlace(new Ship(shipSize, mouseButton), shipPart.x, shipPart.y);

						event.consume();
					}
				}
			});

			if (selectedShip != null) {
				switch (selectedShip.getId()) {
				case "carrier":
					shipSize = 5;
					break;
				case "battleship":
					shipSize = 4;
					break;
				case "cruiser":
					shipSize = 3;
					break;
				case "submarine":
					shipSize = 2;
					break;
				case "destroyer":
					shipSize = 1;
					break;
				default:
					break;
				}

				System.out.println(selectedShip.getId());
				shipPart.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent e) {
						if (!noMultiples.contains(shipSize)) {
							noMultiples.add(shipSize);
							if (!running & playerBoard.putShipInPlace(new Ship(shipSize, e.getButton()), shipPart.x,
									shipPart.y)) {
								selectedShip = null;
								shipSize = 0;
								System.out.println("&&&&&&&");
								System.out.println(shipsToPlace);
								if (--shipsToPlace == 0) {
									startGame();

								}
							}
						}
					}
				});
			}
			// }

		});

		computerBoard = new Board(true, event -> {
			if (!running)
				return;

			ShipPart shipPart = (ShipPart) event.getSource();
			if (shipPart.shipPartWasHit)
				return;
//            if (!computerTurn){
//				Rectangle r = new Rectangle(34, 35);
//				Image img = new Image(getClass().getClassLoader().getResource("miss.png").toString());
//				r.setFill(new ImagePattern(img));
//				playerBoard.getStackPart(x, y).getChildren().add(r);
//			}
			computerTurn = !shipPart.hitShipPart();
			StackPane s = (StackPane) shipPart.getParent();
			
			if (shipPart.attacked == false) {
				shipPart.attacked = true;
				Rectangle r = new Rectangle(34, 35);

				if (computerTurn) {
					r.setFill(new ImagePattern(miss));
				} else {
					r.setFill(new ImagePattern(hit));
				}
				s.getChildren().add(r);
			}
			if (computerBoard.ships == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("YOU WIN");
				alert.showAndWait();

				System.out.println("YOU WIN");
				Popup pop = new Popup();
				pop.getContent().add(new Label("You win"));
				pop.show(Main.rootStage);
			}

			if (computerTurn)
				computerMove();
		});

		HBox boards = new HBox(65, computerBoard, playerBoard);

		rotateNode(computerBoard, 30, 0, Rotate.Z_AXIS);
		rotateNode(playerBoard, 30, 0, Rotate.Z_AXIS);

		rotateNode(boards, 70, 0, Rotate.X_AXIS);

		HBox.setMargin(computerBoard, new Insets(0, 0, 0, 66));

		windowRoot.setCenter(boards);

	}

	private void setTopArea() {
		opponentImage.setImage(new Image(getClass().getClassLoader().getResource("blueEnemies (1).png").toString()));

		playerImage.setImage(new Image(getClass().getClassLoader().getResource(Main.profileImg).toString()));
		int en = random.nextInt(5 - 1) + 1;
		opponentImage.setImage(
				new Image(getClass().getClassLoader().getResource("blueEnemies (" + en + ").png").toString()));

		if (Main.colorTheme != null) {
			switch (Main.colorTheme) {
			case RED:
				opponentImage.setImage(
						new Image(getClass().getClassLoader().getResource("redEnemies (" + en + ").png").toString()));
				label1.setStyle("-fx-background-color:red");
				label2.setStyle("-fx-background-color:red");
				colorRectangles(opponentArmada, Color.RED);
				colorRectangles(PlayerArmada, Color.RED);
				break;
			case BLUE:
				opponentImage.setImage(
						new Image(getClass().getClassLoader().getResource("blueEnemies (" + en + ").png").toString()));
				label1.setStyle("-fx-background-color:dodgerblue");
				label2.setStyle("-fx-background-color:dodgerblue");
				colorRectangles(opponentArmada, Color.DODGERBLUE);
				colorRectangles(PlayerArmada, Color.DODGERBLUE);
				break;
			case GREEN:
				opponentImage.setImage(
						new Image(getClass().getClassLoader().getResource("greenEnemies (" + en + ").png").toString()));
				label1.setStyle("-fx-background-color :rgb(0,255,0) ");
				label2.setStyle("-fx-background-color :rgb(0,255,0) ");
				colorRectangles(opponentArmada, Color.rgb(0, 255, 0));
				colorRectangles(PlayerArmada, Color.rgb(0, 255, 0));

				break;
			}

		}
		playerImage.setOpacity(0.6);
		opponentImage.setOpacity(0.6);

		// label1.setEffect(blur);
		// label2.setEffect(blur);

	}

	void colorRectangles(VBox vbox, Color color) {
		for (Node hbox : vbox.getChildren()) {

			for (Node rectangle : ((HBox) hbox).getChildren()) {
				((Shape) rectangle).setFill(color);
			}
		}
	}

	@FXML
	void handleShipDrag(MouseEvent event) {
		if (running) {
			selectedShip = null;
			return;
		}
		mouseButton = event.getButton();

		System.out.println("carrier clicked");
		selectedShip = (HBox) event.getSource();
		Dragboard db = carrier.startDragAndDrop(TransferMode.COPY);
		db.setDragView(createSnapshot(selectedShip), event.getX(), event.getY());

		ClipboardContent content = new ClipboardContent();
		content.putString("");
		db.setContent(content);
		event.consume();

	}

	@FXML
	void handleresetBoardButtonClick(ActionEvent event) {
		// Main.mediaPlayer.setMute(true);

		System.out.println("resetBoard Button Clicked");
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../GamePlayScene.fxml"));
			Scene scene = new Scene(root);
			Main.rootStage.hide();
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
			Main.rootStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private WritableImage createSnapshot(HBox node) {
		SnapshotParameters snapshotParams = new SnapshotParameters();
		snapshotParams.setFill(Color.TRANSPARENT);
		WritableImage image = node.snapshot(snapshotParams, null);

		return image;
	}

	@FXML
	void handleExitButtonClick(ActionEvent event) {
		System.out.println("ThemeExit Button Clicked");
		System.exit(0);
	}

	@FXML
	void handleHomeButtonClick(ActionEvent event) {
		System.out.println("Theme  HomeButton Clicked");
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("../FirstScene.fxml"));
			Scene scene = new Scene(root);
			Main.rootStage.setScene(scene);
			Main.rootStage.setFullScreen(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void handlesoundButtonClick(ActionEvent event) {
		if (Main.mediaPlayer.isMute()) {
			Main.mediaPlayer.setMute(false);
			return;
		}
	Main.mediaPlayer.setMute(true);

	}

	public void setButtonBar() {
		System.out.println("Theme  GreenTheme Clicked");
		buttonBarHBox.setStyle("-fx-background-color:dodgerblue");
		if (Main.colorTheme != null) {
			switch (Main.colorTheme) {
			case RED:
				buttonBarHBox.setStyle("-fx-background-color:red");

				break;
			case BLUE:
				buttonBarHBox.setStyle("-fx-background-color:dodgerblue");

				break;
			case GREEN:
				buttonBarHBox.setStyle("-fx-background-color :rgb(0,255,0) ");
				break;
			}
		}
	}

	private RotateTransition rotateNode(Node node, int degree, int cycle, Point3D axis) {
		RotateTransition rotator = new RotateTransition(Duration.millis(10000), node);
		rotator.setAxis(axis);
		// rotator.setAxis(Rotate.Z_AXIS);

		rotator.setFromAngle(0);
		rotator.setToAngle(degree);
		rotator.setInterpolator(Interpolator.LINEAR);

		rotator.setCycleCount(cycle);
		rotator.play();

		return rotator;

	}

	private void computerMove() {
		// choose stacked play or not
		while (computerTurn) { // keep goind or not
			int x = random.nextInt(11 - 1) + 1;
			int y = random.nextInt(11 - 1) + 1;

			ShipPart shipPart = playerBoard.getShipPart(x, y);// get the shipPart at that location
			if (shipPart.shipPartWasHit) /// loop jump next if the sell is unavalable
				continue;

			computerTurn = shipPart.hitShipPart(); // set the shipPart to was shot true
			if (shipPart.attacked == false) {
				Rectangle r = new Rectangle(34, 35);

				shipPart.attacked = true;
				if (!computerTurn) {
					
					r.setFill(new ImagePattern(miss));
				} else {
					r.setFill(new ImagePattern(hit));

				}

				playerBoard.getStackPart(x, y).getChildren().add(r);
			}
			if (playerBoard.ships == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setContentText("YOU LOSE");
				alert.showAndWait();
				System.out.println("YOU LOSE");
				Popup pop = new Popup();
				pop.getContent().add(new Label("You Lose"));
				pop.show(Main.rootStage);

			}
		}
	}

	private void startGame() {
		int computerShips = 5; // set max ship type of enemie

		while (computerShips > 0) {// ship type determined by curr value of enemieShips
			int x = random.nextInt(11 - 1) + 1;
			int y = random.nextInt(11 - 1) + 1;

			MouseButton[] points = new MouseButton[] { MouseButton.PRIMARY, MouseButton.SECONDARY };
			int ran = random.nextInt(2);
			// place enemy ships
			if (computerBoard.putShipInPlace(new Ship(computerShips, points[ran]), x, y)) {
				computerShips--;
			}
		}

		running = true;
	}

}
