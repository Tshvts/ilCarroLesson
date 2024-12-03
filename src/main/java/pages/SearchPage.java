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

    public void clickBtnSignUp()
    {
        btnSignUp.click();
        pause(2);
    }

    public void clickBtnLogOut()
    {
        btnLogOut.click();
        pause(2);
    }

    public void clickBtnLogIn()
    {
        btnLogIn.click();
        pause(2);
    }
}
