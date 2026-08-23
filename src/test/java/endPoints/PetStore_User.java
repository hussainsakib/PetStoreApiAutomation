package endPoints;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo_PetStore.User_POJO;


public class PetStore_User {
	
	public Response createUser(User_POJO payload) {
		
		Response response=given()
		     .contentType("application/json")
		     .accept("application/json")
		     .body(payload)
		.when()
		     .post(Routes.post_url);
		
		return response;
	}
	
	public Response getUser(String userName) {
		              
	Response response =given()
			.pathParam("userName", userName)
	                      .accept(ContentType.JSON)
	.when()
	     .get(Routes.get_url);
		
		return response;
	}
	
public Response updateUser(User_POJO payload , String userName) {
		
		Response response=given()
				.pathParam("userName", userName)
		     .contentType("application/json")
		     .accept("application/json")
		     .body(payload)
		.when()
		     .put(Routes.put_url);
		
		return response;
	}
	
public Response deleteUser(String userName) {
	
	Response response =given()
			.pathParam("userName", userName)
	
	.when()
	     .delete(Routes.delete_url);
		
		return response;
	}

}
