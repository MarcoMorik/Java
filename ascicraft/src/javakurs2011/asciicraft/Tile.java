package javakurs2011.asciicraft;

/**
 * An ascii tile, interface gathering all tiles into one.
 *
 * If the static variable debug_mode is set to true, all tiles will be in debug mode (non utf8, using only latin1)
 */
public abstract class Tile {
	//! if this flag is set to true, all tiles are drawn in debug mode (letters instead of border symbols)
	public static boolean debug_mode = false;

	// ! if set to true, this tile will be drawn
	protected boolean visible;
	
	// ! converts this tile to a string
	public abstract String toString();

	// ! Constructor
	public Tile() {
		this.visible = true;
	}

	// ! returns true, if this tile is still active
	boolean isVisible() {
		return this.visible;
	}

	//! set the visibility flag to the one given
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
