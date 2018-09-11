import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


class Order extends Base {
    Order(String browser) {
        super(browser);
    }

    void openMainPage() {
        this.driver.get("http://prestashop-automation.qatestlab.com.ua");
    }

    void openAllProductsList() {
        this.action.moveToElement(this.driver.findElement(By.cssSelector("a.all-product-link")))
                .click().build().perform();
    }

    void selectRandomProduct() {
        By locator = By.cssSelector("#js-product-list > div > article > div > a");
        List<WebElement> products = this.driver.findElements(locator);
        int index = (int) (Math.random() * products.size());
        WebElement randomProduct = products.get(index);
        this.action.moveToElement(randomProduct).click().build().perform();
    }

    String getProductName() {
        WebElement element = this.driver.findElement(By.cssSelector("#main h1[itemprop=\"name\"]"));
        return element.getText();
    }

    String getProductPrice() {
        WebElement element = this.driver.findElement(By.cssSelector("#main span[itemprop=\"price\"]"));
        return element.getText();
    }

    String getProductQty() {
        WebElement element = this.driver.findElement(By.cssSelector("#main div.product-quantities > span"));
        return element.getText();
    }

    void addToCart() {
        this.action.moveToElement(this.driver.findElement(By.cssSelector("button.add-to-cart")))
                .click().build().perform();
    }

    void goToCart() {
        By locator = By.cssSelector("#blockcart-modal div.cart-content > a.btn");
        WebElement popUpButton = this.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.action.moveToElement(popUpButton).click().build().perform();
    }

    void getCartTotal() {
        this.driver.findElement(By.cssSelector("div.cart-total > span.value")).getText();
    }

    void getCartQty() {
        this.driver.findElement(By.cssSelector("#cart-subtotal-products > span.label")).getText();
    }

    void getCartProductName() {
        this.driver.findElement(By.cssSelector("div.product-line-grid > product-line-grid-body > div > a")).getText();
    }

    void placeTheOrder() {
        By locator = By.cssSelector("div.cart-summary > div.cart-detailed-actions > div > a.btn");
        this.action.moveToElement(this.driver.findElement(locator)).click().build().perform();
    }

    void fillContacts(String name, String surname, String email) {
        WebElement inputName = this.driver.findElement(By.cssSelector("input[name=\"firstname\"]"));
        WebElement inputSurname = this.driver.findElement(By.cssSelector("input[name=\"lastname\"]"));
        WebElement inputEmail = this.driver.findElement(By.cssSelector("input[name=\"email\"]"));
        this.action.moveToElement(inputName).click().sendKeys(name).build().perform();
        this.action.moveToElement(inputSurname).click().sendKeys(surname).build().perform();
        this.action.moveToElement(inputEmail).click().sendKeys(email).build().perform();
    }

    void continueOrder() {
        By locator = By.cssSelector("form button[type=\"submit\"].continue");
        this.action.moveToElement(this.driver.findElement(locator)).click().build().perform();
    }

    void fillAddress(String address, String postcode, String city) {
        WebElement inputAddress = this.driver.findElement(By.cssSelector("input[name=\"address1\"]"));
        WebElement inputPostcode = this.driver.findElement(By.cssSelector("input[name=\"postcode\"]"));
        WebElement inputCity = this.driver.findElement(By.cssSelector("input[name=\"city\"]"));
        this.action.moveToElement(inputAddress).click().sendKeys(address).build().perform();
        this.action.moveToElement(inputPostcode).click().sendKeys(postcode).build().perform();
        this.action.moveToElement(inputCity).click().sendKeys(city).build().perform();
    }

    void fillPayment() {
        By locator = By.cssSelector("div.payment-options label[for=\"payment-option-1\"]");
        this.action.moveToElement(this.driver.findElement(locator)).click().build().perform();
        locator = By.cssSelector("label[for=\"conditions_to_approve[terms-and-conditions]\"");
        this.action.moveToElement(this.driver.findElement(locator)).click().build().perform();
    }

    void finishOrder() {
        By locator = By.cssSelector("#payment-confirmation button[type=\"submit\"]");
        this.action.moveToElement(this.driver.findElement(locator)).click().build().perform();
    }
}
