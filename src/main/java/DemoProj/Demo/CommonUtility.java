package DemoProj.Demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonUtility {
	public static Properties configration;
	public static Properties uitestdata;
	public static Properties api;

	public static void Initialization() throws IOException {
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java///DemoProj/Demo/Configation.properties");
		configration = new Properties();
		configration.load(fs);

		
	}
}
