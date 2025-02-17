package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import highscores.HighScores;
import highscores.IBoardOutline;
import highscores.hsProperties;
/**
 * 
 * @author mike802
 *
 * brand_aware
 * ??? - 2019
 * 
 */
public class TypingScreen extends Actions implements IBoardOutline{
	
	private MenuHandler menuHandler;
	private KeyHandler keyHandler;
	
	public TypingScreen(Properties props){
		properties = props;
		properties.setTypingScreen(this);
		typingScreen = new JFrame("typing_game");
		
	}
	
	private void createPage() {
		typingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		typingScreen.setResizable(false);
		Image company = Toolkit.getDefaultToolkit().getImage(properties.getCompany());
		typingScreen.setIconImage(company);
		
		typingScreen.setPreferredSize(new Dimension(800, 600));
		typingScreen.setLocation(200, 100);
		desktopPane = new JDesktopPane();
		menuHandler = new MenuHandler();
		keyHandler = new KeyHandler();
		
		background = new JLabel();
		ImageIcon backgroundIcon = new ImageIcon(properties.getBackground());
		background.setIcon(backgroundIcon);
		background.setBounds(0, 0, WIDTH, HEIGHT);
		
		logo = new JLabel();
		ImageIcon logoIcon = new ImageIcon(properties.getLogo());
		logo.setIcon(logoIcon);
		int currentX = (WIDTH / 2) - (LOGO_WIDTH / 2) - 15;
		int currentY = 15;
		logo.setBounds(currentX, currentY, LOGO_WIDTH, LOGO_HEIGHT);
		logo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("file");
		about = new JMenu("about");
		
		start = new JMenuItem("start");
		start.addActionListener(menuHandler);
		stop = new JMenuItem("stop");
		stop.addActionListener(menuHandler);
		exit = new JMenuItem("exit");
		exit.addActionListener(menuHandler);
		rules = new JMenuItem("rules");
		rules.addActionListener(menuHandler);
		
		fileMenu.add(start);
		fileMenu.add(stop);
		fileMenu.add(exit);
		about.add(rules);
		menuBar.add(fileMenu);
		menuBar.add(about);
		typingScreen.setJMenuBar(menuBar);		
		
		objective = new JTextArea();
		currentY += 15 + 30 + LOGO_HEIGHT;
		currentX = 30 + 10;
		objective.setBounds(currentX, currentY, OBJECTIVE_WIDTH, OBJECTIVE_HEIGHT);
		objective.setEditable(false);
		objective.setLineWrap(true);
		objective.setFont(new Font("Serif", Font.PLAIN, 32));
		objective.setBorder(BorderFactory.createLineBorder(Color.black));
		
		terminal = new JTextField();
		currentY += 15 + 30 + OBJECTIVE_HEIGHT;
		currentX = 30 + 15;
		terminal.setBounds(currentX, currentY, TERMINAL_WIDTH, TERMINAL_HEIGHT);
		terminal.setEditable(false);
		terminal.setBorder(BorderFactory.createLineBorder(Color.black));
		terminal.addKeyListener(keyHandler);
		
		int statStartY = currentY;
		currentX += TERMINAL_WIDTH + STAT_SPACER + 30;
		streakLabel = new JLabel("streak");
		streakLabel.setBounds(currentX, currentY, STREAK_LABEL_WIDTH, STREAK_LABEL_HEIGHT);
		
		currentY += STREAK_HEIGHT + 15;
		streak = new JTextField("--");
		streak.setBounds(currentX, currentY, STREAK_WIDTH, STREAK_HEIGHT);
		streak.setEditable(false);
		
		currentX += STREAK_LABEL_WIDTH + STAT_SPACER;
		currentY = statStartY;
		timeLabel = new JLabel("time");
		timeLabel.setBounds(currentX, currentY, TIME_LABEL_WIDTH, TIME_LABEL_HEIGHT);
		
		currentY += TIME_LABEL_HEIGHT + 15;
		time = new JTextField("--");
		time.setBounds(currentX, currentY, TIME_WIDTH, TIME_HEIGHT);
		time.setEditable(false);
		
		currentX += TIME_LABEL_WIDTH + STAT_SPACER;
		currentY = statStartY;
		levelLabel = new JLabel("level");
		levelLabel.setBounds(currentX, currentY, LEVEL_LABEL_WIDTH, LEVEL_LABEL_HEIGHT);
		
		currentY += LEVEL_LABEL_HEIGHT + 15;
		level = new JTextField("--");
		level.setBounds(currentX, currentY, LEVEL_WIDTH, LEVEL_HEIGHT);	
		level.setEditable(false);
		
		desktopPane.add(background);
		desktopPane.add(logo);
		desktopPane.add(objective);
		desktopPane.add(terminal);
		desktopPane.add(streakLabel);
		desktopPane.add(streak);
		desktopPane.add(timeLabel);
		desktopPane.add(time);
		desktopPane.add(levelLabel);
		desktopPane.add(level);
		
		desktopPane.moveToBack(background);
		desktopPane.moveToFront(logo);
		desktopPane.moveToFront(objective);
		desktopPane.moveToFront(terminal);
		desktopPane.moveToFront(streakLabel);
		desktopPane.moveToFront(streak);
		desktopPane.moveToFront(timeLabel);
		desktopPane.moveToFront(time);
		desktopPane.moveToFront(levelLabel);
		desktopPane.moveToFront(level);
		
		typingScreen.add(desktopPane);
		typingScreen.pack();
		typingScreen.setVisible(true);		
	}
	
