package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase6 {

    WebDriver driver;

    @BeforeMethod
    public void setup()
    {
        driver= BrowserFactory.getDriver("chrome");
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void teardown (){
        if(driver!=null){
            driver.close();
            driver=null;}
    }

    @Test(description = "Test case #6")
    public void test6()
    {
        String email=driver.findElement(By.id("email")).getText();
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        Wait.wait(3);
        driver.findElement(By.cssSelector("input[name='full_name']")).sendKeys("ilhan Demirhan");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email, Keys.ENTER);
        String actual=  driver.findElement(By.tagName("h3")).getText();
        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        Assert.assertEquals(actual,expected);
        driver.navigate().to("https://www.tempmailaddress.com/");
        Wait.wait(4);
        String verifyemail = driver.findElement(By.className("from")).getText().trim();
        System.out.println(verifyemail);
        String expectedmail = "do-not-reply@practice.cybertekschool.com";
        Assert.assertEquals(verifyemail,expectedmail);
        driver.findElement(By.cssSelector("td[class='from']")).click();
        Wait.wait(3);
        String from= driver.findElement(By.cssSelector("span[id='odesilatel']")).getText();
        Assert.assertEquals(from,expectedmail);
        String subject= driver.findElement(By.cssSelector("span[id='predmet']")).getText();
        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
        Assert.assertEquals(subject,expectedSubject);
    }
}
