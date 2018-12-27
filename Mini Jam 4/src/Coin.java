import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Coin extends Entity {
	// This is just the coin entity, an entity the player can collide with to
	// collect

	public Coin(int x, int y) {
		super(x, y);
		// Getting the coin sprite from the spritesheet
		sprite = spriteSheet.getSubimage(32, 24, 8, 8);
		// Setting the coin bounds
		bounds = new Rectangle(x, y - 2, 8, 9);
		// Identifying the coin
		type = "Coin";
	}

	// Animation counter to allow it to float
	float animationCounter = 0;

	// Where I wanna draw the coin relative to it's actual position
	int drawY = y;

	// Updating the coin (adding animation and recalculating boundaries as the
	// camera moves
	public void update(ArrayList<Entity> entities) {
		// Adding to the animation counter and positioning the coin so it looks like its
		// floating
		animationCounter += .2;
		drawY = y + (int) (Math.sin(animationCounter) * 2) - 2;
		// Recalculating the boundary for camera motion
		bounds = new Rectangle(x, y - 2, 8, 9);
	}

	// Drawing the coin
	public void draw(Graphics g) {
		g.drawImage(sprite, x, drawY, null);
	}
}
