package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import application.ShipPart;

public class Board extends Parent {
	private VBox board = new VBox(); // each board
	private boolean opponent = false; // to tell if it player or oponent
	public int ships = 5; // num ships per board

	public Board(boolean opponent, EventHandler/* <? super MouseEvent> */ handler) { // handler used for mouse clicks on
		// cells
		this.opponent = opponent;
		for (int row = 0; row < 11; row++) {
			HBox rowColums = new HBox();
			for (int col = 0; col < 11; col++) {
				String coords = new String("" + row + "," + col); // m this
				StackPane stack = new StackPane(); // m this
				Text t = new Text("");// m this
				ShipPart c = new ShipPart(row, col, this);// m this

				if (col != 0 && row != 0) {

					c.setOnMouseClicked(handler); // when the shipPart is clicked
					c.setOnDragEntered(handler);
					rowColums.getChildren().add(c);

				} else {

					switch (coords) {
					case "0,0":

						c.setFill(Color.TRANSPARENT);
						c.setStroke(Color.TRANSPARENT);
						break;
					case "1,0":
						t = new Text("A");
						t.resize(10, 10);

						break;
					case "2,0":
						t = new Text("B");
						t.resize(10, 10);

						break;
					case "3,0":
						t = new Text("C");
						t.resize(10, 10);

						break;
					case "4,0":
						t = new Text("D");
						t.resize(10, 10);

						break;
					case "5,0":
						t = new Text("E");
						t.resize(10, 10);

						break;
					case "6,0":
						t = new Text("F");
						t.resize(10, 10);

						break;
					case "7,0":
						t = new Text("G");
						t.resize(10, 10);

						break;
					case "8,0":
						t = new Text("H");

						break;
					case "9,0":
						t = new Text("I");

						break;
					case "10,0":
						t = new Text("J");

						break;
					case "0,1":
						t = new Text("1");
						t.resize(10, 10);

						break;
					case "0,2":
						t = new Text("2");
						t.resize(10, 10);

						break;
					case "0,3":
						t = new Text("3");
						t.resize(10, 10);

						break;
					case "0,4":
						t = new Text("4");
						t.resize(10, 10);

						break;
					case "0,5":
						t = new Text("5");
						t.resize(10, 10);

						break;
					case "0,6":
						t = new Text("6");
						t.resize(10, 10);

						break;
					case "0,7":
						t = new Text("7");
						t.resize(10, 10);

						break;
					case "0,8":
						t = new Text("8");
						t.resize(10, 10);

						break;
					case "0,9":
						t = new Text("9");

						break;

					case "0,10":
						t = new Text("10");

						break;

					default:
						// System.out.println(row +","+col);

						break;

					}
					t.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
					t.setFill(Color.AQUA);
					t.setStroke(Color.GREY);
					c.setFill(Color.BLACK);

					stack.getChildren().addAll(c, t);
					rowColums.getChildren().add(stack);
				}

			}

			board.getChildren().add(rowColums);
		}

		getChildren().add(board);
	}

	public boolean putShipInPlace(Ship ship, int x, int y/* ,boolean set */) {
		if (validPosition(ship, x, y)) {
			int length = ship.shipTypeByLength;

			if (ship.orientation == MouseButton.PRIMARY) {
				for (int i = x; i < x + length; i++) {
					ShipPart shipPart = getShipPart(i, y);
					shipPart.ship = ship;
					// shipPart.set = set;
					if (!opponent) {
						shipPart.setFill(Color.DIMGREY);
						shipPart.setStroke(Color.DIMGREY);
					}
				}
			} else if (ship.orientation == MouseButton.SECONDARY) {
				for (int i = y; i < y + length; i++) {
					ShipPart shipPart = getShipPart(x, i);
					shipPart.ship = ship;
					// shipPart.set = set;

					if (!opponent) {
						shipPart.setFill(Color.GREY);
						shipPart.setStroke(Color.GREY);
					}
				}

			}

			return true;
		}

		return false;
	}

