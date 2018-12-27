import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Entity {
	// This is the entity super class for all of the entities in the game

	// Each entity has a collision box
	Rectangle bounds = new Rectangle();

	// If that entity is solid, the player can collide with it's bounds rectangle
	boolean solid = false;

	// The entity position as x and y
	int x, y;

	// What to draw the entity as
	Image sprite;

	// An identifier for entity behavior and to classify them inside other classes
	String type = "";

	// The sprite sheet which is loaded in every entity constructor, making it
	// easier for me to program the game as I just have to do
	// spriteSheet.getSubimage in each entity
	BufferedImage spriteSheet = null;

	// Entity constructor sets the position you want to load them in
	public Entity(int x, int y) {
		// Set the position of the entity to what you want
		this.x = x;
		this.y = y;

		// Load the sprite sheet for every entity child class
		try {
			spriteSheet = ImageIO.read(new File("Sprites.png"));
		} catch (Exception e) {
		}
	}

	// Update function to update the behavior of the entity
	public void update(ArrayList<Entity> entities) {

	}

	// Drawing the entity
	public void draw(Graphics g) {

	}

	// Key Pressed
	public void keyPressed(KeyEvent e) {

	}

	// Key Released
	public void keyReleased(KeyEvent e) {

	}
}
