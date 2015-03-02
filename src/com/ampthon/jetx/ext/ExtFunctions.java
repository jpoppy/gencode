package com.ampthon.jetx.ext;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import jetbrick.template.JetAnnotations.Functions;

@Functions
public class ExtFunctions {
	public static String lower(String var) {
		if (StringUtils.isBlank(var)) {
			return null;
		}
		return var.substring(0, 1).toLowerCase() + var.substring(1);
	}
	public static String UUID() {
		return java.util.UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static String now() {
		DateTime d = new DateTime();
		return d.toString("yyyy-MM-dd HH:mm:ss");
	}
	public static void main(String[] args) {
		System.out.println(now());
	}
}
