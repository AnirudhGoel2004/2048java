//Anirudh Goel
//Class which is responsible to create the board and include the functions of the game

//import statements
import java.util.ArrayList;
import java.util.List;

public class Board {
	//declaring variables 
	private int size; 						// size of the grid
	private int score; 						// game score
	private int highscore;					// game high score
	private int emptyTiles;					// number of tiles with zero value
	private int starttiles = 2; 			// number of tiles board starts with (usually two tiles)
	private String lostgame;				// string is filled when user loses 
	private boolean genNewTile = false;		// generate new tile when any tile moved
	private List<List<Tile>> tiles;			// board

	/*
	 * constructor method which creates a new board
	 * pre: size of the grid
	 * post: calls start method and bind method
	 */
	public Board(int size) {
		super();
		this.size = size;
		this.emptyTiles = this.size * this.size;
		this.tiles = new ArrayList<>();
		
		Game.CONTROLS.bind();
		start();
	}
	
	/*
	 * start method which calls on other methods to start the game
	 * pre: none
	 * post: calls on initialize and generate tiles method
	 */
	public void start() {
		initialize();
		genInitTiles();
		//show();
	}
	
	/*
	 * method which initializes the tiles for the new board
	 * pre: none
	 * post: none
	 */
	private void initialize() {
		for (int row = 0; row < this.size; row++) {
			tiles.add(new ArrayList<Tile>());
			for (int col = 0; col < this.size; col++) {
				tiles.get(row).add(new Tile());
			}
		}
	}
	
	/*
	 * accessor method which gets the size of the grid
	 * pre: none
	 * post: returns the size of the grid
	 */
	public int getSize() {
		return size;
	}
	
	/*
	 * method which sets the size of the grid
	 * pre: size of the gird
	 * post: none
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/*
	 * accessor method which gets the tiles for the board
	 * pre: none
	 * post: returns the tiles
	 */
	public List<List<Tile>> getTiles() {
		return tiles;
	}

	/*
	 * method which sets the tiles of the grid
	 * pre: tiles of the gird
	 * post: none
	 */
	public void setTiles(List<List<Tile>> tiles) {
		this.tiles = tiles;
	}
	
	/*
	 * accessor method which gets tiles
	 * pre: row and column of tiles
	 * post: none
	 */
	public Tile getTileAt(int row, int col) {
		return tiles.get(row).get(col);
	}

	/*
	 * method which sets tiles
	 * pre: row and column of tiles and tile
	 * post: none
	 */
	public void setTileAt(int row, int col, Tile t) {
		tiles.get(row).set(col, t);
	}

	/*
	 * method which removes tiles
	 * pre: row and column of tiles
	 * post: none
	 */
	public void remTileAt(int row, int col) {
		tiles.get(row).remove(col);
	}
	
	/*
	 * accessor method which gets score of the game
	 * pre: none
	 * post: returns the score
	 */
	public int getScore() {
		return score;
	}

	/*
	 * merges two touching with the same number into one
	 * pre: sequence of tiles
	 * post: merged sequence of tiles
	 */
	private List<Tile> mergeTiles(List<Tile> sequence) {
		for (int l = 0; l < sequence.size() - 1; l++) { 
			if (sequence.get(l).getValue() == sequence.get(l + 1).getValue()) {
				int value = sequence.get(l).merging(); //initializes value
				score += value; //value of merged tiles is added to score
				sequence.remove(l + 1); 
				genNewTile = true; // board has changed its state
				emptyTiles++; //adds one to empty tiles
			}
		}
		return sequence;
	}

	/*
	 * creates empty instances and adds them to the left of merged sequence to fit the board
	 * pre: merged sequence of tiles
	 * post: returns merged
	 */
	
	private List<Tile> addEmptyTilesFirst(List<Tile> merged) {
		for (int k = merged.size(); k < size; k++) {
			merged.add(0, new Tile());
		}
		return merged;
	}

	/*
	 * creates empty instances and adds them to the right of merged sequence to fit the board
	 * pre: merged sequence of tiles
	 * post: returns merged
	 */
	
	private List<Tile> addEmptyTilesLast(List<Tile> merged) { // boolean last/first
		for (int k = merged.size(); k < size; k++) {
			merged.add(k, new Tile());
		}
		return merged;
	}
	
	/*
	 * method which removes empty tile rows
	 * pre: row
	 * post: returns moved
	 */
	private List<Tile> removeEmptyTilesRows(int row) {

		List<Tile> moved = new ArrayList<>();

		for (int col = 0; col < size; col++) {
			if (!getTileAt(row, col).isEmpty()) { // NOT empty
				moved.add(getTileAt(row, col));
			}
		}

		return moved;
	}

	/*
	 * method which removes empty tile columns
	 * pre: row
	 * post: returns moved
	 */
	private List<Tile> removeEmptyTilesCols(int row) {

		List<Tile> moved = new ArrayList<>();

		for (int col = 0; col < size; col++) {
			if (!getTileAt(col, row).isEmpty()) { // NOT empty
				moved.add(getTileAt(col, row));
			}
		}

		return moved;
	}

