package src;

import java.io.IOException;
import java.net.NoRouteToHostException;

import src.clients.dataTypes.CameraData;
import src.clients.dataTypes.RobotMapMirror;

public class SimulateRobot {
	public static void main(String[] args) throws IOException, NoRouteToHostException{
		Client client = new Client("192.168.1.255", 3003);

		// Data object for JSON config file
		RobotMapMirror dtObj1 = new RobotMapMirror();
		
		// Data object for camera
		CameraData dtObj2 = new CameraData();
		
		dtObj1 = client.getDataObject(dtObj1.getClass());
		dtObj2 = client.getDataObject(dtObj2.getClass());
		
		System.out.println();
	}
}