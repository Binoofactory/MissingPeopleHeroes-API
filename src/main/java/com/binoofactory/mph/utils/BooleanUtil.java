package com.binoofactory.mph.utils;

public class BooleanUtil 
{
	public static boolean parseYnStr2Boolean(String yn) 
	{
		return "Y".equals(yn);
	}
	public static String parseBoolean2Yn(boolean yn) 
	{		
		return yn ? "Y" : "N";
	}
}
