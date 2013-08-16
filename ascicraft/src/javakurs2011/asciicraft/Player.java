package javakurs2011.asciicraft;

import jline.ANSIBuffer.ANSICodes;

/**
 * This class represents a basic player. 
 * 
 * The player can be moved, simulated and got a direction he is looking at, represented
 * by the boolean flag facing_left.
 */
public class Player extends Tile {
	//! Where does the player look?
	private boolean facing_left;
	
	//! position of player
	private int x;
	private int y;

	//! Constructor taking initial position. (Facing is alway set to left)
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		
		facing_left = true;
	}

	// ! Returns a character representing the current player and its stats
	public String toString() {
		return ANSICodes.attrib(44)+(facing_left ? "◀" : "▶"); // ◁▷
	}

	/**
	 * Updates the player position, according to its initial position
	 */
	public Tile simulate(World world) {
		
		// check if beneath player is air, if so move him down and animate
		while (world.getTile(this.x, this.y + 1).getClass() == AirTile.class && 
				this.y+1 <= world.getHeight()) {
			this.y += 1;
			world.show();
		}
		return this;
	}

	/**
	 * Moves the player in given direction only, if target place is empty (means filled with air ...)
	 * 
	 * @param w World to be checked for an empty space
	 * @param xOffset offset to move player
	 * @param yOffset move player in down direction by this amount
	 */
	public void move(World w, int xOffset, int yOffset) {
		// check if rotation needs to be changed
		if (this.facing_left != (xOffset < 0)) {
			// rotation is needed
			this.facing_left = (xOffset < 0);
		} else {
			// no new rotation, so move directly, if not at end or blocked
			if ((this.x + xOffset > 0) && (this.x + xOffset < w.getWidth()-1 ) &&
				(this.y + yOffset > 0) && (this.y + yOffset < w.getHeight()-1) &&
				(w.getTile(this.x + xOffset, this.y + yOffset).getClass() == AirTile.class)) {
				this.x += xOffset;
				this.y += yOffset;
			}
		}

	}

	public boolean isFacingLeft() {
		return this.facing_left;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
