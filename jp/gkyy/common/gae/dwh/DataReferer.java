package jp.gkyy.common.gae.dwh;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import jp.gkyy.common.gae.exception.GAEException;

public abstract class DataReferer implements IDataReferer {

	@Override
	public abstract ArrayList<String> operate(HttpServletRequest req) throws GAEException;

}
