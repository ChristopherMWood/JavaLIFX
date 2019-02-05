package main;
import java.io.IOException;

import lifx.LIFXHelper;
import lifx.LIFXZStrip;

public class Program
{
	private static String apiKey_one = "cccc94c7e3a634db1ec96911fb5c24d9675626a0241aec9b228499f707f239bc";
	private static String apiKey_two = "c737e42949e28a1471e71450b00b2f0a9d20fdde37c9b3b0f5cbcd98b95b515f";
	private static String apiKey_three = "ceea37531ad6f22608ad1a7dd83d528d2f2d0a330a7911850b2e2838ed8dc14d";
	private static String lightId = "d073d526a467";
	
	public static void Test() throws IOException
	{	
		LIFXZStrip light = LIFXHelper.StartLIFX(apiKey_one, lightId, 1000);
	}
}
