package main;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class ServerTools {
	
	public static enum ServerTypes {
		CONFIG_SERVER(3004), PUBLIC_SERVER(3003), VISION_SERVER(3005);
		
		private Integer port;
		
		ServerTypes(Integer port) {
			this.port=port;
		}
		
		public Integer getPort() {
			return port;
		}
	}
	
	/**Looks for a SocketServer running on the same network, given a specified port.
	 * 
	 * @param ds - A DatagramSocket.
	 * @param port - The port to send packets through.
	 * @return InetAddress - The network address (IPv4 or IPv6) of the server that responds to the ping requests.
	 * @throws IOException
	 */
	public InetAddress discoverServer(DatagramSocket ds, Integer port) throws IOException {
		String defaultInetAddress = "255.255.255.255";
		byte[] sendData = "DISCOVER_REMOTE_SERVER".getBytes();
		
		ds.setBroadcast(true);
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
				InetAddress.getByName(defaultInetAddress), port);
		ds.send(sendPacket);
		
		//Loop over network interfaces and ping each device...
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface networkInterface = interfaces.nextElement();
			
			if (networkInterface.isLoopback() || !networkInterface.isUp()) {
				continue;
			}
			
			for (InterfaceAddress  ia : networkInterface.getInterfaceAddresses()) {
				InetAddress broadcast = ia.getBroadcast();
				if (broadcast == null) {
					continue;
				}
				DatagramPacket actualPacket = new DatagramPacket(sendData, sendData.length, broadcast, port);
				ds.send(actualPacket);
				System.out.println(getClass().getName() + ">>> Request packet sent to: " + broadcast.getHostAddress() 
					+ "; Interface: " + networkInterface.getDisplayName());
				
				System.out.println(getClass().getName() + ">>> Done looping over all network interfaces. Now waiting for a reply!");	
			}
		}
		
		//Wait for a reply!...
		byte[] recvBuf = new byte[20000];
		DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
		ds.receive(receivePacket);
		
		//Response? :D
		System.out.println(getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());
		return receivePacket.getAddress();
	}
	
}
