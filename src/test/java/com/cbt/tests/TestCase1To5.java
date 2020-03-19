package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCase1To5 {

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


    @Test(description = "Test case #1")

    public void test1()
    {
        driver.findElement(By.linkText("Registration Form")).click();
        Wait.wait(3);
        driver.findElement(By.cssSelector("input[name='birthday']")).sendKeys("“wrong_dob");
        Wait.wait(1);
        Assert.assertTrue(driver.findElement(By.xpath("//small[text()='The date of birth is not valid']")).isDisplayed());
    }


    @Test(description = "Test case #2")
    public void test2()
    {
        driver.findElement(By.linkText("Registration Form")).click();
        Wait.wait(3);
        String actual = driver.findElement(By.xpath("//input[@type='checkbox' and @id='inlineCheckbox1']/following-sibling::label")).getText();
        String expected = "C++";
        Assert.assertEquals(actual,expected);
        String actual2 = driver.findElement(By.xpath("//input[@type='checkbox' and @id='inlineCheckbox2']/following-sibling::label")).getText();
        String expected2= "Java";
        Assert.assertEquals(actual2,expected2);
        String actual3 = driver.findElement(By.xpath("//input[@type='checkbox' and @id='inlineCheckbox3']/following-sibling::label")).getText();
        String expected3= "JavaScript";
        Assert.assertEquals(actual3,expected3);
    }


    @Test(description = "Test case #3")
    public void test3()
    {
        driver.findElement(By.linkText("Registration Form")).click();
        Wait.wait(3);
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("a");
        Wait.wait(2);
        Assert.assertTrue(driver.findElement(By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']")).isDisplayed());
    }

    @Test(description = "Test case #4")
    public void test4()
    {
        driver.findElement(By.linkText("Registration Form")).click();
        Wait.wait(3);
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("a");
        Wait.wait(2);
        Assert.assertTrue(driver.findElement(By.xpath("//small[text()='The last name must be more than 2 and less than 64 characters long']")).isDisplayed());
    }

    @Test(description = "Test case #5")
    public void test5()
    {
        driver.findElement(By.linkText("Registration Form")).click();
        Wait.wait(3);
        driver.findElement(By.name("firstname")).sendKeys("ilhan");
        driver.findElement(By.name("lastname")).sendKeys("Demirhan");
        driver.findElement(By.name("username")).sendKeys("ilhandmeirhan");
        driver.findElement(By.name("email")).sendKeys("ilhan@cybertek.com");
        driver.findElement(By.name("password")).sendKeys("CyberTekAlumni2019");
        driver.findElement(By.name("phone")).sendKeys("234-123-1231");
        driver.findElement(By.cssSelector("input[value='male']")).click();
        driver.findElement(By.name("birthday")).sendKeys("12/05/1989");
        Select departmentSelect = new Select(driver.findElement(By.name("department")));
        departmentSelect.selectByVisibleText("Department of Engineering");
        Select jobTitleSelect = new Select(driver.findElement(By.name("job_title")));
        jobTitleSelect.selectByVisibleText("SDET");
        driver.findElement(By.xpath("//label[text()='Java']/preceding-sibling::input")).click();
        driver.findElement(By.id("wooden_spoon")).click();
        Wait.wait(5);
        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();
        Assert.assertEquals(actual, expected);
    }

}
