package ddc.ftp;

public class FtpLiteException extends Exception {
	private static final long serialVersionUID = 1L;
	
    public FtpLiteException(String message) {
        super(message);
    }
    
    public FtpLiteException(Throwable cause) {
        super(cause);
    }
    
    public FtpLiteException(String message, Throwable cause) {
        super(message, cause);
    }

}
