package test_Cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endPoints.PetStore_User;

import io.restassured.response.Response;

import pojo_PetStore.User_POJO;

public class UserTests {
	PetStore_User user= new PetStore_User();
	Faker faker;
	User_POJO userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUp() {
		
		logger=LogManager.getLogger(this.getClass());
		
		faker = new Faker();
		userPayload = new User_POJO();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 12));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		}
	@Test(priority=1)
	public void testPostUser() {
		logger.info("creating user");
		Response response=user.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("user Created");
	}
	@Test(priority=2)
	public void testGetUser() {
		
		Response response=user.getUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		}
	@Test(priority=3)
	public void testUpdateUser() {
		
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 12));
		
		Response response=user.updateUser(userPayload, userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority=4)
public void testDeleteUser() {
		
		Response response=user.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		}

}
