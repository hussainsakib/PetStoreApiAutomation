package endPoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo_PetStore.User_POJO;


public class PetStore_User {
	
	static ResourceBundle getUrl() {
		
		ResourceBundle routes= ResourceBundle.getBundle("Routes");
		return routes;
		
		}
	public Response createUser(User_POJO payload) {
		
		String post_url=getUrl().getString("post_url");
		
		Response response=given()
		     .contentType("application/json")
		     .accept("application/json")
		     .body(payload)
		.when()
		     .post(post_url);
		
		return response;
	}
	
	public Response getUser(String userName) {
		
		String get_url=getUrl().getString("get_url");
		              
	Response response =given()
			.pathParam("userName", userName)
	        .accept(ContentType.JSON)
	.when()
	     .get(get_url);
		
		return response;
	}
	
public Response updateUser(User_POJO payload , String userName) {
	
	String update_url=getUrl().getString("update_url");
		
		Response response=given()
				.pathParam("userName", userName)
		     .contentType("application/json")
		     .accept("application/json")
		     .body(payload)
		.when()
		     .put(update_url);
		
		return response;
	}
	
public Response deleteUser(String userName) {
	
	String delete_url=getUrl().getString("delete_url");
	
	Response response =given()
			.pathParam("userName", userName)
	
	.when()
	     .delete(delete_url);
		
		return response;
	}

}
