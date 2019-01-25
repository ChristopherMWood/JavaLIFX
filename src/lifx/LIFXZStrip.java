package lifx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;

import okhttp3.Response;

public class LIFXZStrip extends LIFXLight
{
	private int zoneCount;
	private HTTPRequestService httpService;
	
	public LIFXZStrip(String id, String name, int zoneCount, String deviceJson, HTTPRequestService httpService) 
	{
		super(id, name, deviceJson);
		this.zoneCount = zoneCount;
		this.httpService = httpService;
	}
	
	public int ZonesCount()
	{
		return zoneCount;
	}

	public void SetZoneColor(int zone, String color, int brightness)
	{
		String selector = "id:" + this.GetId() + "|" + zone;
		SetZone(selector, color, brightness);
	}
	
	public void SetZoneRangeColor(int beginZone, int endZone, String color, int brightness) {
		String selector = "id:" + this.GetId() + "|" + beginZone + "-" + endZone;
		SetZone(selector, color, brightness);
	}
	
	@SuppressWarnings("unchecked")
	private void SetZone(String selector, String color, int brightness)
	{
		double correctedBrightness = ConvertBrightness(brightness);
		String requestUrl = "https://api.lifx.com/v1/lights/" + selector + "/state";
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("color", color);
		jsonBody.put("brightness", correctedBrightness);
		jsonBody.put("fast", true);
		
		Response response = httpService.Put(requestUrl, jsonBody.toJSONString());
		OutputHTTPResponse("PUT", "SetZoneColor", response.code());
	}
	
	public void TurnOn()
	{
		String requestUrl = "https://api.lifx.com/v1/lights/id:" + this.GetId() + "/state";
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("power", "on");
		jsonBody.put("fast", true);
		Response response = httpService.Put(requestUrl, jsonBody.toJSONString());
		OutputHTTPResponse("PUT", "TurnOn", response.code());
	}
	
	public void TurnOff()
	{
		String requestUrl = "https://api.lifx.com/v1/lights/id:" + this.GetId() + "/state";
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("power", "off");
		jsonBody.put("fast", false);
		Response response = httpService.Put(requestUrl, jsonBody.toJSONString());
		OutputHTTPResponse("PUT", "TurnOff", response.code());
	}
	
	private void OutputHTTPResponse(String verb, String requestName, int code)
	{
		Date date = new Date();
	    String strDateFormat = "hh:mm:ss a";
	    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
	    String formattedDate = dateFormat.format(date);
		System.out.println(toString() + "|" + verb + ": " + requestName + " [code:" + code + "] - " + formattedDate);
	}
	
	private double ConvertBrightness(int brightness)
	{
		if (brightness >= 100)
			return 1.0;
		else if (brightness <= 0)
			return 0.01;
		else
			return (double)brightness * 0.01;
	}
}
