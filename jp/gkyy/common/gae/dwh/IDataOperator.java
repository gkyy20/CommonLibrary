package jp.gkyy.common.gae.dwh;

import javax.servlet.http.HttpServletRequest;

import jp.gkyy.common.gae.exception.GAEException;

public interface IDataOperator {
	public void operate(HttpServletRequest req) throws GAEException;
}
