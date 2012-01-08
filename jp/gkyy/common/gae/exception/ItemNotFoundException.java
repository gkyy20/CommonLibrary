package jp.gkyy.common.gae.exception;

public class ItemNotFoundException extends GAEException {
	private static final long serialVersionUID = -2405236994854278175L;
	private static final long ORG_ERR_NUM = -100;

	public ItemNotFoundException(String msg, long errCode) {
		super(msg,errCode + ORG_ERR_NUM );
	}
}
