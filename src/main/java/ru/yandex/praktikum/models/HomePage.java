package ru.yandex.praktikum.models;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    //Кнопка заказать верхняя
    private By upperOrder = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    //Кнопка заказать нижняя
    private By lowerOrder = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    // Cookie
    private By cookieButton = By.className("App_CookieButton__3cvqF");
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnUpperOrderButton() {
        driver.findElement(upperOrder).click();
    }

    public void clickOnLowerOrderButton() {
        WebElement element = driver.findElement(lowerOrder);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                element);
        element.click();
    }
    public void clickQuestion(int numOfQuestion) {
        By question = By.id("accordion__heading-" + numOfQuestion);
        WebElement element = driver.findElement(question);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
                element);
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(question));
        element.click();
    }
    public String getAnswerText(int numOfQuestion) {
        By answerEl = By.xpath(".//div[@id='accordion__panel-" + numOfQuestion + "']/p");
        String answer = driver.findElement(answerEl).getText();
        return answer;
    }

    public String getAnswersFromScratch(int numOfQuestion) {
        WebElement cookieElement = driver.findElement(cookieButton);
        if (cookieElement.isEnabled()) {
            cookieElement.click();
        }
        clickQuestion(numOfQuestion);
        return getAnswerText(numOfQuestion);
    }

}
