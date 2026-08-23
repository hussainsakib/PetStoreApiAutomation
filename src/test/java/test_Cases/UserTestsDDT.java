package test_Cases;

import org.testng.Assert;
import org.testng.annotations.Test;

import endPoints.PetStore_User;
import io.restassured.response.Response;
import pojo_PetStore.User_POJO;
import utilities.DataProviders;

public class UserTestsDDT {
	
	PetStore_User user= new PetStore_User();
	
	@Test(priority=1, dataProvider="apiData", dataProviderClass=DataProviders.class)
	void testPostUser(String userID,String userName , String fName, String lName, String email, String password , String phone) {
		
		User_POJO userPayload= new User_POJO();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);

		
		Response response=user.createUser(userPayload);
		Assert.assertEquals(response.statusCode(), 200);		
		
	}
	@Test(priority=2 , dataProvider="userName", dataProviderClass=DataProviders.class)
	void testGetUser(String userName) {
		
		Response response=user.getUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}

}
