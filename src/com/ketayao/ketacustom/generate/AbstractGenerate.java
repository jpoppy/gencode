package com.ketayao.ketacustom.generate;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import jetbrick.template.JetEngine;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ketayao.ketacustom.generate.util.Resources;

/**
 * @Description TODO
 * @author chen.bing
 *
 * @version 2015年2月26日 下午3:20:10
 */
public abstract class AbstractGenerate {
	protected static Logger logger = LoggerFactory.getLogger(AbstractGenerate.class);

	protected JetEngine engine;
	protected String tplPath;
	protected String javaPath;
	protected String javaResourcesPath;
	protected String testJavaPath;
	protected String testJavaResourcesPath;
	protected String viewPath;
	protected String sqlPath;
	protected String webappPath;
	protected String projectName;
	
	
	protected String separator;

	// 工程文件
	protected File projectFile;

	protected Map<String, Object> model;

	public AbstractGenerate() {
		init();
	}

	public void init() {
		try {
			// 获取文件分隔符
			separator = File.separator;

			String tmpPath = this.getClass().getClassLoader().getResource("").getPath();

			// 获取工程路径
			projectFile = new File(tmpPath);
			if (projectFile == null) {
				throw new IllegalArgumentException("projectFile path 读取失败 >>>" + tmpPath);
			}

			projectFile = projectFile.getParentFile();

			String projectPath = projectFile.getAbsolutePath();

			logger.info("Project Path: {}", projectPath);

			// 项目名称
			projectName = StringUtils.substring(projectPath.toString(), projectPath.toString().lastIndexOf(separator) + 1);
			logger.info("projectName : {}", projectName);

			// 模板文件路径
			tplPath = StringUtils.replace(URLDecoder.decode(AbstractGenerate.class.getResource("/").getPath(), "utf-8"), "/", separator);
			logger.info("Template Path: {}", tplPath);

			// Java文件路径
			javaPath = StringUtils.replace(projectPath + "/generate/src/", "/", separator);
			logger.info("Java Path: {}", javaPath);

			// javaResources路径
			javaResourcesPath = StringUtils.replace(projectPath + "/generate/src/", "/", separator);
			logger.info("javaResources Path: {}", javaResourcesPath);

			// Java test文件路径
			testJavaPath = StringUtils.replace(projectPath + "/generate/test/", "/", separator);
			logger.info("testJava Path: {}", testJavaPath);

			// javaResources test路径
			testJavaResourcesPath = StringUtils.replace(projectPath + "/generate/test/", "/", separator);
			logger.info("testJavaResources Path: {}", testJavaResourcesPath);

			// webappPath路径
			webappPath = StringUtils.replace(projectPath + "/src/main/webapp/", "/", separator);
			logger.info("webapp Path: {}", webappPath);

			// 视图文件路径
			viewPath = StringUtils.replace(projectPath + "/generate/views/", "/", separator);
			logger.info("View Path: {}", viewPath);

			sqlPath = StringUtils.replace(projectPath + "/generate/sql/", "/", separator);
			logger.info("sqlPath Path: {}", sqlPath);
			
			// 代码模板配置
			engine = JetEngine.create();
			
			// 定义模板变量
			model = new HashMap<String, Object>();
			model.put("packageName", Resources.TPL_PACKAGE_NAME);
			model.put("functionName", Resources.TPL_FUNCTION_NAME);
			model.put("requestMapping", Resources.TPL_REQUEST_MAPPING);

			model.put("pknEntity", Resources.PKN_ENTITY);
			model.put("pknDAO", Resources.PKN_DAO);
			model.put("pknService", Resources.PKN_SERVICE);
			model.put("pknServiceImpl", Resources.PKN_SERVICE_IMPL);
			model.put("pknController", Resources.PKN_CONTROLLER);

			model.put("projectName", projectName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
}
