package core;
/**
 * 
 * @author mike802
 *
 * ??? - 2019
 * 
 */
import java.net.URL;

public class Properties {
	
	private String root;
	private URL logo;
	private URL company;
	private URL background;
	private URL iframe;
	
	private TypingScreen typingScreen;
	
	public Properties(String rootDir) {
		root = rootDir;
		logo = getClass().getResource("/img/logo.png");
		company = getClass().getResource("/img/company.png");
		background = getClass().getResource("/img/background.png");
		iframe = getClass().getResource("/img/company_iframe.png");
	}
	
	public String getRoot() {
		return root;
	}
	public URL getLogo() {
		return logo;
	}
	public URL getCompany() {
		return company;
	}
	public URL getBackground() {
		return background;
	}
	public URL getIframe() {
		return iframe;
	}
	
	public void setTypingScreen(TypingScreen ts) {
		typingScreen = ts;
	}
	public TypingScreen getTypingScreen() {
		return typingScreen;
	}
}