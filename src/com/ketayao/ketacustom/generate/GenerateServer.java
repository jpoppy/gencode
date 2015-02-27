package com.ketayao.ketacustom.generate;

import java.io.IOException;
import java.util.regex.Matcher;

import jetbrick.template.JetTemplate;

import com.ketayao.ketacustom.generate.util.FileUtils;
import com.ketayao.ketacustom.generate.util.JetUtils;


/** 
 * @author 	<a href="mailto:ketayao@gmail.com">ketayao</a>
 * @since   2013年10月24日 上午9:55:58 
 */
public class GenerateServer extends AbstractGenerate {
	public GenerateServer() {
		super();
	}
	
	public void generate() throws IOException {
		String packageName = (String)model.get("packageName");
		packageName = packageName + ".test";
		model.put("packageName", packageName);
		
		JetTemplate template = engine.getTemplate("server" + separator + "QuickStartServer.java.ftl");
		String content = JetUtils.renderTemplate(template, model);
		
		String packagePath = packageName.replaceAll("\\.", Matcher.quoteReplacement(separator));
		
		String filePath = testJavaPath + packagePath + separator + "QuickStartServer.java";
		FileUtils.writeFile(content, filePath);
		
		logger.info("QuickStartServer.java: {}", filePath);
	}
}
