package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.WebDriver;

public class NavigationTests {

    public static void main(String[] args) throws Exception {

        ChromeTest();EdgeTest();FireFoxTest();

    }

    public static void ChromeTest () throws Exception
    {
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://google.com");
        String title1= driver.getTitle();
        driver.get("https://etsy.com");
        String title2=driver.getTitle();
        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title1);
        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title2);
        Thread.sleep(3000);
        driver.close();
    }

    public static void EdgeTest() throws Exception
    {
        WebDriver driver= BrowserFactory.getDriver("edge");
        driver.get("https://google.com");
        String title1= driver.getTitle();
        driver.get("https://etsy.com");
        String title2=driver.getTitle();
        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title1);
        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title2);
        Thread.sleep(3000);
        driver.close();
    }

    public static void FireFoxTest() throws Exception
    {
        WebDriver driver = BrowserFactory.getDriver("firefox");
        driver.get("https://google.com");
        String title1= driver.getTitle();
        driver.get("https://etsy.com");
        String title2=driver.getTitle();
        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title1);
        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title2);
        Thread.sleep(3000);
        driver.close();

    }




}
