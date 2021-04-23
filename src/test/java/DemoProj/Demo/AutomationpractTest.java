package DemoProj.Demo;

import org.testng.annotations.Test;

import BaseTest.TestBase;

import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import Pages.CartsummaryPage;
import Pages.HomePage;
import Pages.ItemPage;
import Pages.SigninPage;
import Pojo.GetPlace;
import Pojo.GetUser;
import Resources.ApiResources;
import Resources.TestData1;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AutomationpractTest extends TestBase {

	@BeforeTest(groups = "UiAutomation")
	public void steupIntialization() throws IOException {
		CommonUtility.readProperty();
		launchBrowser();
		LogCapture.info("Application Launched");

	}

	@Test(priority = 0, description = "To get token", enabled = true, groups = { "api" })
	public void getTocken() throws FileNotFoundException {

		ap = ApiResources.userAPI;
		GetUser gu = given().spec(userspecification()).log().all().expect().defaultParser(Parser.JSON).when()
				.get(ap.getResource()).as(GetUser.class);

		emailid = gu.getData().getEmail();
		LogCapture.info("Email id for login:" + emailid);

	}

	@Test(priority = 1, description = "To verify logo and title", enabled = true, groups = { "UiAutomation" })
	public void verifyHomepageDetails() {

		hp = new HomePage(driver);

		boolean val = hp.verifyLogo();
		AssertJUnit.assertEquals(val, true);

		String actualTitle = hp.verifyTitle();
		Assert.assertEquals(actualTitle, "My Store");
		LogCapture.info("Home Page Logo And Title Verified");
		// getScreenshot(driver);
	}

	@Test(priority = 2, description = "To verify sign in", enabled = true, groups = { "UiAutomation" })
	public void validateSignIn() throws InterruptedException {
		ts = TestData1.password;
		sp = hp.clickSignin();
		boolean val = sp.enterEmailid(emailid);
		Assert.assertEquals(val, true);
		boolean val2 = sp.enterPassword(ts.getResource());
		Assert.assertEquals(val2, true);
		hp = sp.clickSignin();
		LogCapture.info("User signed in sucessfully");

	}

	@Test(priority = 3, description = "To verify search operation", enabled = true, groups = { "UiAutomation" })
	public void verifyPageSearch() {

		ts = TestData1.searchtext;
		boolean val = hp.verifySearchOption();
		Assert.assertEquals(val, true);
		boolean val1 = hp.validateSearchOperation(ts.getResource());
		Assert.assertEquals(val1, true);
		LogCapture.info("Serach Operation Working Properly");
	}

	@Test(priority = 4, description = "Validate select operation of searched item", enabled = true, groups = {
			"UiAutomation" })
	public void validateSelectItem() throws InterruptedException {

		lp = hp.selectingItem();
		LogCapture.info("Searched Item Got Selected");

	}

	@Test(priority = 5, description = "Validate item details", enabled = true, groups = { "UiAutomation" })
	public void enterItemDetails() {

		ts = TestData1.quanityitem;
		quantity = lp.enterQuantity(ts.getResource());
		LogCapture.info("Qauanity of item provided:" + quantity);
		LogCapture.info("Details of selcted item verified and quantity of item provided");

	}

	@Test(priority = 6, description = "Validate add to Add to cart fouctionallity", enabled = true, groups = {
			"UiAutomation" })
	public void validateAddtocart() throws InterruptedException {

		cp = lp.addtoCart();
		LogCapture.info("Add Cart Fouctionallity Is Working and Items Added To Cart");
	}

	@Test(priority = 7, description = "Validate Cartsummary fouctionallity", enabled = true, groups = {
			"UiAutomation" })

	public void validateCartSummary() throws InterruptedException {

		String quanitysummary = cp.getQauantity();
		Assert.assertEquals(quanitysummary, quantity);
		LogCapture.info("Item quanity in Cartsummary verified");

	}

	@Test(priority = 8, description = "Validate Signout fouctionallity", enabled = true, groups = { "UiAutomation" })
	public void validateSignout() throws InterruptedException {
		hp = cp.clickSignout();
		hp.verifySearchOption();
		LogCapture.info("User Sign-out sucessfully");

	}

	// GoogleMaptest Cases

	@Test(priority = 0, description = "To verify place get added sucessfully", enabled = false, groups = {
			"googlemap" })
	public void addPlace() throws FileNotFoundException {

		ap = ApiResources.AddPlaceAPI;
		String response = given().spec(requestspecification()).log().all().body(td.aadPlace()).when()
				.post(ap.getResource()).then().assertThat().statusCode(200).extract().response().asString();

		Placeid = getJsonPath(response, "place_id");

		LogCapture.info(Placeid);

	}

	@Test(priority = 1, description = "To verify place get by placeID", dependsOnMethods = {
			"addPlace" }, enabled = false, groups = { "googlemap" })
	public void getPlace() throws FileNotFoundException {

		ap = ApiResources.getPlaceApi;
		GetPlace gp = given().spec(requestspecification()).queryParam("place_id", Placeid).expect()
				.defaultParser(Parser.JSON).when().get(ap.getResource()).as(GetPlace.class);

		LogCapture.info("Accurecy of location is" + gp.getAccuracy());
		LogCapture.info("Lattitude of Location is" + gp.getLocation().getLatitude());

	}

	@Test(priority = 2, description = "To verify Place Updated sucessfully", dependsOnMethods = {
			"addPlace" }, enabled = false, groups = { "googlemap" })
	public void updatePlace() throws FileNotFoundException {

		ap = ApiResources.updatePlaceAPi;
		String UpdateResponse = given().spec(requestspecification()).log().all()
				.body(td.updatePlace(Placeid, "Updated address")).when().put(ap.getResource()).then().assertThat()
				.statusCode(200).extract().response().asString();
		String msg = getJsonPath(UpdateResponse, "msg");
		Assert.assertEquals("Address successfully updated", msg);

	}

	@Test(priority = 3, description = "To verify Place deleted sucessfully", dependsOnMethods = {
			"addPlace" }, enabled = false, groups = { "googlemap" })
	public void deletePlace() throws FileNotFoundException {

		ap = ApiResources.deletePlaceAPi;
		String DeleteResponse = given().spec(requestspecification()).log().all().body(td.deletePlace(Placeid)).when()
				.delete(ap.getResource()).then().assertThat().statusCode(200).extract().response().asString();
		String status = getJsonPath(DeleteResponse, "status");
		Assert.assertEquals("OK", status);

	}

	@AfterTest(groups = "UiAutomation")
	public void tearDown() throws IOException {
		driver.quit();
	}

	/*
	 * @AfterClass public void tearDown() throws IOException { driver.quit(); }
	 */

}