	public boolean putShowInPlace(Ship ship, int x, int y/* ,boolean set */) {
		if (validPosition(ship, x, y)) {
			int length = ship.shipTypeByLength;

			if (ship.orientation == MouseButton.PRIMARY) {
				for (int i = x; i < x + length; i++) {
					ShipPart shipPart = getShipPart(i, y);
					// shipPart.ship = ship;
					// shipPart.set = set;
					if (!opponent) {
						shipPart.setFill(Color.DIMGREY);
						shipPart.setStroke(Color.DIMGREY);
					}
				}
			} else if (ship.orientation == MouseButton.SECONDARY) {
				for (int i = y; i < y + length; i++) {
					ShipPart shipPart = getShipPart(x, i);
					// shipPart.ship = ship;
					// shipPart.set = set;

					if (!opponent) {
						shipPart.setFill(Color.GREY);
						shipPart.setStroke(Color.GREY);
					}
				}

			}

			return true;
		}

		return false;
	}

	public boolean removeShipFromPlace(Ship ship, int x, int y) {
		if (validPosition(ship, x, y)) {
			int length = ship.shipTypeByLength;

			if (ship.orientation == MouseButton.PRIMARY) {
				for (int i = x; i < x + length; i++) {
					ShipPart shipPart = getShipPart(i, y);
					shipPart.ship = null;
					if (!opponent && shipPart.ship == null) {
						// shipPart.set = false;

						shipPart.setFill(shipPart.originalFill);
						shipPart.setStroke(shipPart.originalStroke);
					}
				}
			} else if (ship.orientation == MouseButton.SECONDARY) {
				for (int i = y; i < y + length; i++) {
					ShipPart shipPart = getShipPart(x, i);
					shipPart.ship = null;

					if (!opponent && shipPart.ship == null) {
						// shipPart.set = false;

						shipPart.setFill(shipPart.originalFill);
						shipPart.setStroke(shipPart.originalStroke);
					}
				}

			}

			return true;
		}

		return false;
	}

	public ShipPart getShipPart(int x, int y) {
		// System.out.println(""+y+","+x);

		return (ShipPart) ((HBox) board.getChildren().get(x)).getChildren().get(y);
	}

	private ShipPart[] locationsAroundShip(int x, int y) {
		Point2D[] points = new Point2D[] { new Point2D(x - 1, y), new Point2D(x + 1, y), new Point2D(x, y - 1),
				new Point2D(x, y + 1) };

		List<ShipPart> locations = new ArrayList<ShipPart>();

		for (Point2D coords : points) {
			if (validCoordinate(coords.getX(), coords.getY())) {
				locations.add(getShipPart((int) coords.getX(), (int) coords.getY()));
			}
		}

		return locations.toArray(new ShipPart[0]);
	}

	private boolean validPosition(Ship ship, int x, int y) {
		int length = ship.shipTypeByLength;

		if (ship.orientation == MouseButton.PRIMARY) {
			for (int i = x; i < x + length; i++) {
				if (!validCoordinate(i, y))
					return false;

				ShipPart shipPart = getShipPart(i, y);
				if (shipPart.ship != null)
					return false;

				for (ShipPart neighbor : locationsAroundShip(i, y)) {
					if (!validCoordinate(i, y))
						return false;

					if (neighbor.ship != null)
						return false;
				}
			}
		} else if (ship.orientation == MouseButton.SECONDARY) {// if ship is vertical
			for (int i = y; i < y + length; i++) {
				if (!validCoordinate(x, i))
					return false;

				ShipPart shipPart = getShipPart(x, i);
				if (shipPart.ship != null)
					return false;

				for (ShipPart neighbor : locationsAroundShip(x, i)) {
					if (!validCoordinate(x, i))
						return false;

					if (neighbor.ship != null)
						return false;
				}
			}
		}

		return true;
	}

	private boolean validCoordinate(double x, double y) {// ensure points match with board deminsions
		return x > 0 && x < 11 && y > 0 && y < 11;
	}

}