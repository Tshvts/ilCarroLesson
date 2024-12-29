package tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ResultsPage;
import pages.SearchPage;

import static utilits.RandomUtils.*;

public class SearchCarTests extends AppManager
{
    SearchPage searchPage;
    ResultsPage resultsPage;

    @Test
    public void searchCarPositiveTest()
    {
        searchPage = new SearchPage(getDriver());
        searchPage.fillSearchCarFormWOCalendar("Haifa", "12/02/2025", "12/03/2025");
        resultsPage = new ResultsPage(getDriver());
        Assert.assertTrue(resultsPage.isUrlResultsPresent());
    }

    @Test
    public void searchCarNegativeTest_WrongCity()
    {
        searchPage = new SearchPage(getDriver());
        searchPage.fillSearchCarFormWOCalendar(generateString(15), "12/02/2025", "12/03/2025");
        resultsPage = new ResultsPage(getDriver());
        Assert.assertTrue(searchPage.isElementPresentDOM("//*[text() = ' City is required ']", 5));
    }

    @Test
    public void searchCarNegativeTest_WrongDateBefore()
    {
        searchPage = new SearchPage(getDriver());
        searchPage.fillSearchCarFormWOCalendarNegative("Haifa", "12/02/2025", "12/03/2023");
        resultsPage = new ResultsPage(getDriver());
        Assert.assertTrue(searchPage.isElementPresentDOM("//div[@class='error ng-star-inserted']",3));
    }

    @Test
    public void searchCarNegativeTest_EmptyDate()
    {
        searchPage = new SearchPage(getDriver());
        searchPage.fillSearchCarFormWOCalendarNegative("Haifa", "", "12/03/2023");
        resultsPage = new ResultsPage(getDriver());
        Assert.assertTrue(searchPage.isElementPresentDOM("//div[@class='error ng-star-inserted']",3));
    }


    @Test
    public void searchCarPositiveTestWithCalendar()
    {
        logger.info("Test searchCarPositiveTestWithCalendar-->" + "Haifa" + "Oct/2/2025" + "Dec/3/2025");
        searchPage = new SearchPage(getDriver());
        searchPage.fillSearchCarFormWithCalendar("Haifa", "Oct/2/2025", "Dec/3/2025");
        resultsPage = new ResultsPage(getDriver());
        Assert.assertTrue(resultsPage.isUrlResultsPresent());
    }
}
