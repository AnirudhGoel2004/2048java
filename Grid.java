//Anirudh Goel 
//Grid class which also houses GUI elements and paint and print components

//import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Grid extends JPanel {
	//declaring variables 
	private static final long serialVersionUID = 1L;
	private static final int TILE_RADIUS = 15;
	private static final int WIN_MARGIN = 20;
	private static final int TILE_SIZE = 65;
	private static final int TILE_MARGIN = 15;
	private static final String FONT = "Tahoma";
	
	/*
	 * method to paint all components of the program
	 * pre: graphics g2
	 * post: calls on all draw methods
	 */
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);

		Graphics2D g = ((Graphics2D) g2); // cast to get context for drawing

		// turn on antialiasing for smooth and non-pixelated edges
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		drawBackground(g);
		drawTitle(g);
		drawScoreBoards(g);
		drawBoard(g);
		instructions(g);

		g.dispose(); // release memory
	}
	
	/*
	 * method to print title
	 * pre: graphics g
	 * post: none
	 */
	private static void drawTitle(Graphics g) {
		g.setFont( new Font(FONT, Font.BOLD, 38) );
		g.setColor( ColorScheme.BRIGHT );
		g.drawString("2048", WIN_MARGIN, 53);
	}
	
	/*
	 * method to draw scoreboards
	 * pre: graphics g
	 * post: none
	 */
	private void drawScoreBoards(Graphics2D g) {
		int width = 80;
		int height = 40;
		int xOffset = Game.WINDOW.getWidth() - WIN_MARGIN - width;
		int yOffset = 16;
		g.fillRoundRect(xOffset, 26, 65, height, TILE_RADIUS, TILE_RADIUS);
		g.setFont( new Font(FONT, Font.BOLD, 10) );
		g.setColor( new Color(0XFFFFFF) );
		g.drawString("SCORE", xOffset + 7, yOffset + 25);
		g.setFont( new Font(FONT, Font.BOLD, 12) );
		g.drawString(String.valueOf(Game.BOARD.getScore()), xOffset + 7, yOffset + 40);
		g.setColor( ColorScheme.BRIGHT );
		g.fillRoundRect(xOffset - 80, 26, 65, height, TILE_RADIUS, TILE_RADIUS);
		g.setFont( new Font(FONT, Font.BOLD, 10) );
		g.setColor( new Color(0XFFFFFF) );
		g.drawString("BEST", xOffset - 73, yOffset + 25);
		g.setFont( new Font(FONT, Font.BOLD, 12) );
		g.drawString(String.valueOf(Game.BOARD.getHighScore()), xOffset - 73, yOffset + 40);
	}
	
	/*
	 * method to draw brackground
	 * pre: graphics g
	 * post: none
	 */
	private static void drawBackground(Graphics g) {
		g.setColor(ColorScheme.WINBG);
		g.fillRect(0, 0, Game.WINDOW.getWidth(), Game.WINDOW.getHeight());		
	}
	
	/*
	 * method to draw board
	 * pre: graphics g
	 * post: calls on draw tile method
	 */
	private static void drawBoard(Graphics g) {
		g.translate(WIN_MARGIN, 80);
		g.setColor(ColorScheme.GRIDBG);
		g.fillRoundRect(0, 0, 335, 320 + TILE_MARGIN, TILE_RADIUS, TILE_RADIUS);

		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				drawTile(g, Game.BOARD.getTileAt(row, col), col, row); //calls on draw tile method
			}
		}
	}
	
	/*
	 * method to print game instructions
	 * pre: graphics g
	 * post: none
	 */
	private static void instructions(Graphics g) {
		g.setFont( new Font(FONT, Font.BOLD, 12) );
		g.setColor( ColorScheme.BRIGHT );
		g.drawString("HOW TO PLAY: Use your arrow keys to move the tiles.", 2, 360);
		g.drawString("Tiles with the same number merge into one when", 1, 375);
		g.drawString("they touch. Add them up to reach 2048 or more!", 1, 390);
		g.drawString("When no moves are no longer possible, your next", 1, 415);
		g.drawString("move will signal GAME OVER!", 1, 430);
		g.drawString("Press 'esc' to quit.", 1, 455);
		g.drawString("Press 'enter' for a new game.", 1, 480);
		g.drawString("Made by Anirudh Goel", 2, -10);
	}
	
	/*
	 * method to draw tiles
	 * pre: graphics g, tile, and its x and y components
	 * post: none
	 */
	private static void drawTile(Graphics g, Tile tile, int x, int y) {
		int value = tile.getValue();
		int xOffset = x * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
		int yOffset = y * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
		g.setColor(Game.COLORS.getTileBackground(value));
		g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, TILE_RADIUS, TILE_RADIUS);

		g.setColor(Game.COLORS.getTileColor(value));

		final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
		final Font font = new Font(FONT, Font.BOLD, size);
		g.setFont(font);

		String s = String.valueOf(value);
		final FontMetrics fm = g.getFontMetrics(font);

		final int w = fm.stringWidth(s);
		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

		if (value != 0) { //tile is merged
			Game.BOARD.getTileAt(y, x).setPosition(y, x); // tile gets its new position
			g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE - (TILE_SIZE - h) / 2 - 2);
		}
		
		if (Game.BOARD.getLostGame() != null) { //if game is over and no new moves can be made
			g.setColor(new Color(255, 255, 255, 40));
			g.fillRect(0, 0, Game.WINDOW.getWidth(), Game.WINDOW.getHeight());
			g.setColor(Color.RED);
			g.setFont(new Font(FONT, Font.BOLD, 30));
			g.drawString("GAME OVER!", 70, 160);
		}

	}

}