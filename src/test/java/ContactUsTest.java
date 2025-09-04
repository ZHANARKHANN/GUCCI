import com.codeborne.selenide.Condition;
import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selection.ContactUsPage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.gucci.data.Sections.CONTACT_US;
import static com.gucci.data.Sections.SIGN_IN_LOGIN;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class ContactUsTest extends BaseWebTest{



    @Test
    @DisplayName("Contact Us Form Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_6")
    @Link("https://automationexercise.com/test_cases")
    public void contactUsFormTest() {

        var contactUs = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(CONTACT_US, ContactUsPage.class);

        softly.assertThatCode(() -> contactUs.getInTouchHeader
                        .shouldHave(Condition.exactText("Get In Touch")))
                .as("'Get In Touch' header must be visible")
                .doesNotThrowAnyException();

            contactUs.fillContactUsForm(faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.educator().subjectWithNumber(),
                    faker.witcher().character())
                    .clickSubmitButton()
                    .clickAlertAccept();
        softly.assertThatCode(() -> contactUs.successHeader
                        .shouldHave(Condition.exactText("Success! Your details have been submitted successfully.")))
                .as("'Success! Your details have been submitted successfully' header must be visible")
                .doesNotThrowAnyException();
            contactUs.clickHome().waitForPageLoaded();
            softly.assertAll();






    }
}
