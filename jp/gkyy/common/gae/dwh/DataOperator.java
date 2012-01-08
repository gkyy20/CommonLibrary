package jp.gkyy.common.gae.dwh;

import javax.servlet.http.HttpServletRequest;

import jp.gkyy.common.gae.exception.GAEException;


public abstract class DataOperator implements IDataOperator {

	@Override
	public abstract void operate(HttpServletRequest req) throws GAEException;
	
}
