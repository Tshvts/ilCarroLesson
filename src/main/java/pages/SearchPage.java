package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
}
