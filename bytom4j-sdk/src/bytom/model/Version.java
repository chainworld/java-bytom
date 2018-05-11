package bytom.model;

/**
 * 
 * @author https://github.com/JackyKen
 *
 */
public class Version {
	private final static String VERSION = "1.0.0";
	private final static String TITLE = "bytom4jV1";

	public static String getVersion() {
		return VERSION;
	}

	public static void main(String[] args) {
		System.out.println(TITLE + " " + VERSION);
	}
}
