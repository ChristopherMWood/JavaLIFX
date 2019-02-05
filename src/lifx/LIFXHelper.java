package lifx;

import java.util.ArrayList;
import lifx.LIFXZStrip;

public class LIFXHelper
{
	private static LIFXHttpService service;
	private static ArrayList<LIFXZStrip> zStrips;
	
	public static LIFXZStrip StartLIFX(String apiKey, String lightId, int delay)
	{
		try
		{
			LIFXHelper.SetLIFXService(apiKey, 1000);
			if (lightId.equals(""))
			{
				LIFXHelper.ListAllLIFXLights();
			}
			else
			{
				LIFXZStrip light = LIFXHelper.GetLightFrom(lightId);
				return light;
			}
		}
		catch (Exception e)
		{
			System.out.println("Oops, something went wrong. Please call someone over.");
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	private static void SetLIFXService(String apiKey, int delay)
	{
		HTTPRequestService httpService = new HTTPRequestService(apiKey, delay);
		service = new LIFXHttpService(httpService);
	}
	
	private static void ListAllLIFXLights() throws Exception
	{
		if (service == null)
			throw new Exception("SetLIFXService must be called first");
		
		if (zStrips == null)
			zStrips = service.GetAvailableZStrips();
		
		for(LIFXZStrip light : zStrips)
			System.out.println(light.toString());
	}
	
	private static LIFXZStrip GetLightFrom(String lightId) throws Exception
	{
		if (service == null)
			throw new Exception("SetLIFXService must be called first");
		
		if (zStrips == null)
			zStrips = service.GetAvailableZStrips();
		
		for(LIFXZStrip light : zStrips)
		{
			if (light.GetId().equals(lightId))
				return light;
		}
		
		return null;
	}
}
