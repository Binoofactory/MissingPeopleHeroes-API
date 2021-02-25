package com.binoofactory.mph.utils;

import java.util.ArrayList;
import java.util.List;

public class Array2ListUtil {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List array2List(Object[] arr)
	{
		List list = new ArrayList();
		for(int idx = 0; idx<arr.length; idx++) 
		{
			list.add(arr[idx]);
		}
		return list;
	}
}
