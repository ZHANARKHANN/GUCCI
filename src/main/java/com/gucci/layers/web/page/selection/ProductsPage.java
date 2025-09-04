package com.gucci.layers.web.page.selection;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.gucci.layers.web.page.BasePage;
import com.gucci.layers.web.page.signup_login.LoginPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class ProductsPage extends BasePage<ProductsPage> {

    Random random = new Random();

    public SelenideElement allProductsHeader = $x("//h2[text()='All Products']");
    public SelenideElement allProducts = $x("//div[@class = 'features_items']/div[@class='col-sm-4']");
    public SelenideElement category = $x("//p[contains(normalize-space(.), 'Category:')]");
    public SelenideElement price = $x("//span[contains(text(), 'Rs.')]");
    public SelenideElement availability = $x("//b[text()='Availability:']");
    public SelenideElement condition = $x("//b[text()='Condition:']");
    public SelenideElement brand = $x("//b[text()='Brand:']");
    public SelenideElement searchProductInput = $(By.id("search_product"));
    public SelenideElement submitSearchBtn = $(By.id("submit_search"));
    public SelenideElement brandsOnLeftSideBar = $x("//div[@class='brands-name']");
    public SelenideElement searchedProductsHeader = $x("//h2[text()='Searched Products']");
    public SelenideElement continueShoppingBtn = $x("//button[text()='Continue Shopping']");
    public SelenideElement viewCartBtn = $x("//a[@href='/view_cart']/u[text()='View Cart']");
    public SelenideElement firstProductsViewProductButton = $x("//a[@href='/product_details/1']");
    public SelenideElement featuresItemsForm = $(".features_items");
    public SelenideElement viewProductBtn1 = $x("//a[@href='/product_details/1']");
    public SelenideElement searchInput = $("input[name='search']");
    public SelenideElement searchBtn = $("input[name='search'] + button");
    public SelenideElement searchedProductName = $x("//div[@class='overlay-content']/p");
    public SelenideElement firstProduct =$x("//a[@data-product-id='1']/parent::div/parent::div[@class='single-products']");
    public SelenideElement secondProduct =$x("//a[@data-product-id='2']/parent::div/parent::div[@class='single-products']");
    public SelenideElement firstProductsAddToCartButton = $x("//div/parent::div[@class='product-overlay']//a[@data-product-id='1']");
    public SelenideElement secondProductsAddToCartButton = $x("//div/parent::div[@class='product-overlay']//a[@data-product-id='2']");
    public SelenideElement continueShoppingButton = $x("//button[text()='Continue Shopping']");
    public SelenideElement viewCartButton = $x("//u[text()='View Cart']");
    public SelenideElement subcategoriesValue = $x("//li[@class='active']");
    public SelenideElement menCategory = $x("//a[normalize-space()='Men']");
    public SelenideElement brandOnTitle = $x("//h2[@class='title text-center']");
    public SelenideElement cartButton = $x("//a[text()=' Cart']");
    public SelenideElement addYourReviewHeader = $x("//a[text()='Write Your Review']");
    public SelenideElement nameInput = $(By.id("name"));
    public SelenideElement emailInput = $(By.id("email"));
    public SelenideElement reviewInput = $(By.id("review"));
    public SelenideElement submitReviewFormButton = $(By.id("button-review"));
    public SelenideElement tyForReviewHeader = $x("//span[text()='Thank you for your review.']");


    public ElementsCollection listOfSearchedProducts = $$x("//div[@class='features_items']//div[@class='col-sm-4']");
    public ElementsCollection listOfAddToCartOfSearchedProducts = $$x("//div[@class='features_items']//div[@class='col-sm-4']//div//div[@class='product-overlay']//a");
    public ElementsCollection menSubcategories = $$x("//div[@id='Men']//li/a");
    public ElementsCollection products = featuresItemsForm.$$x("div[class='col-sm-4']");
    public ElementsCollection listOfBrandsOnLeftSideBar = $$x("//div[@class='brands-name']//a");
    public ElementsCollection listOfViewProducts = $$x("//a[text()='View Product']");



    @Step("Verify user is navigated to ALL PRODUCTS page successfully")
    @Override
    public ProductsPage waitForPageLoaded() {
        allProductsHeader.shouldBe(Condition.visible);
        return this;
    }

    @Step("Verify The products list is visible")
    public ProductsPage verifyProductsListIsVisible() {
        allProducts.shouldBe(Condition.visible);
        return this;
    }

    @Step("Click on 'View Product' of first product")
    public ProductDetailsPage clickFirstProductsViewProductButton() {
        elementManager.click(firstProductsViewProductButton);
        return Selenide.page(ProductDetailsPage.class);
    }

    @Step("Enter product name in search input and click search button")
    public ProductsPage fillSearchInput (String input) {
        elementManager.input(searchProductInput, input);
        elementManager.click(submitSearchBtn);
        return this;
    }

    @Step("Hover over first product and click 'Add to cart'")
    public ProductsPage hoverFirstProductAndClickAddToCart () {
        elementManager.hover(firstProduct);
        elementManager.click(firstProductsAddToCartButton);
        return this;
    }

    @Step("Hover over first product and click 'Add to cart'")
    public ProductsPage hoverSecondProductAndClickAddToCart () {
        elementManager.hover(secondProduct);
        elementManager.click(secondProductsAddToCartButton);
        return this;
    }

    @Step("Click 'Continue Shopping' button")
    public ProductsPage clickContinueShoppingButton () {
        elementManager.click(continueShoppingButton);
        return this;
    }

    @Step
    public CartPage clickViewCartButton () {
        elementManager.click(viewCartButton);
        return page(CartPage.class);
    }

    @Step("Click Men Category")
    public ProductsPage clickMenCategory () {
        elementManager.click(menCategory);
        return this;
    }

    @Step("Click Random Men Subcategory")
    public ProductsPage clickRandomMenSubcategory () {
        int randomOne = random.nextInt(menSubcategories.size());
        elementManager.click(menSubcategories.get(randomOne));
        return this;
    }

    @Step("Click Random Men Subcategory")
    public ProductsPage clickRandomBrandOnLeftSideBar () {
        int randomOne= random.nextInt(listOfBrandsOnLeftSideBar.size());
        elementManager.click(listOfBrandsOnLeftSideBar.get(randomOne));
        return this;
    }

    @Step("Click Add to cart for all searched products")
    public ProductsPage clickAllAddToCartButtons () {
        for (int i = 0; i < listOfSearchedProducts.size(); i++) {
            elementManager.hover(listOfSearchedProducts.get(i));
            elementManager.click(listOfAddToCartOfSearchedProducts.get(i));
            elementManager.click(continueShoppingButton);
        }
        return this;
    }

    @Step ("Click Cart Button")
    public CartPage clickCartButton () {
        elementManager.click(cartButton);
        return page(CartPage.class);
    }

    @Step("Click Random View Category")
    public ProductsPage clickRandomViewCategory () {
        int randomOne= random.nextInt(listOfViewProducts.size());
        elementManager.click(listOfViewProducts.get(randomOne));
        return this;
    }

    @Step("fill review form")
    public ProductsPage fillReviewForm (String name, String email, String review) {
        elementManager.input(nameInput, name);
        elementManager.input(emailInput, email);
        elementManager.input(reviewInput, review);
        elementManager.click(submitReviewFormButton);
        return this;
        }

}