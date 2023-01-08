//Anirudh Goel
//Class which creates the window for the game and houses most of the GUI elements of the program

//import statements
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Window extends JFrame {
	//declaring variables 
	private static final long serialVersionUID = -8804446439773037674L;
	private int width = 390; //dimensions of the window
	private int height = 615;

	/*
	 * //constructor method to create window
	 * pre: title string which is 2048
	 * post: none
	 */
	public Window(String title) {
		super(title); // Window title

		this.setLayout( new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close window to exit
		this.setSize(this.width, this.height);
		this.setLocationRelativeTo(null); // centering

		this.setResizable(false);
		this.setFocusable(true); // set focus on window so KeyListener works	

		getContentPane().add( new Grid() );

		this.setVisible(true); // show window

	}
	
	/*
	 * method to get width
	 * pre: none
	 * post: returns width
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * method to get height
	 * pre: none
	 * post: returns height
	 */
	public int getHeight() {
		return height;
	}

}