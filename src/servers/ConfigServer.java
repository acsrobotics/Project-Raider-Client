package servers;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import main.ServerTools;

public class ConfigServer {
	
	InetAddress ip;
	
	public ConfigServer() throws Throwable {
		try {
			findServer();
		} catch(IOException e) {
				throw new InstantiationException().initCause(e);
		}
	}
	
	public InetAddress findServer() throws SocketException, IOException {
		return new ServerTools().discoverServer(new DatagramSocket(), ServerTools.ServerTypes.CONFIG_SERVER.getPort());
	}
}
