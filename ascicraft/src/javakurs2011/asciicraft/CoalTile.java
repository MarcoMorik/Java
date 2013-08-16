package javakurs2011.asciicraft;

import jline.ANSIBuffer.ANSICodes;

/**
 * This class will represent a Tile which is filled with coal.
 *
 * It can be shown using a 'C' or an utf like character.
 */
public class CoalTile extends Tile {
	// ! Constructor
	public CoalTile() {
	}

	// ! Returns a coal tile as a string.
	@Override
	public String toString() {
		if (debug_mode) {
			return ANSICodes.attrib(7) + "C" + ANSICodes.attrib(0) ;
		} else {
			return ANSICodes.attrib(42) + "░";
		}
		// return 'C';
	}
}
