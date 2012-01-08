package jp.gkyy.common.gae.exception;

public class StoreDataException extends GAEException {
	private static final long serialVersionUID = -4926936094978200332L;
	private static final long ORG_ERR_NUM = -400;

	public StoreDataException(String msg, long errCode) {
		super(msg, errCode + ORG_ERR_NUM );
	}
	public StoreDataException(Exception ex, long errCode){
		super(ex.getMessage(), errCode + ORG_ERR_NUM );
		this.setStackTrace(ex.getStackTrace());
	}
}
