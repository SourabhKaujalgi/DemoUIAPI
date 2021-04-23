package DemoProj.Demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtility {
	public static Properties configration;
	public static Properties log;
	public static Properties api;

	public static void readProperty() throws IOException {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java///DemoProj/Demo/Configation.properties");
		configration = new Properties();
		configration.load(fs);
		
		FileInputStream fs2 = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java///DemoProj/Demo/log4j.properties");
		log = new Properties();
		log.load(fs2);

		
	}
}
