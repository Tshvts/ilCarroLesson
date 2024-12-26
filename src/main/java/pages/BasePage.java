package pages;

import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage
{
    @Setter
    static WebDriver driver;

    public boolean isTextInElementPresent(WebElement element, String text)
    {
        return element.getText().contains(text);
    }

    public void clickWait(WebElement element, int time)
    {
        new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    @FindBy(xpath = "//h2[@class='message']")
    WebElement popUpMessage;

    public boolean validateUrl(String url, int time)
    {
       return new WebDriverWait(driver,time).until(ExpectedConditions.urlContains(url));
    }

    public boolean isElementPresentDOM(String locator, int time)
    {
        try
        {
            new WebDriverWait(driver, time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            return true;
        }
        catch (Exception e)
        {
            System.out.println("isElementPresent created exception");
            return false;
        }
    }
}
