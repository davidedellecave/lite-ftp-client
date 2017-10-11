package ddc.ftp.matcher;

import ddc.ftp.FtpFileMatcher;
import ddc.ftp.FtpLiteFile;

public class OrMatcher implements FtpFileMatcher {
	private FtpFileMatcher matcher1;
	private FtpFileMatcher matcher2;
	
	public OrMatcher(FtpFileMatcher matcher1, FtpFileMatcher matcher2) {
		super();
		this.matcher1 = matcher1;
		this.matcher2 = matcher2;
	}
	
	@Override
	public boolean accept(FtpLiteFile file) {		
		return matcher1.accept(file) || matcher2.accept(file);
	}

	@Override
	public String toString() {
		return "((" + matcher1.toString() + ") OR (" + matcher2.toString() + "))";
	}
}
