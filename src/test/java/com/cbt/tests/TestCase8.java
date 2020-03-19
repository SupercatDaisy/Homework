package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase8 {

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

    @Test(description = "Test case #8")
    public void test8()
    {
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.cssSelector("input[type='button']")).click();
        String Expectedmsg = "You selected: United States of America";
        String Actualmsg = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(Expectedmsg,Actualmsg);

    }


}
