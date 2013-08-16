package javakurs2011.asciicraft;

import jline.ANSIBuffer.ANSICodes;

/**
 * This class represents the world of this game.
 *
 * The world includes all tiles and the player. Despite this saving of variables
 * it creates a new world, animates all tiles needed and gathers everything needed
 * for this game.
 */
public class World {
	// ! tiles of this world
	protected Tile[] tiles;

	// ! size of this world
	protected int width;
	protected int height;
	
	protected int iron_count;
	protected int coal_count;
	protected int dirt_count;

	// ! store current player position
	protected Player player;

	/**
	 * Creates this world by taking its dimensions
	 * 
	 * @param width
	 *            of world
	 * @param height
	 *            of world
	 */
	public World(int width, int height, Player player) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[this.width * this.height];
		this.player = player;
		
		this.iron_count = 0;
		this.coal_count = 0;
	}

	/**
	 * Creates the game field by some means of a random algorithm.
	 */
	public void create() {
		// fill with air tiles
		for (int i = 0; i < this.tiles.length; ++i) {
			this.tiles[i] = new AirTile();
		}

		// create some hills on the mid line (each max 1/8th height)
		int hill = (int) (this.height * (1.0 / 8.0) - Math.random()	* this.height / 4);
		for (int x = 0; x < this.width; ++x) {
			int upDown = (Math.random() * 2.0 - 1.0 > 0 ? -1 : 1);
			int offset = (int) (Math.random() * 2.0);
			hill += upDown * offset;
			int start_y = (int) (hill + this.height / 2);
			start_y = Math.max(0, start_y);

			// set surface down to end
			for (int y = start_y; y < this.height; ++y) {
				// check if water should be places
				this.tiles[x + y * this.width] = new DirtTile();
				this.dirt_count ++;
			}
		}

		// swap randomly one or more parts, if dirt is found (ignoring borders)
		for (int x = 1; x < this.width-1; ++x) {
			for (int y = 1; y < this.height-1; ++y) {
				// calculate index
				int index = x + y * width;
				if (this.tiles[index].getClass() == DirtTile.class) {
					// dirt found, so randomly replace a part
					switch ((int) (Math.random() * 10.0)) {
					case 0:
						this.tiles[index] = new CoalTile();
						this.coal_count ++;
						this.dirt_count --;
						break;
					case 1:
						this.tiles[index] = new IronTile();
						this.iron_count ++;
						this.dirt_count --;
						break;
					}
				}
			}
		}

		// now only show visible tiles (all tiles are initially invisible if
		// they are one item below ground)
		// all Tiles at the border = 0 and border = max will be also visible
		for (int x = 1; x < this.width-1; ++x) {
			int tiles_to_surface = 0;
			for (int y = 0; y < this.height-1; ++y) {
				// check if this was the last air tile
				if (this.tiles[x + y * width].getClass() != AirTile.class) {
					tiles_to_surface++;
				}

				if (tiles_to_surface > 1) {
					this.tiles[x + y * width].setVisible(false);
				}
			}
		}

		// place a diamond to be found later on
		// select a randomly one of the x positions (random does not reach 1.0,
		// so we never get out of bounds here!)
		int diamond_x = 1+(int) (Math.random() * this.width-1);

		// select one of the last 3 rows to be searched for (prelast row must be
		// (height -2) !!) (last row will not be used for mining ...)
		int diamond_y = (this.height - 4) + (int)(Math.random() * 3);
		DiamondTile diamond = new DiamondTile();
		diamond.setVisible(false);
		this.tiles[diamond_x + diamond_y * this.width] = diamond; 
		this.dirt_count ++;

		// search for player position
		int player_x = getWidth() / 2;
		int player_y = 0;

		// try and seek new player position
		while (player_y == 0) {
			// seek in current row for empty space
			while (getTile(player_x, player_y + 1).getClass() == AirTile.class
					&& player_y < getHeight()) {
				player_y++;
			}

			// if empty space found, player_y is sane
			if (player_y == getHeight()) {
				player_y = 0; // player cannot be placed, so start over again
				player_x--;
				player_x = player_x % getWidth();
			}
		}
		
		// set player position
		player.setX(player_x);
		player.setY(player_y);
	}

	public void show() {
		// print field from start
		String sField = ANSICodes.setmode(7) + ANSICodes.gotoxy(1, 1);

		// add all tiles
		for (int y = 0; y < this.height; ++y) {
			for (int x = 0; x < this.width; ++x) {
				// check if tile is visible
				if (this.tiles[x + y * width].isVisible()) { 
					sField += ANSICodes.attrib(0) + this.tiles[x + y * width];
				} else { // tile is hidden
					sField += ANSICodes.attrib(40) + " ";
				}
			}
			// ignore last newline
			if (y < this.height - 1) {
				sField += "\n";
			}
		}

		// print player
		Player p = getPlayer();
		sField += ANSICodes.gotoxy(p.getY() + 1, p.getX() + 1);
		sField += p.toString();
		sField += ANSICodes.attrib(37) + ANSICodes.attrib(47);
		sField += ANSICodes.gotoxy(1, 1);

		// and finally print the string to the console
		System.out.print(sField);
	}

	public void simulate() {
		// update player
		getPlayer().simulate(this);
	}

	public Tile getTile(int x, int y) {
		return this.tiles[(x % this.width) + (y % this.height) * this.width];
	}

	public void setTile(int x, int y, Tile tile) {
		if (x < width && x >= 0 && y < height && y >= 0) {
			this.tiles[x + y * this.width] = tile;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getIronCount() {
		return iron_count;
	}

	public int getCoalCount() {
		return coal_count;
	}

	public int getDirtCount() {
		return this.dirt_count;
	}
}
