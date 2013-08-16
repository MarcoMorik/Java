package javakurs2011.asciicraft;

import jline.ANSIBuffer.ANSICodes;

/**
 * This tile is the base tile, a mountain is made of.
 */
public class DirtTile extends Tile {
	// ! Constructor
	public DirtTile() {
	}

	/**
	 * Returns a string with greenish background.
	 */
	@Override
	public String toString() {
		if (debug_mode) {
			return ANSICodes.attrib(42) + "D";
		} else {
			return ANSICodes.attrib(42) + " ";
		}
		// return 'D';
	}
}
