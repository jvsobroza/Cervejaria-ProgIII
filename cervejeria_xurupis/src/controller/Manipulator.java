package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulator {

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("cervejeria_xurupis/properties/config.properties");
		props.load(file);
		return props;

	}

}
