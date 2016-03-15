package src;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

public class TypeAdapter {
	
	public static <T1, T2> void copy(T1 original, T2 newData){
		
		Field[] originalFields = original.getClass().getDeclaredFields();
		Field[] newDataFields  = newData.getClass().getDeclaredFields();
		
		ArrayList<String> originalFieldNames = new ArrayList<String>();
		
		for(Field f : originalFields){
			f.setAccessible(true);
			originalFieldNames.add(f.getName());
		}
		
		Collections.sort(originalFieldNames);
		for(Field f : newDataFields){
			if(originalFieldNames.contains(f.getName())){
				try {
					Double d;
					d = (Double)f.get(newData);
					original.getClass().getDeclaredField(f.getName()).setDouble(null, d);
//					System.out.println("Update field: " + f.getName() + " with value of " + original.getClass().getDeclaredField(f.getName()).getDouble(null));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
}