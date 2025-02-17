import core.Mover;
import core.Properties;
import core.TypingScreen;
/**
 * 
 * @author mike802
 *
 * ??? - 2019
 * 
 */
public class driver {

	public static void main(String[] args) {
		String currentDir = System.getProperty("user.dir");
		Properties properties = new Properties(currentDir);
		TypingScreen screen = new TypingScreen(properties);
		screen.init();
		Mover mover = new Mover(screen);
		Thread thread = new Thread(mover);
		thread.start();
	}
}