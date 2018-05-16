package com.bytom.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.*;

public class Utils {
	public static String rfc3339DateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	public static final Gson serializer = new GsonBuilder().setDateFormat(rfc3339DateFormat).create();

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat(rfc3339DateFormat);
		String d = "2018-05-10T10:00:12.959572357+08:00";
		try {
			Date dd = sdf.parse(d);
			System.out.println(dd.getMonth());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
