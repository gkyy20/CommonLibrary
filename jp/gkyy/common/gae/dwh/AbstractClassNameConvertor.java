package jp.gkyy.common.gae.dwh;

import java.util.HashMap;

import jp.gkyy.common.gae.dwh.IClassNameConvertor;

public class AbstractClassNameConvertor implements IClassNameConvertor{
	protected static final HashMap<String, String> ClassMap = new HashMap<String, String>();

	@Override
	public String getClassName(String key) {
		return getFromMap(key, ClassMap );
	}
	
	private String getFromMap(String key, HashMap<String, String> map ){
		String keyLowCase = key.toLowerCase();
		if( map.containsKey(keyLowCase) ){
			return map.get(keyLowCase);
		} else {
			throw new IllegalArgumentException("Method Token invalid");
		}
	}

}
