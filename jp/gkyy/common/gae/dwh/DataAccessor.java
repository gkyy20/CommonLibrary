package jp.gkyy.common.gae.dwh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.gkyy.common.gae.exception.GAEException;

@SuppressWarnings("serial")
public class DataAccessor extends HttpServlet {
	private static final String REF_METHOD = "RefMethod";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		
		// パラメータから操作を取得
		try{
			String mncName = DWHConfigManager.convert(REF_METHOD);
			IClassNameConvertor mnc = (IClassNameConvertor)(Class.forName(mncName).newInstance());
			String className  = mnc.getClassName(req.getParameter("method"));
			IDataReferer ope = (IDataReferer)(Class.forName(className).newInstance());
			ArrayList<String> ret = ope.operate(req);
			resp.getWriter().println("0");
			for(int i = 0; i < ret.size(); i++){
				resp.getWriter().println(ret.get(i));				
			}
		} catch (GAEException e) {
			long errCode = e.getErrCode() + -20000;
			resp.getWriter().println(errCode);
			resp.getWriter().println(e.getMessage());
			resp.getWriter().println(e.getStackTrace());
		} catch (Exception e) {
			writeException(resp,e,"-29999");
		}
	}

	private void writeException(HttpServletResponse resp,
			Exception e,
			String errCode ) throws IOException{
		resp.getWriter().println(errCode);
		resp.getWriter().println(e.getMessage());
		resp.getWriter().println(e.getStackTrace());
		writeInnerException( resp, e);
	}

	private void writeInnerException(HttpServletResponse resp,
									 Throwable e ) throws IOException{
		if( e.getCause() != null ){
			resp.getWriter().println(e.getCause().getMessage());
			resp.getWriter().println(e.getCause().getStackTrace());
			writeInnerException(resp, e.getCause() );
		}
	}
}
