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
		if(args.length < 1) {
			System.out.println("Error format: java driver <root dir>");
			System.exit(0);
		}
		
		Properties properties = new Properties(args[0]);
		TypingScreen screen = new TypingScreen(properties);
		screen.init();
		Mover mover = new Mover(screen);
		Thread thread = new Thread(mover);
		thread.start();
	}
}