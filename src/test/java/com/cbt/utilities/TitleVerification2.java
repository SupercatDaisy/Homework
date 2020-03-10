package com.cbt.utilities;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.List;
public class TitleVerification2 {
    public static void main(String[] args) {
        List<String> urls = Arrays.asList("https://luluandgeorgia.com",
                "https://wayfair.com/",
                "https://walmart.com, ",
                "https://westelm.com/");
        WebDriver driver = BrowserFactory.getDriver("chrome");
        int count = 0;
        for (int i = 0; i < urls.size(); i++) {
            driver.get(urls.get(i));
            String title = driver.getTitle().replace(" ", "").toLowerCase();
            String url = driver.getCurrentUrl();
            if(url.contains(title))
                System.out.println(url + " does contain title");
            else
                System.out.println(url + " does not contain title");
        }
        driver.close();
    }
    }
