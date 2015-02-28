package runner;

import org.apache.commons.lang3.StringUtils;

public class Temptest {
	public static void main(String[] args) {
		String calzzName = "RoleTest";
		System.out.println(calzzName.substring(0, 1).toLowerCase()+calzzName.substring(1));
		System.out.println(StringUtils.uncapitalize(calzzName));
	}
}
