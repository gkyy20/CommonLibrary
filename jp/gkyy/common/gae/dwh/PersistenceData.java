package jp.gkyy.common.gae.dwh;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.gkyy.common.gae.exception.GAEException;

public abstract class PersistenceData {
    //パターン定義  
    protected static final String DATE_PATTERN = "yyyy/MM/dd";  
    protected static final String ITEM_SEP = "&";
    protected static final String SEP = "=";

    abstract public String createOutputString();
    
    @SuppressWarnings("unchecked")
	public void setFromRequest(HttpServletRequest request) throws GAEException{
    	Map map = request.getParameterMap();
    	for( Object key : map.keySet()){
    		if( key instanceof String ){
    			Object value = map.get(key);
    			if( value instanceof String[] ){
    				setValueOnField( (String)key, ((String[])value)[0]);
    			}
    		}
    	}
    }
    
	public void setFromRequest(String input) throws GAEException{
		String[] values = input.split(ITEM_SEP);
		for( String value : values ){
			String[] items = value.split(SEP);
			if( items.length == 2 ){
				setValueOnField(items[0], items[1]);
			}
		}
	}
	
	protected void setValueOnField(String fieldName, String value){
		if( "".equals(value)) return;
		try {
			Object objValue = null;
			Class<? extends PersistenceData> c = this.getClass();
			Field f = c.getDeclaredField(fieldName);
			f.setAccessible(true);
			if( "java.util.Date".equals(f.getType().getName()) ){
				SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
				objValue = format.parse(value);
			} else if ( "long".equals(f.getType().getName()) ){
				objValue = Long.parseLong(value);
			} else if ( "int".equals(f.getType().getName()) ){
				objValue = Integer.parseInt(value);
			} else if ( "double".equals(f.getType().getName()) ){
				objValue = Double.parseDouble(value);
			} else if ( "java.math.BigDecimal".equals(f.getType().getName()) ){
				objValue = new BigDecimal(value);
			} else {
				objValue = new String(value);
			}
			f.set(this, objValue);
		} catch (Exception e) {
			// Exceptionは無視
		}
	}

    //Date日付型をString文字列型へ変換  
    protected static String date2string(Date date) {  
        DateFormat df = new SimpleDateFormat(DATE_PATTERN);  
        return df.format(date);
    }
}
