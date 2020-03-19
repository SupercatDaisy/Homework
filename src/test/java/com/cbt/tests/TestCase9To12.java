package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

public class TestCase9To12 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    @DataProvider
    public Object [][] StatusCode()
    {
return new Object[][]{{"200"},{"301"},{"404"},{"500"}};
    }
    @Test(description = "Test case #9 to 12" , dataProvider = "StatusCode")
    public void test9(String codeNo) {
        driver.findElement(By.linkText("Status Codes")).click();
        driver.findElement(By.linkText(codeNo)).click();
        Wait.wait(2);
        String expected= "This page returned a " +codeNo+ " status code.";
        String actual = driver.findElement(By.tagName("p")).getText().substring(0,39).trim();
        Assert.assertEquals(expected,actual);


    }
}