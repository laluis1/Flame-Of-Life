import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.imageio.ImageIO;

public class EndState extends State {
	// I forgot I put this here and it caught me. Ima leave it here cause it low-key
	// made me laugh what tired me says:
	// the end state is that, it predicts that soon, we will all die

	public EndState() {
		// There is a delay here. Documenting this, I forget why I added it, it was
		// probably for debug purposes, however, it doesnt bother me, so im going to
		// leave it here
		// Hey, its not a product, its a makeshift game
		try {
			Thread.sleep(200);
		} catch (Exception e) {
		}

		// setting the name of the state for the fsm of the game
		type = "End";

		// Read the spritesheet with the title words on it
		try {
			letters = ImageIO.read(new File("Letters.png"));
		} catch (Exception e) {
		}

		// These for loops place all of the pillars and ground for the win state
		// recreating the temple
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
	}

	public void update() {
		// updating the entities is probably not needed, but seems fitting
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update(entities);
		}
	}

	// The text that shows up in the end of the game:
	// You have passed the seasons of life only to have your flame extinguished
	public void draw(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).draw(g);
		}
		g.clearRect(40, 0, 72, 16);
		// drawing the text:
		g.drawImage(letters.getSubimage(0, 48, 24, 8), 44, 0, null);
		g.drawImage(letters.getSubimage(32, 48, 32, 8), 76, 0, null);
		g.drawImage(letters.getSubimage(0, 56, 48, 8), 44, 12, null);
		g.drawImage(letters.getSubimage(0, 64, 24, 8), 44, 24, null);
		g.drawImage(letters.getSubimage(24, 64, 56, 8), 44, 36, null);
		g.drawImage(letters.getSubimage(0, 8, 16, 8), 44, 48, null);
		g.drawImage(letters.getSubimage(16, 8, 32, 8), 44 + 24, 48, null);
		g.drawImage(letters.getSubimage(48, 72, 32, 8), 44, 60, null);
		g.drawImage(letters.getSubimage(16, 72, 32, 8), 84, 60, null);
		g.drawImage(letters.getSubimage(32, 48, 32, 8), 44, 72, null);
		g.drawImage(letters.getSubimage(0, 48, 32, 8), 44, 84, null);
		g.drawImage(letters.getSubimage(0, 0, 40, 8), 44, 96, null);
		g.drawImage(letters.getSubimage(40, 32, 56, 8), 44, 108, null);
	}

}
