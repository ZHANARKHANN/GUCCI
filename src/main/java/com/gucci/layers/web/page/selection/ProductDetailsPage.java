package com.gucci.layers.web.page.selection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class ProductDetailsPage extends BasePage<ProductDetailsPage> {
    public SelenideElement writeYourReview = $x("//a[@href='#reviews']");
    public SelenideElement increaseQuantity = $x("//input[@id = 'quantity']");
    public SelenideElement addToCart = $x("//button[@class='btn btn-default cart']");
    public SelenideElement viewCartBtn = $x("//a[@href='/view_cart']/u[text()='View Cart']");
    public SelenideElement productsName = $x("//span[text()='Blue Top']/ancestor::h2");
    public SelenideElement productsCategory =$x("//p[text()='Category: Women > Tops']");
    public SelenideElement productsPrice = $x("//span[text()='Rs. 500']");
    public SelenideElement productsAvailability = $x("//b[text()='Availability:']/..");
    public SelenideElement productsCondition = $x("//b[text()='Condition:']/..");
    public SelenideElement productsBrand = $x("//b[text()='Brand:']/..");
    public SelenideElement productInformation = $x("//div[@class='product-information']");
    public SelenideElement productsQuantity = $(By.id("quantity"));
    public SelenideElement addToCartButton = $x("//button[normalize-space()='Add to cart']");
    public SelenideElement viewCartText = $x("//u[text()='View Cart']/..");


    @Override
    public ProductDetailsPage waitForPageLoaded() {
        writeYourReview.shouldHave(Condition.exactText("Write Your Review"));
        return this;
    }

    @Step("View Product Click")
    public CartPage clickViewCart () {
        viewCartText.shouldBe(Condition.visible, Duration.ofSeconds(10));
        elementManager.click(viewCartBtn);
        return page(CartPage.class);
    }

    @Step ("Add product to cart")
    public ProductDetailsPage clickAddToCartButton () {
        elementManager.click(addToCartButton);
        return this;
    }

    @Step ("Change Quantity of product")
    public ProductDetailsPage changeQuantity (String quantity) {
        elementManager.clearAndType(productsQuantity, quantity);
        return this;
    }

    @Step("Increase quantity to {0}")
    public ProductDetailsPage increaseQuantity(String quantity) {
        increaseQuantity.sendKeys(Keys.CONTROL + "a");
        increaseQuantity.sendKeys(Keys.DELETE);
        elementManager.input(increaseQuantity, quantity);
        return this;
    }

    @Step("verify product btn is open")
    public ProductDetailsPage clickAddToCart() {
        elementManager.click(addToCart);
        return this;
    }


}