	/*
	 * method which sets row to board
	 * pre: list of moved and row
	 * post: returns moved
	 */
	private List<Tile> setRowToBoard(List<Tile> moved, int row) {
		for (int col = 0; col < tiles.size(); col++) {
			if (moved.get(col).hasMoved(row, col)) {
				genNewTile = true; //new tile must be generated
			}
			setTileAt(row, col, moved.get(col));
		}

		return moved;
	}

	/*
	 * method which sets columns to board
	 * pre: list of moved and row
	 * post: returns moved
	 */
	private List<Tile> setColToBoard(List<Tile> moved, int row) {
		for (int col = 0; col < tiles.size(); col++) {
			if (moved.get(col).hasMoved(col, row)) {
				genNewTile = true; //new tile must be generated
			}			
			setTileAt(col, row, moved.get(col));
		}

		return moved;
	}

	/*
	 * method for when user moves up (calls on all necessary methods)
	 * pre: tiles moved
	 * post: none
	 */
	public void moveUp() {

		List<Tile> moved;

		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesCols(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesLast(moved);
			moved = setColToBoard(moved, row);

		}

	}

	/*
	 * method for when user moves down (calls on all necessary methods)
	 * pre: tiles moved
	 * post: none
	 */
	public void moveDown() {

		List<Tile> moved;

		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesCols(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesFirst(moved);
			moved = setColToBoard(moved, row);

		}
		
	}

	/*
	 * method for when user moves left (calls on all necessary methods)
	 * pre: tiles moved
	 * post: none
	 */
	public void moveLeft() {

		List<Tile> moved;

		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesRows(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesLast(moved);
			moved = setRowToBoard(moved, row);

		}
		
	}

	/*
	 * method for when user moves right (calls on all necessary methods)
	 * pre: tiles moved
	 * post: none
	 */
	public void moveRight() {
		
		List<Tile> moved;
		
		for (int row = 0; row < size; row++) {

			moved = removeEmptyTilesRows(row);
			moved = mergeTiles(moved);
			moved = addEmptyTilesFirst(moved);
			moved = setRowToBoard(moved, row);

		}
		
	}
	
	/*
	 * method to check if game is over
	 * pre: none
	 * post: none
	 */
	public void isGameOver() {
		
		if (isFull()) { //checks if board is full
			if (!isMovePossible()) {
				// you lost (board is full with no tiles to merge)
				// end(false);
				setLostGame("LOST");
			}
				
		} else {
			newRandomTile(); // game continues
		}
	}

	/*
	 * method for when board is full
	 * pre: none
	 * post: returns empty tiles which is equal to 0
	 */
	private boolean isFull() {
		return emptyTiles == 0;
	}
	
	/*
	 * method that checks if a move is possible
	 * pre: none
	 * post: returns boolean
	 */
	private boolean isMovePossible() {
		for (int row = 0; row < size; row++) { //checks for if any tiles together have the same value
			for (int col = 0; col < size - 1; col++) {
				if (getTileAt(row, col).getValue() == getTileAt(row, col + 1).getValue()) {
					return true;
				}
			}
		}
		
		for (int row = 0; row < size - 1; row++) {
			for (int col = 0; col < size; col++) {
				if (getTileAt(row, col).getValue() == getTileAt(row + 1, col).getValue()) {
					return true;
				}
			}
		}
		return false; //if not, return false
	}
	
	/*
	 * method to generate a new tile
	 * pre: none
	 * post: calls on new random tile method
	 */
	private void genInitTiles() {
		for (int i = 0; i < starttiles; i++) {
			genNewTile = true; //need to generate tile is set to true
			newRandomTile(); //calls on new random tile method
		}
	}

	/*
	 * method for a new random tile
	 * pre: none
	 * post: none
	 */
	private void newRandomTile() {
		if (genNewTile) { //checks if need to generate new tile
			int row;
			int col;
			int value = Math.random() < 0.9 ? 2 : 4;
			do {
				row = (int) (Math.random () * 4); //creates random row
				col = (int) (Math.random () * 4); //creates random column
			} 
			while (getTileAt(row, col).getValue() != 0);
			setTileAt(row, col, new Tile(value, row, col));
			emptyTiles--;
			genNewTile = false;
		}
	}

	/*
	 * method to get string of lost game
	 * pre: none
	 * post: returns lostgame string
	 */
	public String getLostGame() {
		return lostgame;
	}

	/*
	 * method to set string of lost game
	 * pre: string lostgame
	 * post: none
	 */
	public void setLostGame(String lostgame) {
		this.lostgame = lostgame;
	}
	
	/*
	 * method when user resets game
	 * pre: size of grid
	 * post: calls start method
	 */
	public void restart(int size) {
		score = 0; //sets score to 0
		lostgame = null; //lostgame string is set to null
		this.size = size;
		this.emptyTiles = this.size * this.size;
		this.tiles = new ArrayList<>();

		start();
	}
	
	/*
	 * method to keep track of highscore
	 * pre: none
	 * post: returns high score
	 */
	public int getHighScore() {
        if(score>highscore) {
            highscore = score;
        }
        return highscore;
    }

}