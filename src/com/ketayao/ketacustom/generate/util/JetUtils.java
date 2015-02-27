package com.ketayao.ketacustom.generate.util;

import java.io.StringWriter;
import java.util.Map;

import jetbrick.template.JetTemplate;

public class JetUtils {
	public static String renderTemplate(JetTemplate template,Map<String, Object> model) {
		StringWriter sw = new StringWriter();
		template.render(model, sw);
		return sw.toString();
	}
}
