package org.openCart.Locator;

import org.openqa.selenium.By;

public class LocatorUtils {

    public static By getLocatorById(String id){
        return By.xpath("//*[@id='"+id+"']");
    }

    public static By getLocatorByText(String text) {
        return By.xpath("//*[contains(text(),'"+text+"')]");
    }

    public static By getLocatorByClassName(String className){
        return By.xpath("//*[contains(@class,'"+className+"')]");
    }

    public static By getDropdownLocator(String dropdownName) {
        return By.xpath("//*[contains(@class,\"dropdown\")]//*[contains(text(),'"+dropdownName+"')]");
    }

    public static By getLocatorByPlaceholder(String placeholder) {
        return By.xpath("//*[@placeholder='"+placeholder+"']");
    }

    public static By getButtonLocator(String type, String value) {
        return By.xpath("//*[@type='"+type+"' and @value='"+value+"']");
    }

    public static By getLocatorByName(String name) {
        return By.xpath("//*[@name='"+name+"']");
    }

}
