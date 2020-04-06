package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	private CloseableHttpResponse httpResponse;
	//Get method without Headers
	public CloseableHttpResponse get(String url)  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		try {
			 httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpResponse;
	}
	//Get Method with Headers
	public CloseableHttpResponse get(String url, HashMap<String, String> hm)  {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		for(Map.Entry<String, String> entry : hm.entrySet())
		{
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		try {
			 httpResponse = httpClient.execute(httpGet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return httpResponse;
	}
}
