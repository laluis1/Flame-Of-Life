import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener {

	// State that the game is in (menu, levels, ending) {its a fsm for what the game
	// logic should be}
	State currentState;

	// Graphics and Image for double buffering
	Image dbImage;
	Graphics g;

	// Boolean which sets the game loop
	boolean running = true;

	// Window scale to mulitply by (cause game is actually running in 160px by
	// 144px)
	int scale = 6;

	// Setup for game window etc
	public Main() {
		// JFrame window parameters
		setSize(160 * scale, 144 * scale);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setTitle("MINI JAM 4");
		setBackground(new Color(197, 226, 161));

		// Setting the state for the game to start on
		currentState = new MenuState();

		// Game loop
		while (running) {
			// Delay to ensure game runs at around 30 fps like some flash games
			try {
				Thread.sleep(33);
			} catch (Exception e) {
			}

			// Adding key listener to allow us to give inputs
			this.addKeyListener(this);

			// Update function for all game related stuff, other things like timer and
			// drawing go here to keep organized
			update();

			// Redraw stuff on JFrame
			repaint();
		}
	}

	// ENTRY POINT FOR CODE:
	public static void main(String args[]) {
		// Creating a new Main class, which creates a JFrame with all of the code for
		// the game
		new Main();
	}

	// Update function which has game related stuff
	public void update() {
		// update current state
		currentState.update();

		// Transition function for game state FSM, if you are in the game state and you
		// request to change the state, it means you win and go to end state, else...
		// if the player fell (when he gets hit by enemy, is moved under display so dont
		// have to add extra logic) restart the game
		if (currentState.type == "Game") {
			if (currentState.change) {
				currentState = new EndState();
			}
		}
		if (currentState.p.y > 144) {
			currentState = new MenuState();
		}
	}

	// JFrame paint function: where the stuffs are drawn
	public void paint(Graphics g2) {
		// Creating Image I will paint on to prevent the window from flickering
		dbImage = createImage(160, 144);

		// Getting the graphics from that image to draw with
		g = dbImage.getGraphics();

		// Clearing the image
		g.clearRect(0, 0, getWidth(), getHeight());

		// This makes sure that there actually is a state to draw from, and if there is,
		// draw what it wants you to draw
		// (basically, once this class has been written, I can never touch it again
		// because all of the logic will be done on the game states
		if (currentState != null) {
			currentState.draw(g);
		}

		// We draw everything onto that image to prevent flickering, now we draw what is
		// already prepared on the image to the display!
		g2.drawImage(dbImage, 0, 0, 160 * scale, 144 * scale, null);
	}

	// KeyListener keypressed function for when a key is pressed
	public void keyPressed(KeyEvent e) {
		// if the game is in the state "End", that state is "press any key to continue"
		// and if you do, it starts menu state
		if (currentState.type == "End") {
			currentState = new MenuState();
		}
		// if the game is in the state "Main", that state is "press any key to continue"
		// and if you do, it starts game again
		if (currentState.type == "Menu") {
			currentState = new GameState();
		}

		// Tells the current state a key has been pressed and data on what that key is
		currentState.keyPressed(e);
	}

	// KeyListener keyreleased function for when a key is released
	public void keyReleased(KeyEvent e) {
		// Tells the current state a key has been released and data on what that key is
		currentState.keyReleased(e);
	}

	// useless and I hate it >:0
	// {nah, jk <3 :3:3}
	public void keyTyped(KeyEvent e) {
	}
}
