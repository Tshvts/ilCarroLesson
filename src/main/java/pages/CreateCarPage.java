package pages;

import dto.CreateCarDto;
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
    WebElement fieldAddress;

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


    public void typeFormCreatingCar(CreateCarDto createCar)
    {
        fieldManufacture.sendKeys(createCar.getManufacture());
        fieldModel.sendKeys(createCar.getModel());
        fieldYear.sendKeys(createCar.getYear());
        selectTypeFuel(createCar.getFuels());
        fieldSeats.sendKeys(createCar.getSeats());
        fieldCarClass.sendKeys(createCar.getCarClass());
        fieldRegistrationNumber.sendKeys(createCar.getCarRegistrationNumber());
        fieldPrice.sendKeys(createCar.getPrice());
        fieldAbout.sendKeys(createCar.getAbout());
        btnSubmit.click();
    }

    private void selectTypeFuel(List<Fuel> fuels)
    {
        fieldFuel.click();
        selectFuelType.click();
        for (Fuel h: fuels)
        {
            WebElement fuelElement = driver.findElement(By.xpath(h.getLocator()));
            fuelElement.click();
        }
    }
}
