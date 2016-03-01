package servers;

import java.net.InetAddress;

import concurrent.ObjectWrapper;

public interface Servers {
	
	ObjectWrapper objw = new ObjectWrapper();
	
	default <T> InetAddress findServer() {
		return null;
	}
	
	
}
