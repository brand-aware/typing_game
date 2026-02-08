package core;
/**
 * 
 * @author mike802
 *
 * ??? - 2019
 * 
 */
import java.io.File;

public class Properties {
	
	private String root;
	private String logo;
	private String company;
	private String background;
	private String iframe;
	
	private TypingScreen typingScreen;
	
	public Properties(String rootDir) {
		root = rootDir;
		logo = root + File.separator + "img" + File.separator + "logo.png";
		company = root + File.separator + "img" + File.separator + "company.png";
		background = root + File.separator + "img" + File.separator + "background.png";
		iframe = root + File.separator + "img" + File.separator + "company_iframe.png";
	}
	
	public String getRoot() {
		return root;
	}
	public String getLogo() {
		return logo;
	}
	public String getCompany() {
		return company;
	}
	public String getBackground() {
		return background;
	}
	public String getIframe() {
		return iframe;
	}
	
	public void setTypingScreen(TypingScreen ts) {
		typingScreen = ts;
	}
	public TypingScreen getTypingScreen() {
		return typingScreen;
	}
}