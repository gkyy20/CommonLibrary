package jp.gkyy.common.gae.exception;

public class EncryptException extends GAEException {
	private static final long serialVersionUID = 967420795541300811L;
	private static final long ORG_ERR_NUM = -600;

	public EncryptException(String msg,long errCode) {
		super(msg,errCode + ORG_ERR_NUM );
	}
	public EncryptException(Exception ex, long errCode) {
		super(ex.getMessage(), errCode+ ORG_ERR_NUM );
		super.setStackTrace(ex.getStackTrace());
	}

}
