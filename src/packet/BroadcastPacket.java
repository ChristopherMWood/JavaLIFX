package packet;

import java.nio.ByteBuffer;

public class BroadcastPacket extends Packet {

	@Override
	public int packetType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int packetLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void parsePacket(ByteBuffer bytes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ByteBuffer packetBytes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] expectedResponses() {
		// TODO Auto-generated method stub
		return null;
	}

}
