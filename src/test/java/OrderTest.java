import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.models.HomePage;
import ru.yandex.praktikum.models.OrderPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderTest
{
    private WebDriver driver = new ChromeDriver(); // или new FirefoxDriver()

    private String firstName, lastName, address, metro, phoneNumber;
    private String orderDate, duration, colour, comment;

    public OrderTest(String firstName, String lastName, String address, String metro, String phoneNumber,
                     String orderDate, String duration, String colour, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] setParameters(){
        return new Object[][] {
            {"Алекс", "Синий", "Введенского", "Красносельская", "79543339454", "22.05.2023", "трое суток", "серая безысходность", "Пой"},
            {"Олег", "Первый", "Карибский бассейн", "Октябрьская", "79543634454", "09.02.2023", "двое суток", "чёрный жемчуг", ""}
        };
    }

    @Test
    public void orderThroughUpperButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnUpperOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterOrderDataPageOne(firstName, lastName, address, metro, phoneNumber);
        orderPage.enterOrderDataPageTwo(orderDate, duration, colour, comment);
        orderPage.clickYesButton();
        assertThat(orderPage.getResult(), CoreMatchers.containsString("Заказ оформлен"));

    }

    @Test
    public void orderThroughMiddleButton() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        driver.manage().window().maximize();
        HomePage homePage = new HomePage(driver);
        homePage.clickOnLowerOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.enterOrderDataPageOne(firstName, lastName, address, metro, phoneNumber);
        orderPage.enterOrderDataPageTwo(orderDate, duration, colour, comment);
        orderPage.clickYesButton();
        assertThat(orderPage.getResult(), CoreMatchers.containsString("Заказ оформлен"));

    }
     @After
    public void closeDriver(){
        driver.quit();
     }
}
