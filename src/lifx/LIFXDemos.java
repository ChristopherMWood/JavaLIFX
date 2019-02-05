package lifx;

public class LIFXDemos 
{
	public static void WalkUpLightStrip(LIFXZStrip light)
	{
		light.TurnOn();
		for (int i = 0; i < light.ZonesCount(); i++)
		{
			light.SetZoneColor(i, LightColors.Purple);	
		}	
		light.TurnOff();
	}
	
	public static void FlashingColors(LIFXZStrip light)
	{
		light.TurnOn();
		light.SetZoneRangeColor(0, light.ZonesCount(), LightColors.Blue);
		light.Delay(1000);
		light.SetZoneRangeColor(0, light.ZonesCount(), LightColors.Red);
		light.Delay(1000);
		light.SetZoneRangeColor(0, light.ZonesCount(), LightColors.White);
		light.Delay(1000);
		light.SetZoneRangeColor(0, light.ZonesCount(), LightColors.Purple);
		light.Delay(1000);
		light.SetZoneRangeColor(0, light.ZonesCount(), LightColors.Green);
		light.TurnOff();
	}
}
