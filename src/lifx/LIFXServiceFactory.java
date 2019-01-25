package lifx;

public class LIFXServiceFactory 
{
	public static LIFXService CreateHttpService(String apiKey, int requestDelay)
	{
		HTTPRequestService requestService = new HTTPRequestService(apiKey, requestDelay);
		return new LIFXHttpService(requestService);
	}
	
	public static LIFXService CreateLanService()
	{
		return null;
	}
}
