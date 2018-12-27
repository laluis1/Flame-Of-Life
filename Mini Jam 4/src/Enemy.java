import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Enemy extends Entity {
	// This is the enemy state, its purpose is to walk around and damage the player

	// sight 1 and sight 2 act as sensors to prevent the enemy from falling off of
	// the stage
	Rectangle sight2, sight1;
	
	//direction the enemy is moving in, (x positive or x negative)
	int direction = 1;
	
	// if sight 1 sees something and if sight 2 sees something
	boolean s1 = true, s2 = true;

	// Enemy constructor
	public Enemy(int x, int y) {
		super(x, y);
		// Setting the type of this entity
		type = "Enemy";
		// Getting the enemy sprite
		sprite = spriteSheet.getSubimage(24, 24, 8, 8);
		// Getting the bounding box for the entitiy
		bounds = new Rectangle(x + 2, y, 4, 8);
	}

	// How long it takes for the enemy to move (slows down the movement a bit slower that one pixel at a time)
	int stepCounter = 0;

	// Updating the enemy
	public void update(ArrayList<Entity> entities) {
		sight1 = new Rectangle(x - 3, y + 8, 1, 4);
		sight2 = new Rectangle(x + 7, y + 8, 1, 4);
		bounds = new Rectangle(x + 2, y, 4, 8);
		if (stepCounter >= 5) {
			x += direction;
			stepCounter = 0;
		}
		stepCounter++;
		if (s1 != s2) {
			if (s1 == false) {
				direction = 1;
			} else if (s2 == false) {
				direction = -1;
			}
		}
		s1 = false;
		s2 = false;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).solid) {
				if (entities.get(i).bounds.intersects(sight1)) {
					s1 = true;
				}
				if (entities.get(i).bounds.intersects(sight2)) {
					s2 = true;
				}
			}
		}
	}

	// Drawing the enemy
	public void draw(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}
}
