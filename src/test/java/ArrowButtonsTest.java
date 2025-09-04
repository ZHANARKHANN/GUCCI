import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.home.HomePage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class ArrowButtonsTest extends BaseWebTest{

    @Test
    @DisplayName("Verify Scroll Up using 'Arrow' button and Scroll Down functionality Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_25")
    @Link("https://automationexercise.com/test_cases")
    public void scrollUpUsingArrowButtonTest() {
        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .scrollDownToFooter();
        softly.assertThat(homePage.subscription.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'SUBSCRIPTION' should be visible")
                .isTrue();
            homePage.clickScrollUpArrowButton();
        softly.assertThat(homePage.listOffFullFledgetPracticeHeaders.stream().anyMatch(SelenideElement::isDisplayed))
                .as("One of 'Full-Fledged practice website for Automation Engineers' headers should be visible")
                .isTrue();
        softly.assertAll();
    }

    @Test
    @DisplayName("Verify Scroll Up without 'Arrow' button and Scroll Down functionality Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_26")
    @Link("https://automationexercise.com/test_cases")
    public void scrollUpWithoutArrowButtonTest() {
        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .scrollDownToFooter();
        softly.assertThat(homePage.subscription.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'SUBSCRIPTION' should be visible")
                .isTrue();
            homePage.scrollUpToHeader();
        softly.assertThat(homePage.listOffFullFledgetPracticeHeaders.stream().anyMatch(SelenideElement::isDisplayed))
                .as("One of 'Full-Fledged practice website for Automation Engineers' headers should be visible")
                .isTrue();
        softly.assertAll();
    }
}
