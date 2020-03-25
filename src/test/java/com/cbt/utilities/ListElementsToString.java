package com.cbt.utilities;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ListElementsToString {

    public static List<String> getTextFromWebElements(List<WebElement> elements) {
        List<String> textValues = new ArrayList<>();
        for (WebElement element : elements) {
            textValues.add(element.getText());
        }
        return textValues;
    }
    public static List<Integer> getTextFromWebElementsInt(List<WebElement> elements) {
        List<Integer> textValues = new ArrayList<>();
        for (WebElement element : elements) {
            textValues.add(Integer.parseInt(element.getText()));
        }
        return textValues;
    }
}

