package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.ListElementsToString;
import com.cbt.utilities.VerifyLinks;
import com.cbt.utilities.Wait;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class HomeWork4 {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
    }


    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }


    @Test
    public void Days() {
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        int count = 0;
        List<WebElement> days = driver.findElements(By.xpath("//input[@type='checkbox']"));
        List<WebElement> names = driver.findElements(By.tagName("label"));
        do {
            for (int i = 0; i < days.size(); i++) {
                if (days.get(i).isEnabled()) {
                    int random = (int) (Math.random() * 5);
                    System.out.println(random);
                    days.get(random).click();
                    // From utilities Package List To String method
                    System.out.println(ListElementsToString.getTextFromWebElements(names).get(random));
                    Wait.wait(1);
                    days.get(random).click();
                    if (random == 4) {
                        count++;
                    }
                }
            }
        }
        while (count < 3);
    }

    @Test
    public void TodaysDate() {
        driver.get("http://practice.cybertekschool.com/dropdown");
        Select year = new Select(driver.findElement(By.id("year")));
        String value_year = year.getFirstSelectedOption().getText();
        Select month = new Select(driver.findElement(By.id("month")));
        String value_month = month.getFirstSelectedOption().getText();
        Select day = new Select(driver.findElement(By.id("day")));
        String value_day = day.getFirstSelectedOption().getText();
        String actual_date = value_year + " " + value_month + " " + value_day;
        String expected = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MMMM dd"));
        Assert.assertEquals(actual_date, expected);
    }


    @Test
    public void YearsMonthsDays() {
        boolean check = false;
        driver.get("http://practice.cybertekschool.com/dropdown");
        Select year = new Select(driver.findElement(By.id("year")));
        List<WebElement> years = year.getOptions();
        int random = (int) (Math.random() * (years.size()));
        year.selectByIndex(random);
        Wait.wait(5);
        Select month = new Select(driver.findElement(By.id("month")));
        List<WebElement> months = month.getOptions();
        month.selectByValue("0");
        Wait.wait(5);
        Select day = new Select(driver.findElement(By.id("day")));
        List<WebElement> days = day.getOptions();
        Assert.assertEquals(days.size(), 31);
        for (int i = 0; i < months.size(); i++) {
            month.selectByIndex(i);
            Select newday = new Select(driver.findElement(By.id("day")));
            List<WebElement> newdays = newday.getOptions();
            if (i == 0 || i == 2 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11) {
                Assert.assertEquals(newdays.size(), 31);
            } else if (i == 3 || i == 5 || i == 8 || i == 10) {
                Assert.assertEquals(newdays.size(), 30);
            } else { //IF THE SELECTED MONTH IS FEBRUARY Let's check random year was leap or not
                for (int j = 0; j < years.size(); j++) {
                    if (Integer.parseInt(years.get(random).getText()) % 4 != 0) {
                        check = false;
                    } else if (Integer.parseInt(years.get(random).getText()) % 400 == 0) {
                        check = true;
                    } else if (Integer.parseInt(years.get(random).getText()) % 100 == 0) {
                        check = false;
                    } else {
                        check = true;
                    }
                    if (check)//IF THE YEAR IS LEAP YEAR CHECK FEB HAS 29 DAYS
                    {
                        Assert.assertEquals(newdays.get(newdays.size() - 1).getText(), "29");
                    } else if (!check) //IF THE YEAR IS NOT LEAP CHECK FEB HAS 28 DAYS
                    {
                        Assert.assertEquals(newdays.get(newdays.size() - 1).getText(), "28");
                    }
                }

            }

        }
    }

    @Test
    public void DepartmentsSort() {
        driver.get("https://www.amazon.com");
        Select alldpts = new Select(driver.findElement(By.cssSelector("select[id='searchDropdownBox']")));
        String defaultVal = driver.findElement(By.cssSelector("select[id='searchDropdownBox']")).getAttribute("value");
        String actual = driver.findElement(By.cssSelector("div[data-value='" + defaultVal + "'")).getText();
        String expected = "All";
        Assert.assertEquals(actual, expected);
        List<WebElement> alldptsList = alldpts.getOptions();
        List<String> Dropdown = ListElementsToString.getTextFromWebElements(alldptsList);
        boolean check = Dropdown.stream().sorted().collect(Collectors.toList()).equals(Dropdown);
        Assert.assertTrue(check);

    }


    @Test
    public void MainDepartments() {
        driver.get("https://www.amazon.com/gp/site-directory");
        Select alldpts = new Select(driver.findElement(By.cssSelector("select[id='searchDropdownBox']")));
        List<WebElement> alldptsList = alldpts.getOptions();
        List<WebElement> mainDpts = driver.findElements(By.xpath("//h2[@class='fsdDeptTitle']"));
        List<String> alldptsString = ListElementsToString.getTextFromWebElements(alldptsList);
        List<String> mainDptsString = ListElementsToString.getTextFromWebElements(mainDpts);
        mainDptsString.retainAll(alldptsString);
        Assert.assertTrue(alldptsString.containsAll(mainDptsString));
    }

    @Test
    public void Links() {
        driver.get("https://www.w3schools.com/");
        List<WebElement> TagHeuer = driver.findElements(By.tagName("a"));
        for (int i = 0; i < TagHeuer.size(); i++) {
            if (TagHeuer.get(i).isDisplayed()) {
                System.out.println("Text of Element is: " + TagHeuer.get(i).getText());
                System.out.println("Href of element is: " + TagHeuer.get(i).getAttribute("href"));
            }
        }

    }


    @Test
    public void ValidLinks() {
        driver.get("https://www.selenium.dev/documentation/en/");
        List<WebElement> Tags = driver.findElements(By.tagName("a"));
        for (int i = 0; i < Tags.size(); i++) {
            if (Tags.get(i).getAttribute("href") != null) {
                WebElement link = Tags.get(i);
                String url = link.getAttribute("href");
                VerifyLinks.verifyLinkValid(url); //Utilities Package Public Static Method Check
            }
        }
    }


    @Test
    public void Cart() {
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);
        Wait.wait(3);
        List<WebElement> results = driver.findElements(By.xpath("//span[@class='a-size-base-plus a-color-base a-text-normal']"));
        int random = (int) (Math.random() * results.size());
        String actualname = results.get(random).getText();
        List<WebElement> priceWhole = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        List<WebElement> priceDec = driver.findElements(By.xpath("//span[@class='a-price-fraction']"));
        String price1 = priceWhole.get(random).getText();
        String price2 = priceDec.get(random).getText();
        String actualPrice = "$" + price1 + "." + price2;
        results.get(random).click();
        Wait.wait(3);
        String actualQty = driver.findElement(By.xpath("//span[@id='a-autoid-0-announce']/span[2]")).getText();
        Assert.assertEquals(actualQty, "1");
        String expectedName = driver.findElement(By.xpath("//span[@id='productTitle']")).getText().trim();
        Assert.assertEquals(actualname, expectedName);
        Wait.wait(2);
        String expectedPrice = driver.findElement(By.cssSelector("#price_inside_buybox")).getText();
        Assert.assertEquals(actualPrice, expectedPrice);
        Assert.assertTrue(driver.findElement(By.id("add-to-cart-button")).isDisplayed());
    }

    @Test
    public void OptimusPrime() {
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);
        Wait.wait(2);
        String Actualname = driver.findElement(By.xpath("//i[@aria-label='Amazon Prime']/../parent::span/../../preceding-sibling::div[3]/h2/a/span")).getText();
        driver.findElement(By.xpath("//i[@class='a-icon a-icon-checkbox']")).click();
        Wait.wait(5);
        String ExpectedName = driver.findElement(By.xpath("//h2//a//span")).getText();
        //String ExpectedName = driver.findElement(By.xpath("//h2[@class='a-size-mini a-spacing-none a-color-base s-line-clamp-4']")).getText();
        Assert.assertEquals(Actualname, ExpectedName);
        driver.findElement(By.xpath("//span[contains(text(),'Brand')]/../following-sibling::ul/li[last()]/span/a/div/label/i")).click();
        Wait.wait(10);
        String ExpectedName2 = driver.findElement(By.xpath("//h2//a//span")).getText();
        Assert.assertNotEquals(Actualname, ExpectedName2);
    }

    @Test
    public void MoreSpoons() {
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);
        Wait.wait(2);
        List<WebElement> brandNames = driver.findElements(By.xpath("//ul[@aria-labelledby='p_89-title']/li"));
        List<String> Actual = ListElementsToString.getTextFromWebElements(brandNames);
        driver.findElement(By.xpath("//i[@class='a-icon a-icon-checkbox']")).click();
        Wait.wait(3);
        List<WebElement> brandNames2 = driver.findElements(By.xpath("//ul[@aria-labelledby='p_89-title']/li"));
        List<String> Expected = ListElementsToString.getTextFromWebElements(brandNames2);
        Assert.assertEquals(Expected, Actual);
    }


    @Test
    public void CheapSpoons()
    {
        driver.get("https://amazon.com");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("wooden spoon", Keys.ENTER);
        Wait.wait(2);
        String url = driver.findElement(By.xpath("//*[.='Wood']/..")).getAttribute("href");
        Wait.wait(2);
        driver.navigate().to(url);
        Wait.wait(3);
        driver.findElement(By.xpath("//input[@type='text' and @placeholder='Max']")).sendKeys("25",Keys.ENTER);
        Wait.wait(3);
        List<WebElement> resultPrices = driver.findElements(By.xpath("//span[@class='a-price-whole']"));
        for (int i = 0; i <resultPrices.size() ; i++) {
            Assert.assertTrue(Integer.parseInt(resultPrices.get(i).getText())<25);
        }
    }



}