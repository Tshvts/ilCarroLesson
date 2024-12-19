package pages;

import dto.UserDtoLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage
{
    public LoginPage(WebDriver driver)
    {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//button[text()='Ok']")
    WebElement btnOk;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPwd;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpMessage;


    public void clickBtnOk()
    {
        btnOk.click();
    }

    public void typeLoginForm(UserDtoLombok user)
    {
        inputEmail.sendKeys(user.getEmail());
        inputPwd.sendKeys(user.getPassword());
    }

    public void clickBtnYalla()
    {
        btnYalla.click();
    }

    public boolean MessageLogin(String text)
    {
        return isTextInElementPresent(popUpMessage, text);
    }
}
