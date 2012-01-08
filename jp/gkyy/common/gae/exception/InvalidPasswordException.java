package jp.gkyy.common.gae.exception;

public class InvalidPasswordException extends GAEException {
	private static final long serialVersionUID = -8512907169874247111L;
	private static final long ORG_ERR_NUM = -500;

	public InvalidPasswordException(String msg, long errCode) {
		super(msg, errCode + ORG_ERR_NUM);
	}
	public InvalidPasswordException(Exception ex, long errCode){
		super(ex.getMessage(), errCode + ORG_ERR_NUM );
		this.setStackTrace(ex.getStackTrace());
	}
}
