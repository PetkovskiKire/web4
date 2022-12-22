package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditBalloon extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement manufacturer;
    private WebElement submit;

    public AddOrEditBalloon(WebDriver driver) {
        super(driver);
    }

    public static BalloonPage addBalloon(WebDriver driver, String name, String description, String manufacturer) {
        get(driver, "/balloon/add-form");
        AddOrEditBalloon addOrEditBalloon = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditBalloon.name.sendKeys(name);
        addOrEditBalloon.description.sendKeys(description);
        addOrEditBalloon.manufacturer.click();
        addOrEditBalloon.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditBalloon.submit.click();
        return PageFactory.initElements(driver, BalloonPage.class);
    }

    public static BalloonPage editBalloon(WebDriver driver, WebElement editButton, String name, String description, String manufacturer) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditBalloon addOrEditBalloon = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditBalloon.name.sendKeys(name);
        addOrEditBalloon.description.sendKeys(description);
        addOrEditBalloon.manufacturer.click();
        addOrEditBalloon.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditBalloon.submit.click();
        return PageFactory.initElements(driver, BalloonPage.class);
    }


}