package com.ketayao.ketacustom.generate;

import java.io.IOException;

import jetbrick.template.JetTemplate;

import org.apache.commons.lang3.StringUtils;

import com.ketayao.ketacustom.generate.util.FileUtils;
import com.ketayao.ketacustom.generate.util.JetUtils;
import com.ketayao.ketacustom.generate.vo.Table;

/**
 * @Description 生成sql语句
 * @author chen.bing
 *
 * @version 2015年3月2日 上午10:27:39
 */
public class GenerateSettings extends AbstractGenerate {
	public GenerateSettings() {
		super();
	}

	public void generate(Table table) throws IOException {
		model.put("tableName", table.getTableName().toLowerCase());
		model.put("className", table.getClazzName());
		model.put("instanceName", StringUtils.uncapitalize(table.getClazzName()));
		model.put("columns", table.getColumns());
		model.put("pk", table.getPk());

		String sqlIdPrefix = (String) model.get("requestMapping");

		if (sqlIdPrefix != null) {
			sqlIdPrefix = sqlIdPrefix.replaceAll("/", "_");
		}
		
		model.put("sqlIdPrefix", sqlIdPrefix);

		JetTemplate template = engine.getTemplate("settings" + separator + "setings.jetx");
		String content = JetUtils.renderTemplate(template, model);

		String filePath = sqlPath + separator + "sqls.sql";
		FileUtils.writeFile(content, filePath);
	}
}
