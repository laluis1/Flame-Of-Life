import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;

public class MenuState extends State {

	// This state tells the game the stage for the Main Menu

	// Constructor
	public MenuState() {
		// There is a delay here. Documenting this, I forget why I added it, it was
		// probably for debug purposes, however, it doesnt bother me, so im going to
		// leave it here
		// Hey, its not a product, its a makeshift game
		try {
			Thread.sleep(200);
		} catch (Exception e) {
		}
		
		// Setting the identity of the State
		type = "Menu";

		// Here we read the "Letters.png" spritesheet
		try {
			letters = ImageIO.read(new File("Letters.png"));
		} catch (Exception e) {
		}
		
		// Of course this is dumb, but so was using arraylists, it just places all of
		// the temple blocks etc into pre-programmed locations
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
	}


	// Game State update function
	public void update() {
		// Update all of the entities and give them lists of other entities on stage
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update(entities);
		}
	}

	public void draw(Graphics g) {
		// Load main menu text
		g.drawImage(letters.getSubimage(0, 0, 48, 8), 56, 48, null);
		g.drawImage(letters.getSubimage(0, 8, 16, 8), 48, 60, null);
		g.drawImage(letters.getSubimage(16, 8, 32, 8), 72, 60, null);

		// Draw all of the entities
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(g);
		}
	}

}
