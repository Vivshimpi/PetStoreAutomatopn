package api.test;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 
{
	Faker faker;
	User userPayload;
    Logger log = Logger.getLogger("/PetStoreAutomation/logs");


 @BeforeClass
 public void setup()

 {
	//logs
     PropertyConfigurator.configure("Log4j.properties");
     
     
     faker = new Faker(); 
     userPayload = new User();
     
     userPayload.setId(faker.idNumber().hashCode());
     userPayload.setUsername(faker.name().username());
     userPayload.setFirstname(faker.name().firstName());
     userPayload.setLastname(faker.name().lastName());
     userPayload.setEmail(faker.internet().emailAddress());
     userPayload.setPassword(faker.internet().password(5,10));
     userPayload.setPhone(faker.phoneNumber().cellPhone());
 
     
 
 }
 
 
 @Test(priority = 1)
 public void testPostUser()
 {
	 log.info("Creating User");
	 Response res = UserEndpoints2.createUser(userPayload);
	 res.then().log().all();
	 Assert.assertEquals(res.getStatusCode(), 200);
	 log.info("User is created");

 }
 
 @Test(priority = 2)
 public void testGetUserByName()
 {
	 log.info("geting User info");

//	here below we refer the pay load by using this keyword and use get method   
	 Response res = UserEndpoints2.readUser(this.userPayload.getUsername());
	 res.then().log().all();
	 Assert.assertEquals(res.getStatusCode(), 200);
	 log.info("User info is displayed");

 }
 
 
 @Test(priority = 3)
 public void testUpdateUserByName()
 {
	 log.info("Updating User");

//	 update data using pay load
	 userPayload.setFirstname(faker.name().firstName());
     userPayload.setLastname(faker.name().lastName());
     userPayload.setEmail(faker.internet().emailAddress());
   
     Response res = UserEndpoints2.updateUser(this.userPayload.getUsername(), userPayload);
     res.then().log().body();
	 Assert.assertEquals(res.getStatusCode(), 200);
	 log.info("User is updated");

//	 checking data after update
	 Response resAfterUpdate = UserEndpoints2.readUser(this.userPayload.getUsername());
	 Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);

 }
 
 @Test(priority = 4)
 public void testDeleteUserByName()
 {
	    log.info("Deleting User");

	    Response res = UserEndpoints2.deleteUser(this.userPayload.getUsername());
		 Assert.assertEquals(res.getStatusCode(), 200);
		    log.info("User is Deleted");


 }
 
}
 

