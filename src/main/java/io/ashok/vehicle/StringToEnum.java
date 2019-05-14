package io.ashok.vehicle;

public class StringToEnum {
	
	public static Status convert(String status) {
		try {
			return Status.valueOf(status);
		}catch(Exception e) {
			return null;
		}
	}
	
//	public <T> T convert(T status) {
//		try {
//			return (enum)<T>.valueOf(status);
//		}catch(Exception e) {
//			return null;
//		}
//	}

}
