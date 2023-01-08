//Anirudh Goel
//Class which is responsible for all the tiles on the board

public class Tile {
	//declaring variables 
	private int value; 
	private int row;
	private int col;
	
	//Constructor overloading
	
	/*
	 * constructor method to set value
	 * pre: value of tile
	 * post: none
	 */
	public Tile(int value) {
		super();
		this.value = value;
	}
	
	/*
	 * constructor method to set tile and value
	 * pre: value, row and column of tile
	 * post: none
	 */
	public Tile(int value, int row, int col) {
		this.value = value;
		this.row = row;
		this.col = col;
	}
	
	/*
	 * constructor method for tile
	 * pre: none
	 * post: none
	 */
	public Tile() {
		new Tile(0);
	}

	
	/*
	 * accessor method to get value
	 * pre: none
	 * post: returns value of tile
	 */
	public int getValue() {
		return value;
	}

	/*
	 * mutater method to set value of tile
	 * pre: value of tile
	 * post: none
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/*
	 * mutater method to set position of the tile
	 * pre: row and column of tile
	 * post: none
	 */
	public void setPosition(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/*
	 * method to get value when tiles are merged
	 * pre: none
	 * post: returns value of merged tile
	 */
	public int merging() {
		return (this.value += this.value);
	}
	
	/*
	 * method to check whether numbered (nonzero) tile changes its position
	 * pre: rows and columns
	 * post: true if tile changed its position, false if not
	 */
	public boolean hasMoved(int row, int col) {
		return (!isEmpty() && ((this.row != row) || (this.col != col)));
	}
	
	/*
	 * method to check whether tile is empty
	 * pre: none
	 * post: returns value as 0 because tile doesn't exist
	 */
	public boolean isEmpty() {
		return (value == 0);
	}
	
}