import java.io.IOException;

import lifx.LIFXHelper;
import lifx.LIFXZStrip;
import lifx.LightColors;

public class Program
{
	private static String apiKey = "cccc94c7e3a634db1ec96911fb5c24d9675626a0241aec9b228499f707f239bc";
	private static String lightId = "";
	
	public static void YourCode(LIFXZStrip light)
	{
		light.TurnOn();
		light.SetZoneRangeColor(0, light.ZonesCount(), LightColors.White);
		
		for (int i = 0; i < light.ZonesCount(); i++)
		{
			if (i == 0)
			{
				light.SetZoneColor(i, LightColors.Green);
			}
			else
			{
				light.SetZoneColor(i - 1, LightColors.White);
				light.SetZoneColor(i, LightColors.Green);
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{	
		try
		{
			LIFXHelper.SetLIFXService(apiKey, 500);
			if (lightId.equals(""))
			{
				LIFXHelper.ListAllLIFXLights();
			}
			else
			{
				LIFXZStrip light = LIFXHelper.GetLightFrom(lightId);
				YourCode(light);
			}
		}
		catch (Exception e)
		{
			System.out.println("Oops, something went wrong. Please call someone over.");
			System.out.println(e.getMessage());
		}
	}
}
