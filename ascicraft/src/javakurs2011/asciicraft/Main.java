package javakurs2011.asciicraft;

import java.io.IOException;

import jline.ANSIBuffer.ANSICodes;
import jline.Terminal;

/**
 * Main class containing main method.
 */
public class Main {

	public static void main(String[] args) {
		// create terminal binding
		Terminal terminal = Terminal.setupTerminal();
		Terminal.resetTerminal();

		// get defaults for console
		int width = terminal.getTerminalWidth();
		int height = terminal.getTerminalHeight();

		// create world from arguments
		World w = processArguments(args, width, height);

		// return when game is done
		runMainLoop(terminal, w);

		// done
		Terminal.resetTerminal();
	}

	/**
	 * Main loop method, updating the game as long no one has won
	 * 
	 * @param terminal
	 * @param w
	 */
	protected static void runMainLoop(Terminal terminal, World w) {
		// create some statistics
		int found_coal = 0;
		int found_iron = 0;
		int found_diamond = 0;
		int found_dirt = 0;

		// main loop
		boolean game_is_running = true;
		while (game_is_running) {
			// update and redraw world
			w.simulate();
			w.show();

			// get keyboard input
			int key;
			Tile mined_tile = null;
			try {
				key = terminal.readCharacter(System.in);
				switch (key) {
				case 'w':
					w.getPlayer().move(w, 0, -1);
					break;
				case 's':
					w.getPlayer().move(w, 0, +1);
					break;
				case 'a':
					w.getPlayer().move(w, -1, 0);
					break;
				case 'd':
					w.getPlayer().move(w, +1, 0);
					break;

				case 'e': // mine above player
					// mine something
					mined_tile = mine(w, 0, -1);

					// check if one of them is a diamond
					if (mined_tile.getClass() == DiamondTile.class) {
						// yes it is, so terminate game with success
						game_is_running = false;
					}
					break;
				case 'r': // mine in view direction up
					mined_tile = mine(w, w.getPlayer().isFacingLeft() ? -1 : 1, -1);
					break;
				case 'f': // mine simply in view direction
					mined_tile = mine(w, w.getPlayer().isFacingLeft() ? -1 : 1,  0);
					break;
				case 'c': // mine facing down
					mined_tile = mine(w, w.getPlayer().isFacingLeft() ? -1 : 1, +1);
					break;
				case 'x': // mine down
					mined_tile = mine(w, 0, +1);
					break;
				case ' ': // jump in facing direction, if free to jump
					// create offeset
					int x = w.getPlayer().getX();
					int y = w.getPlayer().getY();
					int xoffset = w.getPlayer().isFacingLeft() ? -1 : 1;
					int yoffset = -1;

					// check if tile is free and the intermediate tiles are free
					if (w.getTile(x + xoffset, y + yoffset).getClass() == AirTile.class
							&& w.getTile(x, y + yoffset).getClass() == AirTile.class) {
						// path and tile is free, so "jump"
						w.getPlayer().setX(x + xoffset);
						w.getPlayer().setY(y + yoffset);
					}
					break;
				case 27:
					game_is_running = false;
					break;
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} // try read key

			// check if something was mined
			if (mined_tile != null) {
				if (mined_tile.getClass() == DiamondTile.class) {
					found_diamond++;
					// terminate game with success
					game_is_running = false;
				}else if (mined_tile.getClass() == IronTile.class) {
					found_iron ++;
				}else if( mined_tile.getClass() == CoalTile.class) {
					found_coal ++;
				} else {
					found_dirt++;
				}
			}
		} // while main loop

		// Print result of game		
		printGameOverScreen(found_coal, w.getCoalCount(), found_iron, w.getIronCount(), found_diamond, found_dirt, w.getDirtCount() );

	} // end of main loop method

	/**
	 * This Method will be used to print a screen, once the game is over, summarazing the achievements of the player.
	 *	
	 * @param found_coal number of already found coal
	 * @param max_coal amount of coal present in game
	 * @param found_iron iron digged from the player
	 * @param max_iron maximum of iron foundable by player
	 * @param found_diamond amount of diamonds found by player
	 * @param found_dirt number of dirt tiles mined by player
	 * @param max_dirt number of dirt files mined by chuck norris
	 */
	protected static void printGameOverScreen(int found_coal, int max_coal, int found_iron, int max_iron,
			int found_diamond, int found_dirt, int max_dirt) {
		// clear screen and draw a nice game over title
		String sOverScreen = ANSICodes.clrscr();
		sOverScreen += ANSICodes.attrib(0) + ANSICodes.attrib(5) + ANSICodes.attrib(33) + ANSICodes.attrib(47);
		sOverScreen += ANSICodes.gotoxy(1, 1);
		sOverScreen += "      ╭───────────────╮\n";
		sOverScreen += "      │   " + ANSICodes.attrib(31) +"Game OVER"+ ANSICodes.attrib(33)+"   │\n";
		sOverScreen += "      ╰───────────────╯\n";
		
		// create the first line of output to get the length of it
		String sFirstLine = String.format("%s│%s %-12s %5d / %5d %s│%s\n", 
				ANSICodes.attrib(32), 
				ANSICodes.attrib(36), 
				"diamonds", 
				found_diamond, 
				1,
				ANSICodes.attrib(32), 
				ANSICodes.attrib(33));
		
		// draw a dashed line in the same length of the output created (minus the vertical bars)
		sOverScreen += ANSICodes.attrib(32) + "╭────────────────────────────╮\n" + ANSICodes.attrib(33);
		// draw statistic output
		sOverScreen += sFirstLine; 
		sOverScreen += String.format("%s│%s %-12s %5d / %5d %s│%s\n", ANSICodes.attrib(32), ANSICodes.attrib(33), "iron", found_iron, max_iron, ANSICodes.attrib(32), ANSICodes.attrib(33));
		sOverScreen += String.format("%s│%s %-12s %5d / %5d %s│%s\n", ANSICodes.attrib(32), ANSICodes.attrib(33), "coal", found_coal, max_coal, ANSICodes.attrib(32), ANSICodes.attrib(33));
		sOverScreen += String.format("%s│%s %-12s %5d / %5d %s│%s\n", ANSICodes.attrib(32), ANSICodes.attrib(33), "dirt", found_dirt, max_dirt, ANSICodes.attrib(32), ANSICodes.attrib(33));
		sOverScreen += String.format("%s│%s %-12s %5.1f / %3.1f %s│%s\n", ANSICodes.attrib(32), ANSICodes.attrib(35), "percent", (found_dirt+found_coal+found_iron+found_diamond)/(float)(max_dirt+max_coal+max_iron+1)*100.0f, 100.0f, ANSICodes.attrib(32), ANSICodes.attrib(33));
		
		// add second closing line
		sOverScreen += ANSICodes.attrib(32) + "╰────────────────────────────╯\n" + ANSICodes.attrib(33);
		
		// prepare for printing on screen.
		sOverScreen += "\n";
		sOverScreen += "\n";
		sOverScreen += ANSICodes.attrib(0);
		System.out.println(sOverScreen);
	}

	/**
	 * Set all tiles next to the mined tile visible and returns the mined tile.
	 * 
	 * If the player is at the last -1 level, only left and right mining can be
	 * performed. Same is true for down and up minig: Nothing will be preformed
	 * if this would result in mining at the border.
	 * 
	 * Ascii diagramm for mining "right" (in view direction):
	 * 
	 * <pre>
	 * --D--
	 * -☻MD-
	 * --D--
	 * 
	 * ☻ .. player (facing right)
	 * M .. mined tile
	 * D .. discovered tile
	 * - .. unchanged tile
	 * </pre>
	 * 
	 * @param w World where mining takes place (tile will be replaced with air)
	 * @param x_direction direction of mining (0 only when up or down mining)
	 * @param y_direction 1 if the player is about to be mining down, -1 if she is mining up, 0 only when mining direcly
	 * @return the Tile mined
	 */
	private static Tile mine(World w, int x_direction, int y_direction) {
		// short cut for player position
		int player_x = w.getPlayer().getX();
		int player_y = w.getPlayer().getY();

		// ignore mining in an undesired direction
		if (player_y + y_direction >= w.getHeight() - 1
				|| player_y + y_direction <= 0) {
			y_direction = 0;
		}
		if (player_x + x_direction >= w.getWidth() - 1
				|| player_x + x_direction <= 0) {
			x_direction = 0;
		}

		// mine tile
		Tile mined_tile = w.getTile(player_x + x_direction, player_y
				+ y_direction);

		// now remove tile from game
		w.setTile(player_x + x_direction, player_y + y_direction, new AirTile());

		// activate all now seeable tiles
		w.getTile(player_x + x_direction + 1, player_y + y_direction).setVisible(true);
		w.getTile(player_x + x_direction - 1, player_y + y_direction).setVisible(true);
		w.getTile(player_x + x_direction, player_y + y_direction + 1).setVisible(true);
		w.getTile(player_x + x_direction, player_y + y_direction - 1).setVisible(true);

		return mined_tile;
	}

	/**
	 * Recieves all commandline arguments and parses them into the returned world variable.
	 * @param args arguments given from commandline ("./run.sh -W=10 -H=10 --help")
	 * @param width width of terminal
	 * @param height height of termial
	 * @return a new created world, representing the parameters given
	 */
	protected static World processArguments(String[] args, int width, int height) {
		// check arguments given
		if (args.length > 0) {
			for (String s : args) {
				// ignore all - (dashes)
				int position = 0;
				for (; s.charAt(position) == '-'; ++position) {
				}

				// check if arguments are present and apply action if needed
				switch (s.charAt(position)) {
				case 'W':
					if (s.charAt(position + 1) == '=') {
						width = Integer.parseInt(s.substring(position + 2));
					}
					break;
				case 'H':
					if (s.charAt(position + 1) == '=') {
						height = Integer.parseInt(s.substring(position + 2));
					}
					break;
				case 'd':
					// debug mode selected
					Tile.debug_mode = true;
					break;
				default:
					System.out.println("Could not parse \"" + s + "\"");
				case 'h':
					printHelp(args);
					break;
				}
			}
		}

		// setup game
		Player p = new Player(0, 0);
		World w = new World(width, height, p);
		w.create();

		return w;
	}

	/**
	 * Helper method printing usage and closes this programm.
	 */
	private static void printHelp(String[] args) {
		System.out.println("Usage: java asciicraft [-W=WIDTH][-H=HEIGHT][-h(elp)][-d(ebug mode)]");
		System.exit(0);
	}
}
