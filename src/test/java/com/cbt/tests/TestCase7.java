package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase7 {
    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        driver= BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void teardown (){
        if(driver!=null){
            driver.close();
            driver=null;}
    }

    @Test(description = "Test case #7")
    public void test7()
    {
        driver.findElement(By.linkText("File Upload")).click();
        driver.findElement(By.id("file-upload")).sendKeys("C:\\Users\\ilhan\\Desktop\\BankAccountInfo.txt");
        Wait.wait(2);
        driver.findElement(By.id("file-submit")).click();
        Wait.wait(2);
        String expected = "BankAccountInfo.txt";
        String actual= driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(expected,actual);
    }


}
