//Anirudh Goel
//Class which houses the control methods and implements keylistener

//import statements
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Controls implements KeyListener {

	public void keyTyped(KeyEvent e) {} //empty
	public void keyReleased(KeyEvent e) {} //empty
	
	/*
	 * method to control program when a key is pressed
	 * pre: keyevent (key pressed)
	 * post: calls on move methods 
	 */
	public void keyPressed(KeyEvent e) {
		//declaring variables
		int keyCode = e.getKeyCode(); 
		Sounds soundeffect = new Sounds(); //sound effect
		String swipe;
		swipe = ".//res//swipe.wav"; //sound file
		
		soundeffect.setFile(swipe);
		
		switch (keyCode) {
			case KeyEvent.VK_UP: //user moves up
				Game.BOARD.moveUp();
				if (Game.BOARD.getLostGame() == null) { //sound effect is only played when game is not over
					soundeffect.play();
				}
				break;
			case KeyEvent.VK_DOWN: //user moves down
				Game.BOARD.moveDown();
				if (Game.BOARD.getLostGame() == null) {
					soundeffect.play();
				}
				break;
			case KeyEvent.VK_LEFT: //user moves left
				Game.BOARD.moveLeft();
				if (Game.BOARD.getLostGame() == null) {
					soundeffect.play();
				}
				break;
			case KeyEvent.VK_RIGHT: //user moves right
				Game.BOARD.moveRight();
				if (Game.BOARD.getLostGame() == null) {
					soundeffect.play();
				}
				break;
			case KeyEvent.VK_ESCAPE: //user presses escape
				Game.WINDOW.dispose();
				break;
			case KeyEvent.VK_ENTER: //user presses enter
				Game.BOARD.restart(4);
				break;
			default:
				break;
		}
		
		Game.BOARD.isGameOver(); //checks if game is over
		//Game.BOARD.show();
		Game.WINDOW.repaint(); //repaints window
		
	}
	
	/*
	 * method to bind keys
	 * pre: none
	 * post: calls on method to add key listener
	 */
	public void bind() {
		Game.WINDOW.addKeyListener(this);
	}
	
	//public class sounds houses all the sound effect elements of the game
	
	public class Sounds{

        Clip clip;
        
    	/*
    	 * method to set sound effect file
    	 * pre: string of the file name
    	 * post: none
    	 */
        public void setFile(String soundFileName) {

            try {
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            catch(Exception e) {

            }
        }
    	/*
    	 * method to play sound effect
    	 * pre: none
    	 * post: plays sound effect
    	 */
        public void play() {
            clip.setFramePosition(0);
            clip.start();
        }

    }

}