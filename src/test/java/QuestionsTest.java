import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.models.HomePage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsTest {

    private WebDriver driver = new ChromeDriver(); // или new FirefoxDriver()
    private static String[]  answers = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    private int numOfQuestion;
    private String answer;

    public QuestionsTest(int numOfQuestion, String answer) {
        this.numOfQuestion = numOfQuestion;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] setParameters(){
        return new Object[][] {
                {0, answers[0]},
                {1, answers[1]},
                {2, answers[2]},
                {3, answers[3]},
                {4, answers[4]},
                {5, answers[5]},
                {6, answers[6]},
                {7, answers[7]}
        };
    }

    @Test
    public void checkQuestionFirst() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage homePage = new HomePage(driver);
        String answerFirst = homePage.getAnswersFromScratch(numOfQuestion);
        assertEquals("Неправильный ответ на вопрос", answer, answerFirst);
    }

    @After
    public void closeDriver() {
        driver.quit();
    }
}
