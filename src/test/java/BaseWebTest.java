import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.gucci.data.Sections;
import com.gucci.entities.User;
import com.gucci.entities.UserFactory;
import com.gucci.layers.web.manager.WebDriverManager;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.selenide.AllureSelenide;
import net.datafaker.Faker;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.gucci.config.ConfigurationManager.getAppConfig;

public class BaseWebTest {

    public Faker faker = new Faker();
    public Sections sections = new Sections();
    public SoftAssertions softly = new SoftAssertions();
    public User firstUser = UserFactory.createRendomUser();


    private final String BASE_URL = getAppConfig().baseUrl();

    public <T> T open(String endPoint, Class<T> clazz) {
        return Selenide.open(String.format("%s/%s", BASE_URL, endPoint), clazz);
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.configureBasicWebDriver();
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));
    }

    @AfterAll
    public static void tearDown () {
        SelenideLogger.removeListener("AllureSelenide");
    }
}
