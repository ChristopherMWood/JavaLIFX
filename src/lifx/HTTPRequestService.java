package lifx;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPRequestService 
{
	private String authenticationKey;
	private int requestDelayMilliseconds;
	
	public HTTPRequestService(String authenticationKey, int requestDelayMilliseconds)
	{
		this.authenticationKey = authenticationKey;
		this.requestDelayMilliseconds = requestDelayMilliseconds;
	}
	
	public Response Get(String url)
	{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url(url)
		  .get()
		  .addHeader("Authorization", "Bearer " + authenticationKey)
		  .build();

		try 
		{
			Response response = client.newCall(request).execute();
			Delay();
			return response;
		} 
		catch (IOException e) 
		{
			System.out.println("Error performing Get on " + url);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Response Put(String url, String jsonBody)
	{
		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, jsonBody);
		Request request = new Request.Builder()
		  .url(url)
		  .put(body)
		  .addHeader("Authorization", "Bearer " + authenticationKey)
		  .build();
	
		try 
		{
			Response response = client.newCall(request).execute();
			Delay();
			return response;
		} 
		catch (IOException e) 
		{
			System.out.println("Error performing Put on " + url);
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void Delay()
	{
		try
		{
		    Thread.sleep(requestDelayMilliseconds);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
}
