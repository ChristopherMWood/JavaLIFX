package fields;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tim
 */
public class MACAddress {
	
	private ByteBuffer bytes;
	private String hex;
	
	public MACAddress(ByteBuffer bytes) {
		this.bytes = bytes;
		
		createHex();
	}
	
	public MACAddress() {
		this(ByteBuffer.allocate(6));
	}
	
	public ByteBuffer getBytes()
	{
		return bytes;
	}
	
	private void createHex() {
		bytes.rewind();
		
		List<String> byteStrings = new LinkedList<>();
		while (bytes.hasRemaining()) {
			byteStrings.add(String.format("%02X", bytes.get()));
		}
		
		hex = String.join(":", byteStrings);
		
		bytes.rewind();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.hex);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		final MACAddress other = (MACAddress) obj;
		if (!this.hex.equalsIgnoreCase(other.hex)) {
			return false;
		}
		
		return true;
	}
	
}
