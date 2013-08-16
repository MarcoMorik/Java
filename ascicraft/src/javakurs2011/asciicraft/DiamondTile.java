package javakurs2011.asciicraft;

import jline.ANSIBuffer.ANSICodes;

/**
 * This class is the one every player seeks: A Diamond.
 *
 * Once this tile is mined the game ends with success.
 * It gets represented by a nice flowerlike symbol in utf8. If the debug
 * mode is activated (using -d in commandline, or Tile.debug_mode is set
 * to true), then this tile will allway be visible. (By overwriting
 * setVisible(..) to reflect those changes).
 */
public class DiamondTile extends Tile {
	// ! Constructor
	public DiamondTile() {
		if (Tile.debug_mode) {
			super.visible = true;
		}
	}

	/**
	 * Returns a string with greenish background.
	 */
	@Override
	public String toString() {
		return ANSICodes.attrib(1) + ANSICodes.attrib(33)+ ANSICodes.attrib(40) + "❁";
	}

	/**
	 * This method is overwritten to show the tile, if in debug mode.
	 */
	@Override
	public void setVisible(boolean b) {
		if (Tile.debug_mode) {
			super.visible = true;
		} else {
			super.setVisible(b);
		}
	}
}
