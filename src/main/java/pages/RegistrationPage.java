package pages;

import dto.UserDtoLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage
{
    public RegistrationPage(WebDriver driver)
    {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "lastName")
    WebElement inputLastName;

    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPwd;

    @FindBy(xpath = "//div[@class='checkbox-container']")
    WebElement checkbox;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpMessage;

    @FindBy(xpath = "//div[text()='Wrong email format']")
    WebElement highlightedFieldEmail;

    @FindBy(xpath = "//div[text()='Password must contain minimum 8 symbols']")
    WebElement highlightedFieldPasswordSymbols;

    @FindBy(xpath = "//div[text()='Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]']")
    WebElement highlightedFieldPassword;

    public void typeRegistrationForm(UserDtoLombok user)
    {
        inputName.sendKeys(user.getName());
        inputLastName.sendKeys(user.getLastName());
        inputEmail.sendKeys(user.getEmail());
        inputPwd.sendKeys(user.getPassword());
        pause(2);
    }

    public void clickCheckbox()
    {
        checkbox.click();
    }

    public void clickBtnYalla()
    {
        pause(2);
        btnYalla.click();
    }

    public boolean registrationMessage(String text)
    {
        return isTextInElementPresent(popUpMessage, text) ;
    }

    public boolean registrationHighlightedFieldEmail(String text)
    {
        return isTextInElementPresent(highlightedFieldEmail, text);
    }

    public boolean registrationHighlightedFieldPassword(String text)
    {
        return isTextInElementPresent(highlightedFieldPasswordSymbols, text);
    }
    public boolean registrationHighlightedFieldPassword1(String text)
    {
        return isTextInElementPresent(highlightedFieldPassword, text);
    }

    public boolean disabledBtn()
    {
        btnYalla.isEnabled();
        return false;
    }
}
