package application;

import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class for a ship
 */
public class Ship extends Parent {
	/**ship length */
    public int shipTypeByLength; //length ship
    public MouseButton orientation;

    private int life;

    /**
     * 
     * @param type ship length
     * @param vertical  ship orientation: true == vertical | false == horizontal
     */
    public Ship(int type, MouseButton mouseButton) {
    	
        this.shipTypeByLength = type;
        this.orientation = mouseButton;
        life = type;
       
    }

    public void attack() {
        life--;
    }

    public boolean isAlive() {
        return life > 0;
    }
}