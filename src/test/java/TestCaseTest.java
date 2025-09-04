import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selection.TestCasesPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.gucci.data.Sections.TEST_CASES;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class TestCaseTest extends BaseWebTest{

    @Test
    @DisplayName("Verify Test Cases Page Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_7")
    @Link("https://automationexercise.com/test_cases")
    public void verifyTestCasesPageTest() {

       open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(TEST_CASES, TestCasesPage.class)
                .waitForPageLoaded();

    }
}
