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
			IClassNameConvertor mnc = (IClassNameConvertor)(Class.forName(mncName).newInstance());
			String className  = mnc.getClassName(req.getParameter("method"));
			IDataOperator ope = (IDataOperator)(Class.forName(className).newInstance());
			ope.operate(req);
			resp.getWriter().println("0");
		} catch (GAEException e) {
			long errCode = e.getErrCode() + -10000;
			resp.getWriter().println(errCode);
			resp.getWriter().println(e.getMessage());
			resp.getWriter().println(e.getStackTrace());
		} catch (InstantiationException e) {
			writeException(resp, e, "-19992");
		} catch (IllegalAccessException e) {
			writeException(resp, e, "-19993");
		} catch (ClassNotFoundException e) {
			writeException(resp, e, "-19994");
		} catch (SecurityException e) {
			writeException(resp, e, "-19995");
		} catch (IllegalArgumentException e) {
			writeException(resp, e, "-19996");
		} catch (Exception e) {
			writeException(resp, e, "-19997");
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
