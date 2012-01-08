package jp.gkyy.common.gae.dwh;

import java.util.HashMap;

import jp.gkyy.common.gae.dwh.IMethodNameConvertor;

public class AbstractMethodNameConvertor implements IMethodNameConvertor{
	protected final static String PACKAGE_NAME = "jp.gkyy.common.gae.dwh.";
	protected static final HashMap<String, String> MethodMap = new HashMap<String, String>();
	
	@Override
	public String convert(String key) {
		String keyLowCase = key.toLowerCase();
		if( MethodMap.containsKey(keyLowCase) ){
			return PACKAGE_NAME + MethodMap.get(keyLowCase);
		} else {
			throw new IllegalArgumentException("Method Token invalid");
		}
	}

}
