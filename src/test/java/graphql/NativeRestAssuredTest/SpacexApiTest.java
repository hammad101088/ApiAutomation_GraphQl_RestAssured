package graphql.NativeRestAssuredTest;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import spacexGraphql.NativeRestAssured.SpacexApiObjectModel;
import utilities.nativeRestAssured.UtilityClass;

public class SpacexApiTest {

	SpacexApiObjectModel spacexApiObjectModel;

	@BeforeClass
	public void beforeClass() {
		baseURI=UtilityClass.BASE_URI;
		spacexApiObjectModel = new SpacexApiObjectModel();

	}

	@Test
	public void testPatLaunches_checkRocketName_shouldBeFalcon9() {

		Response response = spacexApiObjectModel.getPastLaunches();
		UtilityClass.assertGraphqlResponse(response,"data.launchesPast[0].rocket.rocket_name", "Falcon 9");
	}
	
	@Test
	public void insertUser_checkuserName_shouldBeTheSame() {

		Response response = spacexApiObjectModel.insertUser("Mohamed Hammad","Hammad's rocket");
		UtilityClass.assertGraphqlResponse(response,"data.insert_users.returning[0].name", "Mohamed Hammad");
		UtilityClass.assertGraphqlResponse(response,"data.insert_users.returning[0].rocket", "Hammad's rocket");

	}
	

	
}
