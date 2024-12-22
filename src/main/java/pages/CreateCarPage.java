package pages;

import dto.CarDto;
import enums.Fuel;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class CreateCarPage extends BasePage
{
    public CreateCarPage(WebDriver driver)
    {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//input[@id='pickUpPlace']")
    WebElement fieldCity;

    @FindBy(xpath = "//div[@class='pac-item']")
    WebElement locationSubmit;

    @FindBy(xpath = "//input[@id='make']")
    WebElement fieldManufacture;

    @FindBy(xpath = "//input[@id='model']")
    WebElement fieldModel;

    @FindBy(xpath = "//input[@id='year']")
    WebElement fieldYear;

    @FindBy(xpath = "//label[@class='select-label']")
    WebElement fieldFuel;

    @FindBy(xpath = "//select[@id='fuel']")
    WebElement selectFuelType;

    @FindBy(xpath = "//input[@id='seats']")
    WebElement fieldSeats;

    @FindBy(xpath = "//input[@id='class']")
    WebElement fieldCarClass;

    @FindBy(xpath = "//input[@id='serialNumber']")
    WebElement fieldRegistrationNumber;

    @FindBy(xpath = "//input[@id='price']")
    WebElement fieldPrice;

    @FindBy(xpath = "//textarea[@placeholder='About (max 500 chars)']")
    WebElement fieldAbout;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement btnSubmit;

    public void typeLetCarWorkForm(CarDto car)
    {
        fieldCity.sendKeys(car.getCity());
        clickWait(locationSubmit,5);
        fieldManufacture.sendKeys(car.getManufacture());
        fieldModel.sendKeys(car.getModel());
        fieldYear.sendKeys(car.getYear());
        fieldFuel.click();
        clickWait(driver.findElement(By.xpath(car.getFuel())),5);
        fieldSeats.sendKeys(car.getSeats() + "");
        fieldCarClass.sendKeys(car.getCarClass());
        fieldRegistrationNumber.sendKeys(car.getSerialNumber());
        fieldPrice.sendKeys(Double.toString(car.getPrice()));
        fieldAbout.sendKeys(car.getAbout());
        clickWait(btnSubmit,5);
    }

    public boolean isPopUpMessagePresent(String text)
    {
        return isTextInElementPresent(popUpMessage, text);
    }
}
