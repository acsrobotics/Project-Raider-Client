package src.clients;

import java.net.InetAddress;

import src.clients.dataTypes.RobotMapMirror;

public class ConfigClient extends SocketClient<RobotMapMirror> {

	public ConfigClient(InetAddress hostName, int port) {
		super(hostName, port + 1, new RobotMapMirror());
	}
	
}
