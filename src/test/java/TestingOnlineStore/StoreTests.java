package TestingOnlineStore;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreTests {
    
private final int WAIT_FOR_ELEMENT_TIMEOUT = 30;
private WebDriver driver;
private WebDriverWait webDriverWait;
private Actions action;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testBuyProduct() {

        login("demouser@microsoft.com", "Pass@word1", false);
        filterItems(".NET", "T-Shirt");
        addItemToCart(".NET Foundation Sweatshirt");

        //Assert total
        assertTotal("12.00");

        proceedToCheckOut();
        completePurchase(new CheckOutDetails(), new PaymentDetails());

        //login and assert completed order
    }

    private void login(String email, String password, boolean shouldRememberMe) {

        driver.navigate().to("https://eshop-onweb-webinar-demo2.azurewebsites.net/Identity/Account/Login");
        var emailInput = waitAndFindElement(By.id("Input_email"));
        var passwordInput = waitAndFindElement(By.id("Input_Password"));
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
      
        if (shouldRememberMe) {
            var rememberMeCheckBox = waitAndFindElement(By.id("Input_RememberMe"));
            rememberMeCheckBox.click();
        }

        var loginButton = waitAndFindElement(By.tagName("button"));
        loginButton.click();
    }

    private void filterItems(String brandName, String itemType){

        var brandInput = new Select(waitAndFindElement(By.id("CatalogModel_BrandFilterApplied")));
        if (brandName != null || !brandName.isEmpty()) {

            brandInput.selectByVisibleText(brandName);
        } else {
            brandInput.selectByVisibleText("All");
        }

        var itemTypeInput = new Select(waitAndFindElement(By.id("CatalogModel_TypesFilterApplied")));
        if (itemType != null || !itemType.isEmpty()) {

            itemTypeInput.selectByVisibleText(itemType);
        } else {
            itemTypeInput.selectByVisibleText("All");
        }

        var filterButton = waitAndFindElement(By.className("esh-catalog-sent"));
        filterButton.click();
    }

    private void addItemToCart(String itemName) {
        String addToBasketXpath = String.format("//span[text()='%s']//parent::div//preceding-sibling::input", itemName);
        var addToBasketButton = waitAndFindElement(By.xpath(addToBasketXpath));
        addToBasketButton.click();
    }

    private void assertTotal (String expectedTotal) {
        var totalSection = waitAndFindElement(By.xpath("//section[text()='Total']//parent::article//following-sibling::article/section[2]"));
        validateInnerTextIs(totalSection, String.format("$ %s", expectedTotal));
    }

    private void proceedToCheckOut() {

        var basketButton = waitAndFindElement(By.xpath("//input[@value= '[ Checkout ]']"));
        basketButton.click();
    }

    private void fillPaymentDetails(PaymentDetails paymentDetails) {

        var brandInput = new Select(waitAndFindElement(By.id("PaymentDetails_Brand")));
        var cardNumberInput = waitAndFindElement(By.id("PaymentDetails_CardNumber"));
        var cardHolderNameInput = waitAndFindElement(By.id("PaymentDetails_CardHolderName"));
        var expirationMonthInput= waitAndFindElement(By.id("PaymentDetails_ExpirationMonth"));
        var expirationYearInput = waitAndFindElement(By.id("PaymentDetails_ExpirationYear"));
        var cvcInput = waitAndFindElement(By.id("PaymentDetails_CVC"));
   
        brandInput.selectByVisibleText(paymentDetails.getCardBrand().getBrandName());
        cardNumberInput.sendKeys(paymentDetails.getCardNumber());
        cardHolderNameInput.sendKeys(paymentDetails.cardHolderName());
        expirationMonthInput.sendKeys(paymentDetails.getExpirationMonth());
        expirationYearInput.sendKeys(paymentDetails.getExpirationYear());
        cvcInput.sendKeys(paymentDetails.getCvc());

    }

    private void fillCheckOutDetails(CheckOutDetails checkOutDetails) {
        var streetInput = waitAndFindElement(By.id("ShippingAddress_Street"));
        var cityInput = waitAndFindElement(By.id("ShippingAddress_City"));
        var stateInput = waitAndFindElement(By.id("ShippingAddress_State"));
        var countryInput = waitAndFindElement(By.id("ShippingAddress_Country"));
        var zipCodeInput = waitAndFindElement(By.id("ShippingAddress_ZipCode"));

        streetInput.sendKeys(checkOutDetails.getStreet());
        cityInput.sendKeys(checkOutDetails.getCity());
        stateInput.sendKeys(checkOutDetails.getState());
        countryInput.sendKeys(checkOutDetails.getCountry());
        zipCodeInput.sendKeys(checkOutDetails.getZipCode());
    }

    private void completePurchase(CheckOutDetails checkOutDetails, PaymentDetails paymentDetails) {
        fillCheckOutDetails(checkOutDetails);
        fillPaymentDetails(paymentDetails);
    }

    private void validateInnerTextIs(WebElement resultElement, String expectedText) {
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(resultElement, expectedText));
    }

    private WebElement waitAndFindElement(By locator) {
    var element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",element);
        return element;
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
