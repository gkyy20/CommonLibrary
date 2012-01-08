package jp.gkyy.common.gae.exception;

public class GAEException extends Exception {
	private static final long serialVersionUID = -5268008512346726040L;
	private long errCode;
	
	public GAEException(String msg,long errCode){
		super(msg);
		this.errCode = errCode;
	}
	
	public long getErrCode(){
		return this.errCode;
	}
	
}
