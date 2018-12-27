import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class GroundBlock extends Entity {
	// This is the Groundblock entity, its a block the player can collide with and
	// makes up the maps

	// Flip counter and flipanimate are only to animate the fire, if flipanimate is
	// true, the sprite will flip every 20 cycles
	int flipCounter = 0;
	boolean flipAnimate = false;

	// Constructor for the block, you get the position, its sprite location in the
	// spritesheet and if its solid
	public GroundBlock(int x, int y, int subX, int subY, boolean solid) {
		super(x, y);
		// Getting sprite as specified;
		sprite = spriteSheet.getSubimage(subX * 8, subY * 8, 8, 8);
		// Setting preference for solid-ness
		this.solid = solid;
		// Setting the boundary
		bounds = new Rectangle(x, y, 8, 8);
		// If the spite selected is of a fire, then we want to flip animate it
		if (subX == 2 || subX == 3) {
			if (subY == 1 || subY == 2) {
				flipAnimate = true;
			}
		}
	}

	// The update function for this entity
	public void update(ArrayList<Entity> entities) {
		// Recalculate the bounds in case of camera movement
		bounds = new Rectangle(x, y, 8, 8);
		// If flip animate is a thing for this entity, then flip it every time flip
		// counter hits 20 and set it to 0, otherwise add 1 to flip counter
		if (flipAnimate) {
			flipCounter++;
			if (flipCounter >= 20) {
				flipCounter = 0;
			}
		}
	}

	// Draw the sprites
	public void draw(Graphics g) {
		// If flipcounter is below 10, draw it normally, otherwise, draw it flipped
		if (flipCounter < 10) {
			g.drawImage(sprite, x, y, 8, 8, null);
		} else {
			g.drawImage(sprite, x + 8, y, -8, 8, null);
		}
	}
}
