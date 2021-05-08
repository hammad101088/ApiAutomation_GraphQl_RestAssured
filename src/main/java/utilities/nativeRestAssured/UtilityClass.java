package utilities.nativeRestAssured;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public final class UtilityClass {

	// ******* variables *******//
	private static org.json.JSONObject jsonObject = new org.json.JSONObject();
	private static RequestSpecification request = RestAssured.given();
	public static final String BASE_URI = "https://api.spacex.land/graphql/";    //should be changed for every project

	// ******* request related methods *******//
	private static void setQuery(String query) {
		jsonObject.put("query", query);
	}


	private static void setVariables(String variables) {
		jsonObject.put("variables", variables);
	}
	

	private static void setFragments(String fragments) {
		jsonObject.put("fragments", fragments);
	}


	private static String getRequestBody() {

		return jsonObject.toString();
	}
	
	/**
	 * Send Graphql Request Using "Query or Mutation" WITH Variables or Fragments.<br>
	 * At least you should provide "Query or Mutation"
	 * @param query -> MANDATORY
	 * @param variables -> OPTIONAL (Nullable)
	 * @param fragments -> OPTIONAL (Nullable)
	 * @return Graphql Response
	 */
	public static Response sendGraphqlRequest(String query, String variables, String fragments) {
		
		if(query != null) {
		UtilityClass.setQuery(query);}
		
		if (variables !=null) {
		UtilityClass.setVariables(variables);}
		
		if (fragments != null) {
		UtilityClass.setFragments(fragments);}
		
		String requestBody = UtilityClass.getRequestBody();
		
		//request actions
		System.out.println(">>> sending graphql request >>>");
		request.header("Content-Type", "application/json");
		request.body(requestBody);
		request.given().log().all();
		
		//response actions
		Response response = request.post(BASE_URI);
		System.out.println("<<< getting graphql response <<<");
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
