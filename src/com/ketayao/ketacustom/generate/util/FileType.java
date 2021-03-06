package com.ketayao.ketacustom.generate.util;

import java.io.File;
import java.util.regex.Matcher;



/**
 * 生成文件的文件类型
 * 
 */
public enum FileType {
	ENTITY("entity", Resources.PKN_ENTITY,Resources.JAVA_ENTITY_TEMPLATE,".java"), 
	DAO("dao", Resources.PKN_DAO,Resources.JAVA_DAO_TEMPLATE,"Mapper.java"), 
	DAOMAPPER("daomapper", Resources.PKN_DAO,Resources.JAVA_DAO_MAPPER_TEMPLATE,"Mapper.xml"), 
	SERVICE("service", Resources.PKN_SERVICE,Resources.JAVA_SERVICE_TEMPLATE,"Service.java"), 
	SERVICE_IMPL("service.impl", Resources.PKN_SERVICE_IMPL,Resources.JAVA_SERVICE_IMPL_TEMPLATE,"ServiceImpl.java"), 
	CONTROLLER("controller", Resources.PKN_CONTROLLER,Resources.JAVA_CONTROLLER_TEMPLATE,"Controller.java"), 
	
	JETX_LIST("jetx_list", null,Resources.JETX_LIST_TEMPLATE,"List.jetx"),
	JETX_CREATE("jetx_add", null,Resources.JETX_CREATE_TEMPLATE,"Add.jetx"),
	JETX_UPDATE("jetx_edit", null,Resources.JETX_UPDATE_TEMPLATE,"Edit.jetx"),
	JETX_VIEW("jetx_view", null,Resources.JETX_VIEW_TEMPLATE,"view.jetx");
	
	
	// 成员变量
	private String type;//文件类型
	private String pakage;//包声明
	private String template;//模板
	private String fileNameExtension;//文件扩展

	// 构造方法
	private FileType(String type, String pkage,String template,String fileNameExtension) {
		this.type = type;
		this.pakage = pkage;
		this.template=template;
		this.fileNameExtension=fileNameExtension;
	}

	public String getType() {
		return type;
	}

	public String getPakage() {
		return pakage;
	}
	
	public String getTemplate(){
		return template;
	}
	
	public String getFileNameExtension(){
		return fileNameExtension;
	}
	
	/**
	 * 根据Java文件类型获取存储地址
	 * 
	 * @param type
	 * @return
	 */
	public String getJavaStorePath() {
		String packageDecl = null;
		if (getPakage() == null || "".equals(getPakage()))
			packageDecl = Resources.TPL_PACKAGE_NAME + "." + getType();
		else
			packageDecl = getPakage();
		packageDecl = packageDecl.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
		return packageDecl;
	}

	public static void main(String[] args) {
		System.out.println(FileType.ENTITY.getPakage());
		System.out.println(FileType.ENTITY.getTemplate());
	}

}
