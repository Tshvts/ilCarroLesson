package pages;

import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void pause(int time)
    {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
