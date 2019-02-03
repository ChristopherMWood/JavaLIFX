package lifx;

public class LIFXDemos 
{
	public static void WalkUpLightStrip(LIFXZStrip light)
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
}
