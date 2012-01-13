package jp.gkyy.common.gae.dwh;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
		
		// パラメータから操作を取得
		try{
			String mncName = DWHConfigManager.convert(MNG_METHOD);
			IMethodNameConvertor mnc = (IMethodNameConvertor)(Class.forName(mncName).newInstance());
			String className  = mnc.getClassName(req.getParameter("method"));
			String methodName  = mnc.getMethodName(req.getParameter("method"));
			IDataOperator ope;
			ope = (IDataOperator)(Class.forName(className).newInstance());
			Method method = ope.getClass().getMethod(methodName, new Class[]{ HttpServletRequest.class } );
			method.invoke(ope, new Object[]{ req } );
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
		} catch (NoSuchMethodException e) {
			writeException(resp, e, "-19996");
		} catch (IllegalArgumentException e) {
			writeException(resp, e, "-19997");
		} catch (InvocationTargetException e) {
			writeException(resp, e, "-19998");
		} catch (Exception e) {
			writeException(resp, e, "-19999");
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
