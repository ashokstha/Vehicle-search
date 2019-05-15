package io.ashok.util;

import io.ashok.vehicle.Make;
import io.ashok.vehicle.Status;

public class StringToEnum {
	
	public static Status convertStatus(String status) {
		try {
			return Status.valueOf(status);
		}catch(Exception e) {
			return null;
		}
	}
	
	public static Make convertMake(String make) {
		try {
			return Make.valueOf(make);
		}catch(Exception e) {
			return null;
		}
	}
	
//	public <T extends Enum<T>> T convert(T tEnum, String status) {
//		try {
//			return T.valueOf(Class<tEnum>, status);
//		}catch(Exception e) {
//			return null;
//		}
//	}

}
