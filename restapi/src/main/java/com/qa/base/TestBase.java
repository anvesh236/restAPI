package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestBase {

	public int response_status_200 = 200;
	public int response_status_404 = 404;
	public int response_status_401 = 401;
	public int response_status_201 = 201;
	public Properties prop;
	public TestBase()
	{
		prop = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
}
