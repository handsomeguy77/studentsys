package com.utils;

import java.util.ArrayList;
import java.util.List;

public class TermName {
	public static final String term_one = "大一上学期";
	public static final String term_two = "大一下学期";
	public static final String term_third = "大二上学期";
	public static final String term_four = "大二下学期";
	public static final String term_five = "大三上学期";
	public static final String term_six = "大三下学期";
	public static final String term_seven = "大四上学期";
	public static final String term_eight = "大四下学期";
	
	public static List<String> getTerm(){
		List<String> list = new ArrayList<String>();
		list.add(term_one);
		list.add(term_two);
		list.add(term_third);
		list.add(term_four);
		list.add(term_five);
		list.add(term_six);
		list.add(term_seven);
		list.add(term_eight);
		return list;
	}
}


