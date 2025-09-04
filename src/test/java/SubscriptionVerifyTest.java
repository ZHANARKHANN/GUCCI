import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selection.CartPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.gucci.data.Sections.CART;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class SubscriptionVerifyTest extends BaseWebTest{

    @Test
    @DisplayName("Verify Subscription in home page Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_10")
    @Link("https://automationexercise.com/test_cases")
    public void verifySubscriptionInHomePageTest() {

        var homePage = open("", HomePage.class)
                .waitForPageLoaded()
                .scrollDownToFooter();
        softly.assertThatCode(() -> homePage.subscriptionHeader.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'SEARCHED PRODUCTS' - must be visible")
                .doesNotThrowAnyException();
            homePage.fillEmailInputAndClickArrowButton(firstUser.getEmail());
        softly.assertThatCode(() -> homePage.youHaveBeenSuccessfullySubscribed.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'You have been successfully subscribed!' - must be visible")
                .doesNotThrowAnyException();
        softly.assertAll();
    }

    @Test
    @DisplayName("Verify Subscription in Cart page Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_11")
    @Link("https://automationexercise.com/test_cases")
    public void verifySubscriptionInCartTest() {

        var cartPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(CART, CartPage.class)
                .scrollToTheFooter();
        softly.assertThatCode(() -> cartPage.subscriptionText.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'SUBSCRIPTION' - must be visible")
                .doesNotThrowAnyException();
            cartPage.fillEmailClickArrowButton(firstUser.getEmail());
        softly.assertThatCode(() -> cartPage.youHaveBeenSuccessfullySubscribed.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'You have been successfully subscribed!' - must be visible")
                .doesNotThrowAnyException();
        softly.assertAll();
    }
}
