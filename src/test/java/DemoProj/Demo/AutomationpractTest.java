package DemoProj.Demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
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
	public void before_it() throws IOException {
		CommonUtility.Initialization();
		launch();
	}

	/*
	 * @BeforeClass public void before_it() throws IOException {
	 * 
	 * CommonUtility.Initialization(); launch(); }
	 */

	@Test(priority = 0, description = "To verify sign in", enabled = true, groups = { "api" })
	public void getTocken() throws FileNotFoundException {

		ap = ApiResources.valueOf("userAPI");
		GetUser gu = given().spec(userspecification()).log().all().expect().defaultParser(Parser.JSON).when()
				.get(ap.getResource()).as(GetUser.class);

		emailid = gu.getData().getEmail();
		System.out.println(emailid);

	}

	@Test(priority = 1, description = "To verify logo and title", enabled = true, groups = { "UiAutomation" })
	public void verifyHomepageDetails() {

		hp = new HomePage(driver);

		boolean val = hp.verifylogo();
		AssertJUnit.assertEquals(val, true);

		String actualTitle = hp.verifytitle();
		AssertJUnit.assertEquals(actualTitle, "My Store");
		System.out.println("Home Page Logo And Title Verified");
		// getScreenshot(driver);
	}

	@Test(priority = 2, description = "To verify sign in", enabled = true, groups = { "UiAutomation" })
	public void validateSignIn() throws InterruptedException {
		ts = TestData1.valueOf("password");
		sp = hp.signinclick();
		boolean val = sp.enteremailid(emailid);
		AssertJUnit.assertEquals(val, true);
		boolean val2 = sp.enterpassword(ts.getResource());
		AssertJUnit.assertEquals(val2, true);
		hp = sp.clicksignin();

	}

	@Test(priority = 3, description = "To verify search operation", enabled = true, groups = { "UiAutomation" })
	public void verifyPagesearch() {

		ts = TestData1.valueOf("searchtext");
		boolean val = hp.verifyserachdisplayed();
		AssertJUnit.assertEquals(val, true);
		boolean val1 = hp.validateserachoperation(ts.getResource());
		AssertJUnit.assertEquals(val1, true);
		System.out.println("Serach Operation Working Properly");
	}

	@Test(priority = 4, description = "Validate select operation of searched item", enabled = true, groups = {
			"UiAutomation" })
	public void validateSelectitem() throws InterruptedException {

		lp = hp.selectingsearcheditem();
		System.out.println("Searched Item Got Selected");

	}

	@Test(priority = 5, description = "Validate price of searched item", enabled = true, groups = { "UiAutomation" })
	public void verifyItemdeatils() {

		ts = TestData1.valueOf("quanityitem");
		lp.getprice();
		lp.entquantity(ts.getResource());
		System.out.println("Details of selcted item verified and quantity of item provided");

	}

	@Test(priority = 6, description = "Validate add to Add to cart fouctionallity", enabled = true, groups = {
			"UiAutomation" })
	public void validateAddtocart() throws FindFailed, InterruptedException {

		cp = lp.addtocart();
		System.out.println("Add Cart Fouctionallity Is Working and Items Added To Cart");
	}

	// GoogleMaptest Cases

	@Test(priority = 0, description = "To verify place get added sucessfully", enabled = false, groups = {
			"googlemap" })
	public void addPlace() throws FileNotFoundException {

		ap = ApiResources.valueOf("AddPlaceAPI");
		String response = given().spec(requestspecification()).log().all().body(td.aadPlace()).when()
				.post(ap.getResource()).then().assertThat().statusCode(200).extract().response().asString();

		Placeid = getJsonPath(response, "place_id");

		System.out.println(Placeid);

	}

	@Test(priority = 1, description = "To verify place get by placeID", dependsOnMethods = {
			"addPlace" }, enabled = false, groups = { "googlemap" })
	public void getPlace() throws FileNotFoundException {

		ap = ApiResources.valueOf("getPlaceApi");
		GetPlace gp = given().spec(requestspecification()).queryParam("place_id", Placeid).expect()
				.defaultParser(Parser.JSON).when().get(ap.getResource()).as(GetPlace.class);

		System.out.println("Accurecy of location is" + gp.getAccuracy());
		System.out.println("Lattitude of Location is" + gp.getLocation().getLatitude());

	}

	@Test(priority = 2, description = "To verify Place Updated sucessfully", dependsOnMethods = {
			"addPlace" }, enabled = false, groups = { "googlemap" })
	public void updatePlace() throws FileNotFoundException {

		ap = ApiResources.valueOf("updatePlaceAPi");
		String UpdateResponse = given().spec(requestspecification()).log().all()
				.body(td.updatePlace(Placeid, "Updated address")).when().put(ap.getResource()).then().assertThat()
				.statusCode(200).extract().response().asString();
		String msg = getJsonPath(UpdateResponse, "msg");
		AssertJUnit.assertEquals("Address successfully updated", msg);

	}

	@Test(priority = 3, description = "To verify Place deleted sucessfully", dependsOnMethods = {
			"addPlace" }, enabled = false, groups = { "googlemap" })
	public void deletePlace() throws FileNotFoundException {

		ap = ApiResources.valueOf("deletePlaceAPi");
		String DeleteResponse = given().spec(requestspecification()).log().all().body(td.deletePlace(Placeid)).when()
				.delete(ap.getResource()).then().assertThat().statusCode(200).extract().response().asString();
		String status = getJsonPath(DeleteResponse, "status");
		AssertJUnit.assertEquals("OK", status);

	}

	@AfterTest(groups = "UiAutomation")
	public void tearDown() throws IOException {
		driver.quit();
	}

	/*
	 * @AfterClass public void tearDown() throws IOException { driver.quit(); }
	 */

}
