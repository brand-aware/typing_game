package core;

import java.io.IOException;

/**
 * 
 * @author mike802
 *
 * ??? - 2019
 * 
 */
public class Mover implements Runnable{
	
	private TypingScreen screen;
	
	public Mover(TypingScreen scn) {
		screen = scn;
	}

	@Override
	public void run() {
		while(true) {
			try {
				screen.doMove();
				Thread.sleep(37);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
