package jp.gkyy.common.gae.dwh;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(identityType=IdentityType.APPLICATION ,detachable= "true")
public abstract class PersistenceData<T> {
    //パターン定義  
    protected static final String DATE_PATTERN = "yyyy/MM/dd";  
    protected static final String ITEM_SEP = String.valueOf('\u3000');
    protected static final String SEP = ";";

    abstract public String CreateOutputString();
    
	public void setFromRequest(String input){
		String[] values = input.split(ITEM_SEP);
		for( String value : values ){
			String[] items = value.split(SEP);
			if( items.length == 2 ){
				setValueOnField(items[0], items[1]);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void setValueOnField(String fieldName, String value){
		try {
			Object objValue = value;
			Class<? extends PersistenceData> c = this.getClass();
			Field f = c.getField(fieldName);
			if( "Date".equals(f.getType().getName()) ){
				SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
				objValue = format.parse(value);
			} else if ( "long".equals(f.getType().getName()) ){
				objValue = Long.parseLong(value);
			} else if ( "int".equals(f.getType().getName()) ){
				objValue = Integer.parseInt(value);
			} else if ( "double".equals(f.getType().getName()) ){
				objValue = Double.parseDouble(value);
			} else if ( "BigDecimal".equals(f.getType().getName()) ){
				objValue = new BigDecimal(value);
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
