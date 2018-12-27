import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Player extends Entity {

	/*
	 * This is the player controller class, it gives us a player to move around, but
	 * super basic
	 */

	// Which buttons the key press and release functions tell us are pressed right
	// now for other functions to use
	boolean upKey = false, downKey = false, leftKey = false, rightKey = false, xKey = false, zKey = false,
			onGround = false;

	// The speed to fine tune the player's movement
	int spd = 2;

	// Player constructor, when you spawn a player, you pick its initial x and y
	// position
	public Player(int x, int y) {
		// Just supering it to the entity class to actually place it in the x and y
		// position specified
		super(x, y);
		// Load the specific player sprite from the game's sprite sheet
		sprite = spriteSheet.getSubimage(0, 16, 8, 8);
		// Define the bounding box for the player
		bounds = new Rectangle(x + 2, y, 4, 8);
	}

	// The player's x and y velocity
	float dx = 0, dy = 0;

	// Update function for the player, it gives also an array of objects in the
	// current stage
	public void update(ArrayList<Entity> entities) {
		// Here, the player logic checks through all of the entities in the scene, and
		// for the enemies,
		// If they are touching the player, the player moves to a y position of 1000.
		// Remember, in the game state logic, if the player Y is greater than 144, it
		// assumes the player died,
		// So this is a cheap way to kill the player when it's hit by an enemy
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).type == "Enemy") {
				if (bounds.intersects(entities.get(i).bounds)) {
					y = 1000;
				}
			}
		}

		// Here is the movement logic for the player, (if a key is pressed, it
		// accelerates in that direction)
		if (leftKey) {
			if (dx > -spd) {
				dx -= .4;
			}
		}
		if (rightKey) {
			if (dx < spd) {
				dx += .4;
			}
		}
		// If the player is on the ground and z is pressed, set velocity to push player
		// up in the air (jumping)
		if (zKey && onGround) {
			dy = -5;
		}

		// This is friction to make sure the player will stop moving when you stop
		// pressing the key, but also makes it feel a bit more smooth
		if (dx > 0) {
			dx -= .2;
		}
		if (dx < 0) {
			dx += .2;
		}

		// Gravity acceleration if player hasn't hit maximum y velocity
		if (dy < 5) {
			dy += .4;
		}

		// Here we implement the player's movement and collision physics

		// Move player in x direction by dx (the velocity)
		x += (int) Math.round(dx);
		// Then update the collision box
		bounds = new Rectangle(x + 2, y, 4, 8);
		// Check to see if the player is colliding with any solid surfaces, and if it
		// is, move the player back to the edge of that solid surface
		// (not the best physics, but it works enough for our cases) (might be better if
		// the collision boxes were calculated beforehand as opposed to placing them
		// with each solid object)
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).solid) {
				if (bounds.intersects(entities.get(i).bounds)) {
					bounds = new Rectangle(x + 2, y, 4, 8);
					x -= (int) Math.round(dx);
					if (x > entities.get(i).x + 8) {
						x = entities.get(i).x - 2;
					}
					if (x + 8 < entities.get(i).x) {
						x = entities.get(i).x + 2;
					}
					dx = 0;
					break;
				}
			}
		}

		// Move player in y direction by dy (the velocity)
		y += (int) Math.round(dy);
		// Then update the collision box
		bounds = new Rectangle(x + 2, y, 4, 8);
		// Same old thing from before, but also a "onGround" variable is computed that
		// tells us if the player is on top of a surface
		onGround = false;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).solid) {
				if (bounds.intersects(entities.get(i).bounds)) {
					bounds = new Rectangle(x + 2, y, 4, 8);
					if (y > entities.get(i).y) {
						y = entities.get(i).y + 8;
						dy = 0;
					}
					if (y < entities.get(i).y + 8) {
						y = entities.get(i).y - 8;
						onGround = true;
					}
					break;
				}
			}
		}
	}

	// Draw the player
	public void draw(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

	// If the key [key] is pressed, set boolean [key]key to true
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upKey = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downKey = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftKey = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightKey = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			xKey = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			zKey = true;
		}
	}

	// If the key [key] is released, set boolean [key]key to false
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			xKey = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			zKey = false;
		}
	}
}
