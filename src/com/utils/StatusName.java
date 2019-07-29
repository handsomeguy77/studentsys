package com.utils;

import java.util.ArrayList;
import java.util.List;

public class StatusName {
	public static final String status_one = "³öÇÚ";
	public static final String status_two ="È±ÇÚ";
	public static final String status_three ="Çë¼Ù";
	
	public static List<String> getStatus(){
		List<String> list = new ArrayList<String>();
		list.add(status_one);
		list.add(status_two);
		list.add(status_three);
		return list;
	}
}
