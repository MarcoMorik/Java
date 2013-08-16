package javakurs2011.asciicraft;

import jline.ANSIBuffer.ANSICodes;

/**
 * This class represents a Tile, which should not be minded: A Simple AirTile.
 *
 * If the player stays on top of one of this tiles, he will be dropped to the first non air tile. This
 * can be read in Player.simulate(..)
 */
public class AirTile extends Tile {
	// ! Creates an empty air tile
	public AirTile() {
	}

	//! Returns a space (since air is empty ...)
	@Override
	public String toString() {
		return ANSICodes.attrib(44) + " ";
	}
}
