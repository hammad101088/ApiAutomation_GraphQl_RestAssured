package utilities.nativeRestAssured;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class UtilityClass {

	// ******* variables *******//
	private static org.json.JSONObject jsonObject = new org.json.JSONObject();
	private static RequestSpecification request = RestAssured.given();
	public static final String BASE_URI = "https://api.spacex.land/"; //should be changed for every project
	private static final String END_POINT = "graphql/"; // common end point for every graphql request

	// ******* constructor *******//
	private UtilityClass() {
		jsonObject = new org.json.JSONObject();
	}

	// ******* request related methods *******//
	
	/**
	 * MANDATORY <br>
	 * set the request query or mutation, the request cannot be sent without a query or mutation.
	 * @param query
	 */
	private static void setQuery(String query) {
		jsonObject.put("query", query);
	}

	/**
	 * OPTIONAL<br>
	 * set the request variables, the request can be sent without variables
	 * @param variables
	 */
	private static void setVariables(String variables) {
		jsonObject.put("variables", variables);
	}
	
	/**
	 * OPTIONAL<br>
	 * set the request fragments if there are any, the request can be sent without fragments 
	 * @param fragments
	 */
	private static void setFragments(String fragments) {
		jsonObject.put("fragments", fragments);
	}

	/**
	 * MANDATORY<br>
	 * get the request body as a string value.
	 * @return Request Body
	 */
	private static String getRequestBody() {

		return jsonObject.toString();
	}

	/**
	 * MANDATORY <br>
	 * Send Graphql Request Using The Request Body.<br>
	 * Use this method after using "getRequestBody" method
	 * @param body
	 * @return Graphql Response
	 */
	public static Response sendGraphqlRequest(String query) {
		
		UtilityClass.setQuery(query);

		System.out.println("sending graphql request...");
		request.header("Content-Type", "application/json");
		request.body(UtilityClass.getRequestBody());
		request.given().log().body();
		Response response = request.post(END_POINT);
		System.out.println("getting graphql response...");
		response.then().statusCode(200).log().body();
		return response;
		
	}
	
	public static Response sendGraphqlRequest(String query, String variables) {
		
		UtilityClass.setQuery(query);
		UtilityClass.setVariables(variables);

		System.out.println("sending graphql request...");
		request.header("Content-Type", "application/json");
		request.body(UtilityClass.getRequestBody());
		request.given().log().body();
		Response response = request.post(END_POINT);
		System.out.println("getting graphql response...");
		response.then().statusCode(200).log().body();
		return response;
		
	}
	
	public static Response sendGraphqlRequest(String query, String variables, String fragments) {
		
		UtilityClass.setQuery(query);
		UtilityClass.setVariables(variables);
		UtilityClass.setFragments(fragments);

		System.out.println("sending graphql request...");
		request.header("Content-Type", "application/json");
		request.body(UtilityClass.getRequestBody());
		request.given().log().body();
		Response response = request.post(END_POINT);
		System.out.println("getting graphql response...");
		response.then().statusCode(200).log().body();
		return response;
		
	}
	// ******* response related methods *******//
	
	/**
	 * assert that the actual results matches the expected results.
	 * the execution will stop once this assertion failed
	 * @param response
	 * @param actualResult_jsonPath
	 * @param expectedResult
	 */
	public static void assertGraphqlResponse(Response response, String actualResult_jsonPath, String expectedResult) {
		System.out.println("assert that, '"+ actualResult_jsonPath+"' is equal to:" +expectedResult+"");
		response.then().body(actualResult_jsonPath, equalTo(expectedResult));
	}
	

}