	private class MenuHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == start) {
				doStart();
			}else if(event.getSource() == stop) {
				try {
					double finalTime = Double.parseDouble(time.getText());
					int finalScore = calcScore(finalTime);
					doStop("", "" + finalScore);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else if(event.getSource() == exit) {
				System.exit(0);
			}else if(event.getSource() == rules) {
				doAbout();
			}
		}
	}
	
	private class KeyHandler implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {	}

		@Override
		public void keyReleased(KeyEvent arg0) { }

		@Override
		public void keyTyped(KeyEvent event) {
			if(started) {
				terminal.setText(event.getKeyChar() + "");
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int charCode = (int) event.getKeyChar();
				//boolean letter = checkKeyLetterEvent(event);
				String objText = objective.getText();
				String target = objText.substring(0, 1);
				char targetChar = target.toCharArray()[0];
				if(charCode == (int)targetChar) {
					int streakTxt = Integer.parseInt(streak.getText());
					streakTxt++;
					streak.setText(streakTxt + "");
					if(streakTxt > bestStreak) {
						bestStreak = streakTxt;
					}
					rightCount++;
					levelRight++;
					objText = objText.substring(1, objText.length());
					objective.setText(objText);
					if(isLevel()) {
						int currentLevel = Integer.parseInt(level.getText());
						level.setText((currentLevel + 1) + "");
						levelRight = 0;
						missCount = 0;
					}
				}else {
					missCount++;
					streak.setText("0");
					if(missCount == maxMiss) {
						try {
							doEnding();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				terminal.setText("");
			}
		}
	}
	
	private boolean isLevel() {
		int currentLevel = Integer.parseInt(level.getText());
		int target = levelConst + ((levelConst / levelConst2) * currentLevel);
		
		if(levelRight > target) {
			if(speedConst > 1) {
				speedConst--;
			}else if(maxMiss > 1) {
				maxMiss--;
			}
			return true;
		}
		return false;
	}
	
	public void doMove() throws IOException {
		if(started) {
			doUpdateTime();
		}
		if(speedCounter == 0) {
			speedCounter = speedConst;
			doAddObjective();
		}else {
			speedCounter--;
		}
	}
	
	public void doAbout() {
		JOptionPane.showMessageDialog(
				typingScreen, 
				"Scoring guide:\n\n"
				+"Level target = ((level constant1 / levelConstant2) * current level)\n"
				+"Object Max = 75\n"
				+"miss count (per level) = 3\n"
				+"speed counter = -1\n"
				+"average word length = 5\n"
				+"speed const = 3", 
				"game_rules", 
				JOptionPane.INFORMATION_MESSAGE, 
				new ImageIcon(properties.getCompany()));
	}
	
	public void init() {
		if(!initialized) {
			createPage();
			initialized = true;
		}
	}

	@Override
	public void enable() {
		fileMenu.setEnabled(true);
		
	}

	@Override
	public void initHighScores(hsProperties hsProps) {
		HighScores highScores = new HighScores(this);
		try {
			highScores.setSmallBoard1();
			highScores.init(hsProps);
			highScores.setSmallBoard2();
			desktopPane.add(highScores);
			desktopPane.moveToFront(highScores);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initHighScores(String name, String rank, int score, hsProperties hsProps) {
		HighScores highScores = new HighScores(this);
		try {
			//balogger.setName(name);
			highScores.setSmallBoard1();
			highScores.init(name, rank, score, hsProps);
			highScores.setSmallBoard2();
			desktopPane.add(highScores);
			desktopPane.moveToFront(highScores);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(typingScreen, "IOException", "???", JOptionPane.ERROR_MESSAGE);
		}	
		
	}
}
