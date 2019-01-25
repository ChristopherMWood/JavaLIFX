package lifx;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

import fields.ByteField;
import fields.Field;
import fields.MACAddress;
import fields.MACAddressField;
import fields.UInt16Field;
import fields.UInt64Field;
import fields.UInt8Field;
import packet.BroadcastPacket;

public class LIFXRequestMessage 
{
	//FRAME
	public static final Field<Integer>    FIELD_SIZE         = new UInt16Field().little();
	public static final Field<Integer>    FIELD_PROTOCOL     = new UInt16Field().little();
	public static final Field<ByteBuffer> FIELD_RESERVED_1   = new ByteField(4);
	
	//FRAME ADDRESS
	public static final Field<MACAddress> FIELD_BULB_ADDRESS = new MACAddressField();
	public static final Field<ByteBuffer> FIELD_RESERVED_2   = new ByteField(6);
	public static final Field<Integer>    RESERVED_REQUIRED_ACK     = new UInt8Field().little();
	public static final Field<ByteBuffer> FIELD_RESERVED_3   = new ByteField(2);
	
	//PROTOCOL HEADER
	public static final Field<Long>       FIELD_TIMESTAMP_RESERVED    = new UInt64Field();
	public static final Field<Integer>    FIELD_PACKET_TYPE  = new UInt16Field().little();
	public static final Field<ByteBuffer> FIELD_RESERVED_4   = new ByteField(2);
	
	public ByteBuffer GetBytes(byte[] payload)
	{
		ByteBuffer request = ByteBuffer.allocate(payload.length);
		return request;
	}
	
    public static byte[] Combine(byte[] a, byte[] b)
    {
        int length = a.length + b.length;
        byte[] result = new byte[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        
        return result;
    }
}
