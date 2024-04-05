package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests 
{
@Test(priority=1, dataProvider="data", dataProviderClass=DataProviders.class)
public void testPostuser(String userId,String userName, String fname,
		                  String lname, String email, String pwd, String ph )
{
	User userPayload = new User();
	userPayload.setId(Integer.parseInt(userId));
	userPayload.setUsername(userName);
	userPayload.setFirstname(fname);
	userPayload.setLastname(lname);
	userPayload.setEmail(email);
	userPayload.setPassword(pwd);
	userPayload.setPhone(ph);
	
	 Response res = UserEndpoints.createUser(userPayload);
	 Assert.assertEquals(res.getStatusCode(), 200);
}

  @Test(priority = 2,dataProvider="UserName", dataProviderClass=DataProviders.class)
  public void testDeleteUserByName(String UserName)
  {
	Response res = UserEndpoints.deleteUser(UserName);
	 Assert.assertEquals(res.getStatusCode(), 200);

  }












}
