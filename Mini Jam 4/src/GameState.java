import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GameState extends State {

	// A variable that helps set the boundaries of how much the camera can move with
	// the player when the player moves
	int cameradamp = 10;

	// Which level to load
	int level = 1;

	// How long the level titles will last in each level
	int letterCounter = 0;

	// Weather the player is in the title part of the level or the game part of the
	// level
	boolean temple = true;

	// GameState constructor
	public GameState() {
		// There is a delay here. Documenting this, I forget why I added it, it was
		// probably for debug purposes, however, it doesnt bother me, so im going to
		// leave it here
		// Hey, its not a product, its a makeshift game
		try {
			Thread.sleep(200);
		} catch (Exception e) {
		}

		// Setting the identity of the State
		type = "Game";

		// Here we read the "Letters.png" spritesheet
		try {
			letters = ImageIO.read(new File("Letters.png"));
		} catch (Exception e) {
		}

		// Create a player and position it at 0, 0
		p = new Player(0, 0);

		// Load the first temple (first part of the level with the title)
		loadTemple();
	}

	// This function is in charge of loading a temple in the game.
	public void loadTemple() {
		// Clear any entity that was in the array to begin with
		entities.clear();
		// Add the player to the starting location
		p = new Player(56, 120);
		// Set how long the title for the level should last (in frames) (to get in
		// miliseconds, its approx. 60*30 = 1800)
		letterCounter = 60;

		// Of course this is dumb, but so was using arraylists, it just places all of
		// the temple blocks etc into pre-programmed locations

		// LEVEL 1 ######
		if (level == 1) {
			for (int i = 2; i < 17; i++) {
				entities.add(new GroundBlock(32, i * 8, 0, 1, false));
				entities.add(new GroundBlock(160 - 48, i * 8, 0, 1, false));
				entities.add(new GroundBlock(16, i * 8, 0, 1, false));
				entities.add(new GroundBlock(160 - 32, i * 8, 0, 1, false));
			}
			for (int i = 0; i < 21; i++) {
				entities.add(new GroundBlock(i * 8 - 8, 136, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 128, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 0, 1, 1, true));
			}
			for (int i = 0; i < 17; i++) {
				entities.add(new GroundBlock(i * 8 + 8, 8, 1, 1, true));
			}
			entities.add(new GroundBlock(72, 120, 2, 1, false));
			entities.add(new Exit(72, 106));
		}

		// LEVEL 2 ######
		if (level == 2) {
			for (int i = 2; i < 17; i++) {
				entities.add(new GroundBlock(32, i * 8, 4, 1, false));
				entities.add(new GroundBlock(160 - 48, i * 8, 4, 1, false));
				entities.add(new GroundBlock(16, i * 8, 4, 1, false));
				entities.add(new GroundBlock(160 - 32, i * 8, 4, 1, false));
			}
			for (int i = 0; i < 21; i++) {
				entities.add(new GroundBlock(i * 8 - 8, 136, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 128, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 0, 1, 1, true));
			}
			for (int i = 0; i < 17; i++) {
				entities.add(new GroundBlock(i * 8 + 8, 8, 1, 1, true));
			}
			entities.add(new GroundBlock(0, 128, 1, 0, false));
			entities.add(new GroundBlock(8, 128, 2, 0, false));
			entities.add(new GroundBlock(152, 128, 1, 0, false));
			entities.add(new GroundBlock(136, 128, 1, 0, false));
			entities.add(new GroundBlock(144, 128, 2, 0, false));
			entities.add(new GroundBlock(72, 120, 3, 1, false));
			entities.add(new GroundBlock(48, 120, 1, 0, false));
			entities.add(new GroundBlock(64, 120, 2, 0, false));
			entities.add(new GroundBlock(80, 120, 2, 0, false));
			entities.add(new GroundBlock(88, 120, 1, 0, false));
			entities.add(new GroundBlock(96, 120, 1, 0, false));
			entities.add(new Exit(72, 106));
		}

		// LEVEL 3 ######
		if (level == 3) {
			for (int i = 2; i < 17; i++) {
				entities.add(new GroundBlock(32, i * 8, 4, 1, false));
				entities.add(new GroundBlock(160 - 48, i * 8, 4, 1, false));
				entities.add(new GroundBlock(16, i * 8, 4, 1, false));
				entities.add(new GroundBlock(160 - 32, i * 8, 4, 1, false));
				if (i % 4 == 2) {
					entities.add(new GroundBlock(160 - 32 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(16 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(160 - 32 - 8, i * 8, 5, 2, false));
					entities.add(new GroundBlock(16 - 8, i * 8, 5, 2, false));
				}
				if (i % 4 == 0) {
					entities.add(new GroundBlock(160 - 48 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(32 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(160 - 48 - 8, i * 8, 5, 2, false));
					entities.add(new GroundBlock(32 - 8, i * 8, 5, 2, false));
				}
			}
			for (int i = 0; i < 21; i++) {
				entities.add(new GroundBlock(i * 8 - 8, 136, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 128, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 0, 1, 1, true));
			}
			for (int i = 0; i < 17; i++) {
				entities.add(new GroundBlock(i * 8 + 8, 8, 1, 1, true));
			}
			entities.add(new GroundBlock(72, 120, 3, 2, false));
			entities.add(new GroundBlock(0, 128, 1, 0, false));
			entities.add(new GroundBlock(8, 128, 4, 0, false));
			entities.add(new GroundBlock(152, 128, 1, 0, false));
			entities.add(new GroundBlock(136, 128, 5, 0, false));
			entities.add(new GroundBlock(144, 128, 4, 0, false));
			entities.add(new GroundBlock(72, 120, 3, 1, false));
			entities.add(new GroundBlock(48, 120, 4, 0, false));
			entities.add(new GroundBlock(64, 120, 2, 0, false));
			entities.add(new GroundBlock(80, 120, 5, 0, false));
			entities.add(new GroundBlock(88, 120, 1, 0, false));
			entities.add(new GroundBlock(96, 120, 4, 0, false));
			entities.add(new Exit(72, 106));
		}

		// LEVEL 4 ######
		if (level == 4) {
			for (int i = 2; i < 17; i++) {
				entities.add(new GroundBlock(32, i * 8, 4, 2, false));
				entities.add(new GroundBlock(160 - 48, i * 8, 4, 2, false));
				entities.add(new GroundBlock(16, i * 8, 4, 2, false));
				entities.add(new GroundBlock(160 - 32, i * 8, 4, 2, false));
				if (i % 4 == 2) {
					entities.add(new GroundBlock(160 - 32 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(16 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(160 - 32 - 8, i * 8, 5, 2, false));
					entities.add(new GroundBlock(16 - 8, i * 8, 5, 2, false));
				}
				if (i % 4 == 0) {
					entities.add(new GroundBlock(160 - 48 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(32 + 8, i * 8, 5, 1, false));
					entities.add(new GroundBlock(160 - 48 - 8, i * 8, 5, 2, false));
					entities.add(new GroundBlock(32 - 8, i * 8, 5, 2, false));
				}
			}
			for (int i = 0; i < 21; i++) {
				entities.add(new GroundBlock(i * 8 - 8, 136, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 128, 1, 1, true));
			}
			for (int i = 0; i < 7; i++) {
				entities.add(new GroundBlock(i * 8 + 48, 0, 1, 1, true));
			}
			for (int i = 0; i < 17; i++) {
				entities.add(new GroundBlock(i * 8 + 8, 8, 1, 1, true));
			}
			entities.add(new GroundBlock(152, 128, 4, 0, false));
			entities.add(new GroundBlock(144, 128, 4, 0, false));
			entities.add(new GroundBlock(64, 120, 4, 0, false));
			entities.add(new GroundBlock(80, 120, 5, 0, false));
			entities.add(new GroundBlock(72, 120, 2, 2, false));
			entities.add(new Exit(72, 106));
		}
	}

	// Load the level file: "Level" + level + ".png"
	// (I made them as pngs, so I could edit them in gimp easily instead of having
	// to write my own level editor or learn one from the internet)
	public void loadLevel(String file) {
		// Again, clear anything previously on the map
		entities.clear();
		// Load the image as a buffered image, for an actual game, it would be better to
		// parse all of the data from an image if I were to use gimp as an editor
		// And then save it to a file where it will take up less space
		BufferedImage level = null;
		try {
			level = ImageIO.read(new File(file));
		} catch (Exception e) {
		}

		// Actually parse the file, pixel by pixel, moving from the top left to the
		// bottom right.
		/*
		 * Pixel Color Type Black Ground Block White Dead Bush Red Enemy Green Green
		 * Bush Blue Exit Yellow Green Tree Cyan Player Magenta Dead Tree Dark Red Coin
		 */
		for (int i = 0; i < level.getWidth(); i++) {
			for (int j = 0; j < level.getHeight(); j++) {
				Color pixel = new Color(level.getRGB(i, j));
				// BLACK PIXEL
				if (pixel.getRed() < 50 && pixel.getGreen() < 50 && pixel.getBlue() < 50) {
					entities.add(new GroundBlock(i * 8, j * 8, 0, 0, true));
				}
				// WHITE PIXEL
				if (pixel.getRed() > 200 && pixel.getGreen() > 200 && pixel.getBlue() > 200) {
					entities.add(new GroundBlock(i * 8, j * 8, 5, 0, false));
				}
				// RED PIXEL
				if (pixel.getRed() > 200 && pixel.getGreen() < 50 && pixel.getBlue() < 50) {
					entities.add(new Enemy(i * 8, j * 8));
				}
				// GREEN PIXEL
				if (pixel.getRed() < 50 && pixel.getGreen() > 200 && pixel.getBlue() < 50) {
					entities.add(new GroundBlock(i * 8, j * 8, 1, 0, false));
				}
				// BLUE PIXEL
				if (pixel.getRed() < 50 && pixel.getGreen() < 50 && pixel.getBlue() > 200) {
					entities.add(new Exit(i * 8, j * 8));
				}
				// YELLOW PIXEL
				if (pixel.getRed() > 200 && pixel.getGreen() > 200 && pixel.getBlue() < 50) {
					entities.add(new GroundBlock(i * 8, j * 8, 2, 0, false));
				}
				// CYAN PIXEL
				if (pixel.getRed() < 50 && pixel.getGreen() > 200 && pixel.getBlue() > 200) {
					p = new Player(i * 8, j * 8);
				}
				// MAGENTA PIXEL
				if (pixel.getRed() > 200 && pixel.getGreen() < 50 && pixel.getBlue() > 200) {
					entities.add(new GroundBlock(i * 8, j * 8, 4, 0, false));
				}
				// DARK RED PIXEL
				if (pixel.getRed() > 50 && pixel.getRed() < 200 && pixel.getGreen() < 50 && pixel.getBlue() < 50) {
					entities.add(new Coin(i * 8, j * 8));
				}
			}
		}
	}

	// Game State update function
	public void update() {
		// Count down the letter counter each frame, which tells us how long the season
		// names last on screen
		if (letterCounter > 0) {
			letterCounter--;
		}

		// Call the update function for the player and give it a list of the entities on
		// the stage
		p.update(entities);

		// Update all of the entities and give them lists of other entities on stage
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update(entities);
		}

		// Camera logic
		// If the player isnt in the temple, then we want to move the camera
		if (!temple) {
			// To get the effect of the camera moving, when the player is off of the center
			// of the screen, it pushes all of the entities on screen over
			for (int i = 0; i < entities.size(); i++) {
				entities.get(i).x += (78 - p.x) / cameradamp;
			}
			p.x += (78 - p.x) / cameradamp;
		}

		// If the player touches a coin, remove it
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).type == "Coin") {
				if (p.bounds.getBounds().intersects(entities.get(i).bounds.getBounds())) {
					entities.remove(i);
				}
			}
		}

		// If the player touches a exit, load the next level or temple
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).type == "Exit") {
				if (p.bounds.intersects(entities.get(i).bounds)) {
					if (!temple) {
						if (level == 4) {
							change = true;
						}
						level++;
						loadTemple();
						temple = true;
					} else {
						loadLevel("Level" + level + ".png");
						temple = false;
						letterCounter = 0;
					}
					// loadTemple();
				}
			}
		}
	}

	// Draw everything on screen
	public void draw(Graphics g) {
		// While the text is supposed to be on screen, display it based on which level
		// it should be and make it rise up slowly
		if (letterCounter > 0) {
			if (level == 1) {
				g.drawImage(letters.getSubimage(0, 16, 48, 8), 52, 48 + letterCounter / 10, null);
			}
			if (level == 2) {
				g.drawImage(letters.getSubimage(0, 24, 48, 8), 52, 48 + letterCounter / 10, null);
			}
			if (level == 3) {
				g.drawImage(letters.getSubimage(0, 32, 40, 8), 60, 48 + letterCounter / 10, null);
			}
			if (level == 4) {
				g.drawImage(letters.getSubimage(0, 40, 48, 8), 52, 48 + letterCounter / 10, null);
			}
		}

		// Draw all of the entities
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(g);
		}

		// Draw the player
		p.draw(g);
	}

	// Key pressed
	public void keyPressed(KeyEvent e) {
		// Send that event to the player
		p.keyPressed(e);
	}

	// Key released
	public void keyReleased(KeyEvent e) {
		// Send that event also
		p.keyReleased(e);
	}

}
