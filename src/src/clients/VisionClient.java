package src.clients;

import java.net.InetAddress;

import src.clients.dataTypes.CameraData;

public class VisionClient extends SocketClient<CameraData> {

	public VisionClient(InetAddress hostName, int port) {
		super(hostName, port + 2, new CameraData());
	}

}
