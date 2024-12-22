package tests;

import manager.AppManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ResultsPage;
import pages.SearchPage;

public class SearchCarTests extends AppManager
{
    SearchPage searchPage;
    ResultsPage resultsPage;
    @Test
    public void searchCarPositiveTest()
    {
        searchPage = new SearchPage(getDriver());
        searchPage.fillSearchCarFormWOCalendar("Haifa", "12/25/2024", "12/27/2024");
        resultsPage = new ResultsPage(getDriver());
        Assert.assertTrue(resultsPage.isUrlResultsPresent());
    }
}
