package ru.yandex.praktikum.models;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {

    private WebDriver driver;
    //Поле Имя
    private By firstName = By.cssSelector("[placeholder='* Имя'");
    //Поле Фамилия
    private By lastName = By.cssSelector("[placeholder='* Фамилия'");
    //Поле адрес
    private By address = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция метро
    private By metro = By.cssSelector("[placeholder='* Станция метро']");
    //Поле Телефон
    private By phone = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private By nextButton = By.xpath(".//button[text()='Далее']");
    //Кнопка принятия куки
    private By cookieButton = By.className("App_CookieButton__3cvqF");
    //Поле даты
    private By orderDate = By.cssSelector("[placeholder='* Когда привезти самокат']");
    //Поле длительности аренды
    private By daysDropdownArrow = By.className("Dropdown-arrow");
    //Комментарий для курьера
    private By commentForCourier = By.cssSelector("[placeholder='Комментарий для курьера']");

    //Кнопка Заказать
    private By finishButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    private By yesButton = By.xpath(".//button[text()='Да']");

    // Заголовок
    private By resultText = By.className("Order_ModalHeader__3FDaJ");
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение поля Имя
    public void enterFirstName(String name) {
        driver.findElement(firstName).sendKeys(name);
    }

    // Заполнение поля Фамилия
    public void enterLastName(String lName) {
        driver.findElement(lastName).sendKeys(lName);
    }

    // Заполнение поля Адрес
    public void enterAddress(String homeAddress) {
        driver.findElement(address).sendKeys(homeAddress);
    }

    // Выбор Метро по названию
    public void enterMetroStation(String station) {

        driver.findElement(metro).sendKeys(station);
        By metroName = By.xpath(".//div[@class='Order_Text__2broi']");
        driver.findElement(metroName).click();
    }

    // Заполнение поля Телефон
    public void enterPhoneNumber(String phoneNum) {
        driver.findElement(phone).sendKeys(phoneNum);
    }

    // Нажатие кнопки Далее
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    // Заполнение даты
    public void enterDate(String date) {
        WebElement dateElement = driver.findElement(orderDate);
        dateElement.clear();
        dateElement.sendKeys(date);
    }

    // Выбор длительности аренды
    public void enterDuration(String days) {
        driver.findElement(daysDropdownArrow).click();
        String xpath = ".//div[@class='Dropdown-option' and text()='" + days + "']";
        By duration = By.xpath(xpath);
        driver.findElement(duration).click();
    }

    // Выбор цвета
    public void checkColour(String color) {
        String xpath = ".//*[text()='" + color+ "']";
        driver.findElement(By.xpath(xpath)).click();
    }

    // Заполнение комментария
    public void enterComment(String commentText) {
        driver.findElement(commentForCourier).sendKeys(commentText);
    }

    public void clickFinishButton() {
        driver.findElement(finishButton).click();
    }

    //Заполнение первой порции полей
    public void enterOrderDataPageOne (String frstName, String lstName, String addrss, String val, String phnNum) {
        if (driver.findElement(cookieButton).isDisplayed()) {
            driver.findElement(cookieButton).click();
        }
        enterFirstName(frstName);
        enterLastName(lstName);
        enterAddress(addrss);
        enterMetroStation(val);
        enterPhoneNumber(phnNum);
        clickNextButton();
    }

    //Заполнение второй порции полей
    public void enterOrderDataPageTwo (String date, String duration, String colour, String comment) {
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(orderDate));
        enterDate(date);
        enterDuration(duration);
        checkColour(colour);
        enterComment(comment);
        clickFinishButton();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public String getResult() {
        return driver.findElement(resultText).getText();
    }

}
