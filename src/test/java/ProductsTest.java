import com.gucci.layers.web.page.home.HomePage;
import com.gucci.layers.web.page.selection.ProductsPage;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.gucci.data.Sections.PRODUCTS;

@Tag(Tags.SMOKE)
@Tag(Tags.WEB)
public class ProductsTest extends BaseWebTest {

    @Test
    @DisplayName("Verify All Products and product detail page Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_8")
    @Link("https://automationexercise.com/test_cases")
    public void verifyAllProductsTest() {

        var productDetailsPage = open("", HomePage.class)
                .waitForPageLoaded()
                .switchBetweenSection(PRODUCTS, ProductsPage.class)
                .waitForPageLoaded()
                .verifyProductsListIsVisible()
                .clickFirstProductsViewProductButton()
                .waitForPageLoaded();


        softly.assertThatCode(() -> productDetailsPage.productsCategory.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Products category' - must be visible")
                .doesNotThrowAnyException();
        softly.assertThatCode(() -> productDetailsPage.productsPrice.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Products price' - must be visible")
                .doesNotThrowAnyException();
        softly.assertThatCode(() -> productDetailsPage.productsAvailability.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Products availability' - must be visible")
                .doesNotThrowAnyException();
        softly.assertThatCode(() -> productDetailsPage.productsCondition.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Products condition' - must be visible")
                .doesNotThrowAnyException();
        softly.assertThatCode(() -> productDetailsPage.productsBrand.shouldBe(visible, Duration.ofSeconds(10)))
                .as("'Products brand' - must be visible")
                .doesNotThrowAnyException();
        softly.assertAll();

    }

    @Test
    @DisplayName(" View Category Products Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_18")
    @Link("https://automationexercise.com/test_cases")
    public void viewCategoryProductsTest() {

        var homePage = open("", HomePage.class);
        softly.assertThat(homePage.categoryInLeftSideBar.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'CATEGORY' in left side bar should be visible")
                .isTrue();
        var productsPage =  homePage.clickWomenCategory()
                    .clickRandomWomenSubcategory();
        softly.assertThat(productsPage.subcategoriesValue.shouldBe(visible, Duration.ofSeconds(10))
                .getText())
                .as("Checking subcategory`s value")
                .isIn("Women > Saree", "Women > Tops", "Women > Dress");
        productsPage.clickMenCategory()
                .clickRandomMenSubcategory();
        softly.assertThat(productsPage.subcategoriesValue.shouldBe(visible, Duration.ofSeconds(10))
                        .getText())
                .as("Checking subcategory`s value")
                .isIn("Men > Jeans", "Men > Tshirts");
        softly.assertAll();

    }

    @Test
    @DisplayName("Add review on product Test")
    @Owner("Zhanarkhan")
    @Tag("Test_Case_21")
    @Link("https://automationexercise.com/test_cases")
    public void addReviewOnProductTest() {
        var productsPage = open("", HomePage.class)
                .switchBetweenSection(PRODUCTS, ProductsPage.class)
                .waitForPageLoaded()
                .clickRandomViewCategory();
        softly.assertThat(productsPage.addYourReviewHeader.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'Write Your Review' header should be visible")
                .isTrue();
            productsPage.fillReviewForm(firstUser.getName(), firstUser.getEmail(), faker.text().text(100));
        softly.assertThat(productsPage.tyForReviewHeader.shouldBe(visible, Duration.ofSeconds(10)).isDisplayed())
                .as("'Thank you for your review.' header should be visible")
                .isTrue();
        softly.assertAll();
    }

}
