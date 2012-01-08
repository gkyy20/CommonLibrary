package jp.gkyy.common.gae.dwh;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.gkyy.common.gae.exception.GAEException;

@SuppressWarnings("serial")
public class DataReferer extends HttpServlet {
	private static final String REF_METHOD = "RefMethod";

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("utf-8");
		
		// ƒpƒ‰ƒ[ƒ^‚©‚ç‘€ì‚ğæ“¾
		try{
			String mncName = DWHConfigManager.convert(REF_METHOD);
			IMethodNameConvertor mnc = (IMethodNameConvertor)(Class.forName(mncName).newInstance());
			String className  = mnc.convert(req.getParameter("method"));
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
			resp.getWriter().println("-29999");
			resp.getWriter().println(e.getMessage());
			resp.getWriter().println(e.getStackTrace());
		}

	}
}
