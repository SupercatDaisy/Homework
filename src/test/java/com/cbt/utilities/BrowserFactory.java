package com.cbt.utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
public class BrowserFactory {
    public static WebDriver getDriver(String type) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows") && type.equalsIgnoreCase("safari"))
            return null;
        else if (os.contains("mac") && type.equalsIgnoreCase("edge"))
            return null;
        else if (type.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (type.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else  {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }
    }

}