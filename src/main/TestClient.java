package main;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestClient {
	
	private DatagramSocket ds;
	byte[] discoverServerIP = "DISCOVER_SERVER_IP".getBytes();
	
	public static void main(String[] args) {
	
	}
	
	public void discoverServer(DatagramSocket ds) throws IOException {
		String defaultInetAddress = "255.255.255.255";
		StackTraceElement[] stea = Thread.currentThread().getStackTrace();
		StackTraceElement callerMethod = stea[stea.length-2];
		
		DatagramPacket sendPacket = new DatagramPacket(discoverServerIP, discoverServerIP.length, InetAddress.getByName(defaultInetAddress), 8888);
		ds.send(sendPacket);
		System.out.println("The method '" + callerMethod.getMethodName() + "' in the class '" + callerMethod.getClassName() + "' sent a packet request to: '" + defaultInetAddress + "'.");
		
		
	}
	
}
