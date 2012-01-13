package jp.gkyy.common.gae.dwh;

import javax.servlet.http.HttpServletRequest;

import jp.gkyy.common.gae.exception.GAEException;

public interface IDataOperator {
	public void insert(HttpServletRequest req) throws GAEException;
	public void delete(HttpServletRequest req) throws GAEException;
	public void update(HttpServletRequest req) throws GAEException;
}
