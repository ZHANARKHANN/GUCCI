import com.gucci.entities.User;
import com.gucci.entities.UserFactory;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selection.ProductsPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import com.gucci.utils.WaitManager;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.gucci.data.Sections.SIGN_IN_LOGIN;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class RegistrationTest extends BaseWebTest {



    @Test
    @DisplayName("Register User")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_1")
    @Link("https://automationexercise.com/test_cases")
    public void registerNewUserTest() {

        open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .waitForPageLoaded()
                .fillSignUpName(firstUser.getName())
                .fillSignUpEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signupNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueButton(HomePage.class)
                .verifyLoggedAsUserNameIsVisible(firstUser.getName())
                .clickDeleteAccount()
                .waitForPageLoaded()
                .clickContinueButton();
    }

    @Test
    @DisplayName("Register User with existing email")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_5")
    @Link("https://automationexercise.com/test_cases")
    public void registerUserWithExistingEmailTest() {

        open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .waitForPageLoaded()
                .fillSignUpName(firstUser.getName())
                .fillSignUpEmail(firstUser.getEmail())
                .clickSignUpBtn(SignUpPage.class)
                .waitForPageLoaded()
                .signupNewUser(firstUser)
                .waitForPageLoaded()
                .clickContinueButton(HomePage.class)
                .verifyLoggedAsUserNameIsVisible(firstUser.getName())
                .clickLogoutButton();
        var loginPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .waitForPageLoaded()
                .fillSignUpName(firstUser.getName())
                .fillSignUpEmail(firstUser.getEmail())
                .clickSignUpButtonNegativeCase();

        softly.assertThatCode(() -> loginPage.emailAlreadyExistError.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Email Address already exist!' - must be visible")
                .doesNotThrowAnyException();
        softly.assertAll();

    }
}
