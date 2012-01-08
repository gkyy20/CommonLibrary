package jp.gkyy.common.gae.exception;

public class KeyDuplicateException extends GAEException {
	private static final long serialVersionUID = 1L;
	private static final long ORG_ERR_NUM = -200;

	public KeyDuplicateException(String msg, long errCode) {
		super(msg,errCode + ORG_ERR_NUM );
	}
}
