/**
 * 
 * @author mike802
 *
 * brand_aware
 * ??? - 2019
 * 
 */
package core;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import highscores.NameInput;

public class CommonScreen extends ConfigTypingScreen{
	
	protected Properties properties;
	
	protected JFrame typingScreen;
	protected JDesktopPane desktopPane;
	protected NameInput nameInput;
	
	protected JLabel logo, background;
	protected JLabel streakLabel, timeLabel, levelLabel;
	protected JTextArea objective;
	protected JTextField terminal;
	protected JTextField streak, time, level;
	
	protected JMenuBar menuBar;
	protected JMenu fileMenu, options, about;
	protected JMenuItem start, stop, exit;
	protected JMenuItem rules;
	
	protected boolean started = false;
	protected boolean initialized = false;
	
	//action variables
	protected int objectiveMax = 75;
	protected int maxMiss = 3;
	protected int missCount = 0;
	protected int rightCount = 0;
	protected int levelRight = 0;
	protected int bestStreak = 0;
	protected int levelNum = 0;
	protected int levelConst = 10;
	protected int levelConst2 = 2;
	protected int speedConst = 3;
	protected int speedCounter = -1;
	protected int randLength = 0;
	protected Long lastTime;

}
