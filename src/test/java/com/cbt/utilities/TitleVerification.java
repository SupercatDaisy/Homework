package com.cbt.utilities;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.List;
public class TitleVerification {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/",
                "http://practice.cybertekschool.com/dropdown",
                "http://practice.cybertekschool.com/login");
        WebDriver driver = BrowserFactory.getDriver("chrome");
        String TestUrl="http://practice.cybertekschool.com";
        for (int i = 0; i < urls.size(); i++) {
            driver.get(urls.get(i));}
        String title1 = driver.getTitle();
        String url1 = driver.getCurrentUrl();
        driver.navigate().back();
        String url2 = driver.getCurrentUrl();
        String title2 = driver.getTitle();
        driver.navigate().back();
        String url3 = driver.getCurrentUrl();
        String title3 = driver.getTitle();
        if (title1.equals(title2) && title2.equals(title3)) {
            System.out.println("All titles are same");
        } else System.out.println("Some Titles different");
        if(url1.startsWith(TestUrl)&& url2.startsWith(TestUrl)&& url3.startsWith(TestUrl)){
            System.out.println("All Url's starts with " + TestUrl);}
        driver.close();
        }
}














