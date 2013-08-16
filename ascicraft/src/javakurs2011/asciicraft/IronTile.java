package javakurs2011.asciicraft;

import jline.ANSIBuffer.ANSICodes;

/**
 * This tile represents the less common iron tile in the randomly created mountain.
 */
public class IronTile extends Tile {

	// ! Default constructor
	public IronTile() {
	}

	//! Convert this iron tile to a reddish dirt part with nice sparkles inside
	@Override
	public String toString() {
		if( debug_mode ){
			return ANSICodes.attrib(31) + "I";
		} else {
			return ANSICodes.attrib(42) + ANSICodes.attrib(31) + "▓";
		}
	}
}
