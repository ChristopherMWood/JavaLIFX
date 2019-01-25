import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import lifx.LIFXService;
import lifx.LIFXServiceFactory;
import lifx.LIFXZStrip;
import lifx.UdpTestClient;
import lifx.UdpTestServer;
import packet.BroadcastPacket;
import packet.Packet;

public class Program 
{
	public static final int BROADCAST_PORT = 56700;
	public static final int BROADCAST_DELAY = 1000;
	private static DatagramChannel channel;
	private static String apiKey = "cccc94c7e3a634db1ec96911fb5c24d9675626a0241aec9b228499f707f239bc";
	
	public static void main(String[] args) throws IOException
	{	
		LIFXService service = LIFXServiceFactory.CreateHttpService(apiKey, 10);
		ArrayList<LIFXZStrip> availableLights = service.GetAvailableZStrips();
		
		ListAvailableLights(availableLights);
		LIFXZStrip light = GetLightById("d073d526a467", availableLights);

//		startListening();
//		Packet discoverPacket = new BroadcastPacket();
//		broadcast(discoverPacket);
	}
	
	public static void startListening() throws IOException {
		channel = DatagramChannel.open();
	}
	
	/**
	 * Sends the given packet to the specified destination.
	 * @param packet the packet to send
	 * @param destination the destination address for the packet
	 * @throws java.nio.channels.ClosedChannelException
	 * @throws java.io.IOException
	 */
	public void send(Packet packet, InetSocketAddress destination)
			throws ClosedChannelException, IOException {
		channel.send(packet.bytes(), destination);
	}
	
	/**
	 * Broadcasts the given packet to all possible addresses.
	 * @param packet the packet to broadcast
	 * @throws java.nio.channels.ClosedChannelException
	 * @throws java.io.IOException
	 */
	public static void broadcast(Packet packet)
			throws ClosedChannelException, IOException {
		channel.send(packet.bytes(), new InetSocketAddress("255.255.255.255", BROADCAST_PORT));
	}
	
	
	
	private static void GetAllLightsViaUdp() throws IOException
	{
		
		
//		UdpTestServer server = new UdpTestServer();
//		
//		server.start();
//		UdpTestClient client = new UdpTestClient();
//		String echo = client.sendEcho("Hello World");
//		System.out.println("Response: " + echo);
//		client.close();
//		server.close();
		
	}
	
	public static void broadcast(String broadcastMessage) throws IOException {
		DatagramSocket socket = new DatagramSocket();
        socket.setBroadcast(true);
 
        byte[] buffer = broadcastMessage.getBytes();
 
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), 56700);
        socket.send(packet);
        
        socket.receive(packet);
        System.out.println("Received");
        
        socket.close();
	}
	
	private static LIFXZStrip GetLightById(String id, ArrayList<LIFXZStrip> lights)
	{
		for(LIFXZStrip light : lights)
		{
			if (light.GetId().equals(id))
				return light;
		}
		
		return null;
	}
	
	private static void ListAvailableLights(ArrayList<LIFXZStrip> lights)
	{
		for(LIFXZStrip light : lights)
		{
			System.out.println(light.toString());
		}
	}
}
