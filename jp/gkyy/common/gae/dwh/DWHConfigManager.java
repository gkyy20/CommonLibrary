package jp.gkyy.common.gae.dwh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import jp.gkyy.common.gae.exception.GAEException;

public class DWHConfigManager {
	private static final long ERR_CODE = -100;
	private static final String SEPARATOR = "=";
	private static HashMap<String, String> methodName = null;
	
	private static void loadMethodNameFile() throws GAEException{
		try {
			methodName =  new HashMap<String, String>();
			FileInputStream fis = new FileInputStream("WEB-INF/dwh.cfg");
			InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			while((line = br.readLine()) != null ){
				String[] values = line.split(SEPARATOR);
				if( values.length == 2 ){
					methodName.put(values[0], values[1]);
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new GAEException("Encoding(UTF8) not supported!!", ERR_CODE);
		} catch (FileNotFoundException e) {
			throw new GAEException("Config File not Fould!!", ERR_CODE);
		} catch (IOException e) {
			throw new GAEException("Can't open Config File!!", ERR_CODE);
		}
	}
	
	public static String convert(String key) throws GAEException {
		if( methodName == null ) loadMethodNameFile();
		return methodName.get(key);
	}
}
