package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	String serviceURL;
	String apiURL;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;

	@BeforeMethod
	public void setup() {
		testBase = new TestBase();
		serviceURL = prop.getProperty("serviceURL");
		apiURL = prop.getProperty("APIURL");
		url = serviceURL + apiURL;

	}

	@Test(priority =1)
	public void tc_01() throws ParseException, IOException {

		restClient = new RestClient();
		httpResponse = restClient.get(url);
		// 1. Status code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, response_status_200, "Status code is not 200");
		System.out.println("Status Code-----> " + statusCode);
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		// 2. Json string
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API : " + responseJson);
		
		//get the value from json object(perPage)
		String perPageValue = TestUtil.getValueByJpath(responseJson, "/per_page");
		System.out.println("per_page value is : "+perPageValue);
		Assert.assertEquals(perPageValue, "6");
		
		//get the value from json object(total)
		String totalValue = TestUtil.getValueByJpath(responseJson, "/total");
		System.out.println("total value is : "+totalValue);
		Assert.assertEquals(totalValue, "12");
		
		//get the value from json array
		String ID = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJpath(responseJson, "/data[0]/email");
		String first_name = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		String last_name = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		System.out.println("ID is : "+ID);
		System.out.println("email is : "+email);
		System.out.println("first name is : "+first_name);
		System.out.println("last name is : "+last_name);
		
		
		
		// 3. All Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String, String> hm = new HashMap<String, String>();
		for (Header header : headerArray) {
			hm.put(header.getName(), header.getValue());
		}
		System.out.println("All headers-----> " + hm);
	}
// get api test with header
	@Test(priority =2)
	public void tc_02() throws ParseException, IOException {

		restClient = new RestClient();
		HashMap<String, String > headerMap = new HashMap();
		headerMap.put("Content-Type", "application/json; charset=utf-8");
		headerMap.put("Connection", "keep-alive");
		headerMap.put("Age","5129");
		httpResponse = restClient.get(url,headerMap);
		// 1. Status code
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, response_status_200, "Status code is not 200");
		System.out.println("Status Code-----> " + statusCode);
		String responseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		// 2. Json string
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API : " + responseJson);
		
		//get the value from json object(perPage)
		String perPageValue = TestUtil.getValueByJpath(responseJson, "/per_page");
		System.out.println("per_page value is : "+perPageValue);
		Assert.assertEquals(perPageValue, "6");
		
		//get the value from json object(total)
		String totalValue = TestUtil.getValueByJpath(responseJson, "/total");
		System.out.println("total value is : "+totalValue);
		Assert.assertEquals(totalValue, "12");
		
		//get the value from json array
		String ID = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJpath(responseJson, "/data[0]/email");
		String first_name = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		String last_name = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		System.out.println("ID is : "+ID);
		System.out.println("email is : "+email);
		System.out.println("first name is : "+first_name);
		System.out.println("last name is : "+last_name);
		
		
		
		// 3. All Headers
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String, String> hm = new HashMap<String, String>();
		for (Header header : headerArray) {
			hm.put(header.getName(), header.getValue());
		}
		System.out.println("All headers-----> " + hm);
	}

}
