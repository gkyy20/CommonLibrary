package jp.gkyy.common.gae.dwh;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.gkyy.common.gae.exception.GAEException;

@SuppressWarnings("serial")
public class DataManager extends HttpServlet {
	private static final String MNG_METHOD = "MngMethod";

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		
		// ÉpÉâÉÅÅ[É^Ç©ÇÁëÄçÏÇéÊìæ
		try{
			String mncName = DWHConfigManager.convert(MNG_METHOD);
			IMethodNameConvertor mnc = (IMethodNameConvertor)(Class.forName(mncName).newInstance());
			String className  = mnc.convert(req.getParameter("method"));
			IDataOperator ope = (IDataOperator)(Class.forName(className).newInstance());
			ope.operate(req);
			resp.getWriter().println("0");
		} catch (GAEException e) {
			long errCode = e.getErrCode() + -10000;
			resp.getWriter().println(errCode);
			resp.getWriter().println(e.getMessage());
			resp.getWriter().println(e.getStackTrace());
		} catch (Exception e) {
			resp.getWriter().println("-19999");
			resp.getWriter().println(e.getMessage());
			resp.getWriter().println(e.getStackTrace());
		}
	}
}
