import java.io.IOException;

import lifx.LIFXDemos;
import lifx.LIFXHelper;
import lifx.LIFXZStrip;

public class Program
{
	private static String apiKey = "cccc94c7e3a634db1ec96911fb5c24d9675626a0241aec9b228499f707f239bc";
	private static String lightId = "";
	
	public static void YourCode(LIFXZStrip light)
	{
		LIFXDemos.WalkUpLightStrip(light);
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
