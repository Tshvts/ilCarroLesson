package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SearchPage extends BasePage
{
    public SearchPage(WebDriver driver)
    {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;

    @FindBy(xpath = "//a[text()=' Logout ']")
    WebElement btnLogOut;

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLogIn;

    @FindBy(xpath = "//a[text()=' Let the car work ']")
    WebElement btnLetTheCarWork;

    @FindBy(xpath = "//input[@id='city']")
    WebElement fieldCity;

    @FindBy(xpath = "//input[@id='dates']")
    WebElement fieldDates;

    public void clickBtnSignUp()
    {
        btnSignUp.click();
    }

    public void clickBtnLogOut()
    {
        btnLogOut.click();
    }

    public void clickBtnLogIn()
    {
        btnLogIn.click();
    }

    public void clickBtnLetTheCarWork()
    {
        clickWait(btnLetTheCarWork,5);
    }

    public void fillSearchCarFormWOCalendar(String city, String startDate, String endDate)
    {
        fieldCity.click();
        fieldCity.sendKeys(city);
        Actions actions = new Actions(driver);
        actions.moveToElement(fieldCity, 0, 27).pause(2000).click().perform();
        fieldDates.click();
        fieldDates.sendKeys(startDate + " - " + endDate);
        fieldDates.sendKeys(Keys.ENTER);
    }
}
