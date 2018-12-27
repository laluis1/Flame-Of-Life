import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class State {
	/*
	 * This is a general class for any state in the game The main point of this is
	 * to keep the code modular so I can add things easily and not stress Think of
	 * it as a stage, and it gives me functions I can use to manipulate the actors
	 * on said stage.
	 */

	// Every state has a player, even if it doesn't matter, just because of how
	// small the game is
	Player p = new Player(-10000, -1000);

	// Every state has entities, even if it doesn't matter also
	ArrayList<Entity> entities = new ArrayList<Entity>();

	// Images for the text the game will have (could have made the image different
	// to save on space, but...)
	BufferedImage letters;

	// The name of the state for the state machine to identify it and apply the
	// transition functions correctly
	String type = "";

	// If the state itself has any reason to request a state change to the Main
	// class
	boolean change = false;

	// Here we put the code we want to run every cycle
	public void update() {

	}

	// Here we put the code to draw things every frame
	public void draw(Graphics g) {

	}

	// This is the stairwell of knowledge, wait, no, its the key press function
	public void keyPressed(KeyEvent e) {

	}

	// This is the key release function for states to use
	public void keyReleased(KeyEvent e) {

	}
}
