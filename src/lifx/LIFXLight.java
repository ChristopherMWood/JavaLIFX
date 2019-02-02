package lifx;

public class LIFXLight 
{
	private String id;
	private String name;
	private String deviceJson;
	
	public LIFXLight(String id, String name, String deviceJson)
	{
		this.id = id;
		this.name = name;
		this.deviceJson = deviceJson;
	}
	
	public String GetId()
	{
		return id;
	}
	
	public String GetJson()
	{
		return deviceJson;
	}
	
	public void Delay(int requestDelay)
	{
		try
		{
		    Thread.sleep(requestDelay);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	public String toString()
	{
		return "[" + name + "] - " + id;
	}
}
