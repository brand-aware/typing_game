package core;

import java.io.IOException;
import java.text.DecimalFormat;

import highscores.NameInput;
/**
 * 
 * @author mike802
 *
 * brand_aware
 * ??? - 2019
 * 
 */
public class Actions extends CommonScreen {
	
	protected void doEnding() throws IOException {
		double finalTime = Double.parseDouble(time.getText());
		int finalScore = calcScore(finalTime);
		doStop("", "" + finalScore);
		try {
			nameInput = new NameInput(properties.getRoot(), properties.getTypingScreen());
			nameInput.setDescending();
			desktopPane.add(nameInput);
			desktopPane.moveToFront(nameInput);
			disable();
			nameInput.init(finalScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doAddObjective() throws IOException {
		String obj = objective.getText();
		int newNumber = (int) (Math.random() * (LOWERCASE_END - LOWERCASE_START));
		newNumber += LOWERCASE_START;
		int bool = (int)(Math.random() * 10);
		if(randLength > 3) {
			if(bool < 5) {
				obj += " ";
				randLength = 0;
			}
		}else {
			randLength++;
		}
		if(objective.getText().length() + 1 > objectiveMax) {
			doEnding();
		}else {
			char newChar = (char) newNumber;
			obj += newChar;
			objective.setText(obj);
		}
	}
	
	protected void doStart() {
		//doStop();
		lastTime = System.currentTimeMillis();
		time.setText("0");
		streak.setText("0");
		started = true;
		levelNum = 1;
		speedCounter = speedConst;
		level.setText(levelNum + "");
		
		speedConst = 7;
		maxMiss = 3;
		missCount = 0;
		rightCount = 0;
		levelRight = 0;
		terminal.setEditable(true);
	}
	
	protected void doStop(String name, String score) throws IOException {
		terminal.setEditable(false);
		started = false;
		time.setText("--");
		levelNum = 1;
		level.setText("--");
		streak.setText("--");
		speedCounter = -1;
		objective.setText("");
		terminal.setText("");
		bestStreak = 0;
	}
	
	protected void doUpdateTime() {
		Long currentTime = System.currentTimeMillis();
		String timeText = time.getText();
		if(timeText.compareTo("--") == 0){
			timeText = "0";
		}
		Double previous;
		if(timeText != null) {
			previous = Double.parseDouble(timeText);
			Long difference = currentTime - lastTime;
			double newTime = previous + (difference * .001);
			DecimalFormat output = new DecimalFormat(".##");
			time.setText(output.format(newTime));
			lastTime = currentTime;
		}
	}
	
	//UTILITIES
	private void disable() {
		fileMenu.setEnabled(false);
	}
	public int calcScore(double finalTime) {
		int score = (int)finalTime;
		//System.out.println("time: " + score);
		score -= objective.getText().length();
		//System.out.println("char left: " + objective.getText().length());
		score += bestStreak;
		//System.out.println("streak: " + bestStreak);
		score += rightCount;
		//System.out.println("count: " + rightCount);
		if(score < 0) {
			return 0;
		}
		return score;
	}

}
