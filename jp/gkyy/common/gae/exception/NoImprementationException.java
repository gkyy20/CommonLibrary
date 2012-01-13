package jp.gkyy.common.gae.exception;

@SuppressWarnings("serial")
public class NoImprementationException extends GAEException {
	private static final long ORG_ERR_NUM = -700;

	public NoImprementationException(long errCode) {
		super("This method is not implemented",errCode + ORG_ERR_NUM );
	}
	public NoImprementationException(Exception ex, long errCode) {
		super(ex.getMessage(), errCode+ ORG_ERR_NUM );
		super.setStackTrace(ex.getStackTrace());
	}

}
