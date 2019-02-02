package lifx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.simple.JSONObject;
import okhttp3.Response;

public class LIFXZStrip extends LIFXLight
{
	private long zoneCount;
	private HTTPRequestService httpService;
	private boolean fastMode = true;
	
	public LIFXZStrip(String id, String name, long zoneCount, String deviceJson, HTTPRequestService httpService) 
	{
		super(id, name, deviceJson);
		this.zoneCount = zoneCount;
		this.httpService = httpService;
	}
	
	public void SetFastMode(boolean enableFastMode)
	{
		this.fastMode = enableFastMode;
	}
	
	public int ZonesCount()
	{
		return (int)zoneCount;
	}
	
	public void SetColor(String color)
	{
		SetZoneRangeColor(0, ZonesCount(), color, 100);
	}
	
	public void SetColor(String color, int brightness)
	{
		SetZoneRangeColor(0, ZonesCount(), color, brightness);
	}

	public void SetZoneColor(int zone, String color)
	{
		SetZoneColor(zone, color, 100);
	}
	
	public void SetZoneColor(int zone, String color, int brightness)
	{
		String selector = "id:" + this.GetId() + "|" + zone;
		SetZone(selector, color, brightness);
	}
	
	public void SetZoneRangeColor(int beginZone, int endZone, String color) 
	{
		SetZoneRangeColor(beginZone, endZone, color, 100);
	}
	
	public void SetZoneRangeColor(int beginZone, int endZone, String color, int brightness) 
	{
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
		jsonBody.put("fast", fastMode);
		
		Response response = httpService.Put(requestUrl, jsonBody.toJSONString());
		OutputHTTPResponse("PUT", "SetZoneColor", response.code());
	}
	
	public void TurnOn()
	{
		ToggleLight(true);
	}
	
	public void TurnOff()
	{
		ToggleLight(false);
	}

	@SuppressWarnings("unchecked")
	private void ToggleLight(boolean on)
	{
		String requestUrl = "https://api.lifx.com/v1/lights/id:" + this.GetId() + "/state";
		JSONObject jsonBody = new JSONObject();
		if (on)
			jsonBody.put("power", "on");
		else
			jsonBody.put("power", "off");
		jsonBody.put("fast", fastMode);
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
