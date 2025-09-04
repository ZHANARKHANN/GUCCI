import com.codeborne.selenide.Condition;
import com.gucci.entities.User;
import com.gucci.entities.UserFactory;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import com.gucci.layers.web.page.signup_login.SignUpPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.gucci.data.Sections.SIGN_IN_LOGIN;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class LoginTest extends BaseWebTest {



    @Test
    @DisplayName("Authorization with valid data")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_2")
    @Link("https://automationexercise.com/test_cases")
    public void authorisationWithValidDataTest() {


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
                .switchBetweenSection("Signup / Login", LoginPage.class);

        softly.assertThatCode(() -> loginPage.loginToYourAccountHeader.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Login to your account' - must be visible")
                .doesNotThrowAnyException();

        var homePage = loginPage.fillLoginEmail(firstUser.getEmail())
                .fillLoginPassword(firstUser.getPassword())
                .clickLoginBtn(HomePage.class);

        softly.assertThatCode(() -> homePage.loggedInAsUsernameIsVisible.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'logged In As Username' - must be visible")
                .doesNotThrowAnyException();

        var deleteAccountPage = homePage.clickDeleteAccount();

        softly.assertThatCode(() -> deleteAccountPage.accountDeletedMessage.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'ACCOUNT DELETED!' - must be visible")
                .doesNotThrowAnyException();
        softly.assertAll();


    }


    @Test
    @DisplayName("Authorization with invalid data")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_3")
    @Link("https://automationexercise.com/test_cases")
    public void authorisationWithInValidDataTest() {

        var homePage = open("", HomePage.class)
                .waitForPageLoaded();
        var loginPage = homePage.switchBetweenSection(SIGN_IN_LOGIN, LoginPage.class)
                .waitForPageLoaded()
                .fillLoginEmail(faker.internet().emailAddress())
                .fillLoginPassword(faker.internet().password())
                .clickLoginPageButtonNegativeCase();
        softly.assertThatCode(() -> loginPage.incorrectEmailOrPasswordError.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Your email or password is incorrect!' - must be visible")
                .doesNotThrowAnyException();
        softly.assertAll();
    }

    @Test
    @DisplayName("Logout User test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_4")
    @Link("https://automationexercise.com/test_cases")
    public void logoutUserTest() {

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
                .switchBetweenSection("Signup / Login", LoginPage.class);

        softly.assertThatCode(() -> loginPage.loginToYourAccountHeader.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Login to your account' - must be visible")
                .doesNotThrowAnyException();

        var homePage = loginPage.fillLoginEmail(firstUser.getEmail())
                .fillLoginPassword(firstUser.getPassword())
                .clickLoginBtn(HomePage.class);

        softly.assertThatCode(() -> homePage.loggedInAsUsernameIsVisible.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'logged In As Username' - must be visible")
                .doesNotThrowAnyException();

             homePage.clickLogoutButton();

        softly.assertThatCode(() -> loginPage.loginToYourAccountHeader.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Login to your account' - must be visible")
                .doesNotThrowAnyException();
    }
}

