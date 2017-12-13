package cn.enixlin.jmrc.asone.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import sun.misc.BASE64Encoder;

public class Tools {

	public Tools() {

	}

	/**
	 * 利用MD5进行加密 　　
	 * 
	 * @param str
	 *            待加密的字符串 　　
	 * @return 加密后的字符串 　　
	 * @throws NoSuchAlgorithmException
	 *             没有这种产生消息摘要的算法 　　 * @throws UnsupportedEncodingException 　　
	 */
	public String EncoderByMd5(String str) {
		// 确定计算方法
		MessageDigest md5;
		String newstr=null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newstr;
	}
	
	/**
	 * 在java环境里运行javascript代码
	 * @param str
	 * @return
	 * @throws ScriptException
	 * @throws NoSuchMethodException
	 * @throws IOException
	 */
	public String jsMD5(String str) throws ScriptException, NoSuchMethodException, IOException{
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");     

		String jsFileName = "md5.js";   // 读取js文件   

		FileReader reader = new FileReader(jsFileName);   // 执行指定脚本   
		engine.eval(reader);   

		String minwen = null;
		if(engine instanceof Invocable) {    
		Invocable invoke = (Invocable)engine;    // 调用hex_md5方法，并传入参数    

		// c = merge(2, 3);    

		minwen = (String)invoke.invokeFunction("hex_md5",str);    

		System.out.println("S = " + minwen);   
		}   

		reader.close();  
		return minwen;

	}
	
	
	
}
