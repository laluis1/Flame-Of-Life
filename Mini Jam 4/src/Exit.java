import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Exit extends Entity {
	// This is just an entity that sits in the game and when you touch it, it
	// transports you to the next levels

	public Exit(int x, int y) {
		super(x, y);
		// Getting the exit sprite from the sprite sheet
		sprite = spriteSheet.getSubimage(24, 0, 8, 8);
		// Setting the exit bounds
		bounds = new Rectangle(x, y - 3, 8, 14);
		// Giving it it's type so that the player can know when it collides with it
		type = "Exit";
	}

	// Animation counter to allow it to float
	float animationCounter = 0;

	// Where I wanna draw the exit relative to it's actual position
	int drawY = y;

	// Updating the exit (adding animation and recalculating boundaries as the
	// camera moves
	public void update(ArrayList<Entity> entities) {
		// Adding to the animation counter and positioning the exit so it looks like its
		// floating
		animationCounter += .15;
		drawY = y + (int) (Math.sin(animationCounter) * 3) - 3;
		// Recalculating the boundary for camera motion
		bounds = new Rectangle(x, y - 3, 8, 14);
	}

	// Drawing the exit
	public void draw(Graphics g) {
		g.drawImage(sprite, x, drawY, null);
	}
}
