package jp.gkyy.common.gae.dwh;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.gkyy.common.gae.exception.GAEException;

public interface IDataReferer {
	public ArrayList<String> operate(HttpServletRequest req) throws GAEException;
}
