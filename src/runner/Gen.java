package runner;

import java.io.File;
import java.io.IOException;

import com.ketayao.ketacustom.generate.GenerateFactory;

/**
 * @Description TODO
 * @author chen.bing
 *
 * @version 2015年2月27日 上午11:55:53
 */
public class Gen {
	public static void main(String[] args) {
		GenerateFactory factory = new GenerateFactory();
		factory.genJavaTemplate();
		factory.genJextTemplate();
		/**
		 * 打开生成的文件夹
		 */
		File f = new File("");
		String outPutPathS = f.getAbsolutePath() + File.separator + "generate";
		try {
			Runtime.getRuntime().exec("cmd.exe /c start " + outPutPathS);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
