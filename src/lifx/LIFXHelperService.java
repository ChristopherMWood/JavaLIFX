package lifx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class LIFXHelperService 
{
	public static void ListActiveUDPDevices() throws IOException
	{
		DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        System.out.println(ip.getHostAddress());
        
        byte[] message = new byte[0];
        
        // Big endian: 0011 0100 0000 0000  (34 00)
        // Little endian: 
        
        DatagramPacket DpSend = new DatagramPacket(message, message.length, ip, 56700); 
        ds.send(DpSend); 
        
	}
}
