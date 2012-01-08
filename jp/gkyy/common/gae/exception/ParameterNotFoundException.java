package jp.gkyy.common.gae.exception;

public class ParameterNotFoundException extends GAEException {
	private static final long serialVersionUID = -172006674016796621L;
	private static final long ORG_ERR_NUM = -300;

	public ParameterNotFoundException(String msg,long errCode) {
		super(msg,errCode + ORG_ERR_NUM );
	}
	public ParameterNotFoundException(Exception ex, long errCode) {
		super(ex.getMessage(), errCode+ ORG_ERR_NUM );
		super.setStackTrace(ex.getStackTrace());
	}
}
