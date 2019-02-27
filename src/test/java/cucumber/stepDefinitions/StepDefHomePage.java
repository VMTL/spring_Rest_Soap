package cucumber.stepDefinitions;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.Scenario;
import pages.InvestingHome;

public class StepDefHomePage extends cucBaseTest{
	
	public InvestingHome homePage;	

	@After//(value= {"@negativeMessageScenario"}, order = 10)
	public void after(Scenario cucScenario) {
		takeScreenOnFailure(cucScenario);
		afterClass();
	}
	
	@Given("Opening a {string} browser")
    public void goToHomePage(String browserName) {
		driverIni(browserName);		
		this.homePage = new InvestingHome(driver);
    }
    
    @When("Entering search_field {string} select_search {string}")
    public void eneterFieldValues(String searchField, String searchList){
		driver.browserNavigate().to("https://ca.investing.com/");
		homePage.inputSearch().sendKeys("euro swiss");
		homePage.selectSearchField().click();
		homePage.selectDropDownClick(searchList);
	}

    @Then("Verify results container is not empty")
    public void verifyMessageValue() {
    	assertTrue(homePage.driver.findElementByLocator(By.xpath("//*[@class='js-scrollable-results-wrapper newResultsContainer']")).isDisplayed());
    }
}