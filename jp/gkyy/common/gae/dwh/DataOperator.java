package jp.gkyy.common.gae.dwh;

import javax.servlet.http.HttpServletRequest;

import jp.gkyy.common.gae.exception.GAEException;


public abstract class DataOperator implements IDataOperator {

	@Override
	public abstract void insert(HttpServletRequest req) throws GAEException;

	@Override
	public abstract void delete(HttpServletRequest req) throws GAEException;

	@Override
	public abstract void update(HttpServletRequest req) throws GAEException;

}
