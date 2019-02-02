package lifx;

import java.util.ArrayList;
import lifx.LIFXZStrip;

public class LIFXHelper
{
	private static LIFXHttpService service;
	private static ArrayList<LIFXZStrip> zStrips;
	
	public static void SetLIFXService(String apiKey, int delay)
	{
		HTTPRequestService httpService = new HTTPRequestService(apiKey, delay);
		service = new LIFXHttpService(httpService);
	}
	
	public static void ListAllLIFXLights() throws Exception
	{
		if (service == null)
			throw new Exception("SetLIFXService must be called first");
		
		if (zStrips == null)
			zStrips = service.GetAvailableZStrips();
		
		for(LIFXZStrip light : zStrips)
			System.out.println(light.toString());
	}
	
	public static LIFXZStrip GetLightFrom(String lightId) throws Exception
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
