package com.ketayao.ketacustom.generate;

import java.io.StringWriter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jetbrick.template.JetTemplate;

import com.ketayao.ketacustom.generate.util.FileType;
import com.ketayao.ketacustom.generate.util.FileUtils;
import com.ketayao.ketacustom.generate.util.Resources;
import com.ketayao.ketacustom.generate.vo.Column;
import com.ketayao.ketacustom.generate.vo.Table;

/**
 * @Description TODO
 * @author chen.bing
 *
 * @version 2015年2月26日 下午3:45:06
 */
public class GenerateCode extends AbstractGenerate implements Generate {
	FileType fileType = null;

	public GenerateCode(FileType javaFileType) {
		super();
		this.fileType = javaFileType;
	}

	@Override
	public void generate(Table table) throws Exception {
		model.put("tableName", table.getTableName().toLowerCase());
		model.put("className", table.getClazzName());
		model.put("instanceName", StringUtils.uncapitalize(table.getClazzName()));
		model.put("columns", table.getColumns());
		model.put("pk", table.getPk());

		// 特殊类型处理
		handleSpecial(table.getColumns());

		JetTemplate template = engine.getTemplate("code" + separator + fileType.getTemplate());

		StringWriter content = new StringWriter();
//		System.out.println("=====");
//		for (String key : model.keySet()) {
//			System.out.println(key);
//		}
//		System.out.println("=====");
		template.render(model, content);

		// 生成java
		if (fileType.getFileNameExtension().endsWith(".java")) {
			String filePath = javaPath + fileType.getJavaStorePath() + separator + table.getClazzName() + fileType.getFileNameExtension();
			FileUtils.writeFile(content.toString(), filePath);

			logger.info(fileType.getType() + ": {}", filePath);

			
		} else if(fileType.getFileNameExtension().endsWith(".xml")){//生成到DAOmapper
			String filePath = javaPath + fileType.getJavaStorePath() + separator + table.getClazzName() + fileType.getFileNameExtension();
			FileUtils.writeFile(content.toString(), filePath);

			logger.info(fileType.getType() + ": {}", filePath);
		}else if (fileType.getFileNameExtension().endsWith(".jetx")) {// 生成jetx
			String filePath = viewPath + Resources.TPL_REQUEST_MAPPING + separator+StringUtils.uncapitalize(table.getClazzName()) + fileType.getFileNameExtension();
			FileUtils.writeFile(content.toString(), filePath);

			logger.info(fileType.getType() + ": {}", filePath);
		}
	}

	@Override
	public void generate(List<Table> tables) throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * 特殊类型处理
	 * 
	 * @param columns
	 */
	private void handleSpecial(List<Column> columns) {
		boolean hasDate = false;
		boolean hasBigDecimal = false;
		for (Column column : columns) {
			if (column.getJavaType().equals("Date")) {
				hasDate = true;
			} else if (column.getJavaType().equals("BigDecimal")) {
				hasBigDecimal = true;
			}
		}

		model.put("hasDate", hasDate);
		model.put("hasBigDecimal", hasBigDecimal);
	}
}
