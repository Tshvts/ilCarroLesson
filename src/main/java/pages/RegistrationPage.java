package pages;

import dto.UserDtoLombok;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

    public boolean isPopIpMessagePresent()
    {
        return isTextInElementPresent(popUpMessage, "You are logged in success");
    }

}
