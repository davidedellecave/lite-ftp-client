package ddc.ftp.matcher;

import ddc.ftp.FtpFileMatcher;
import ddc.ftp.FtpLiteFile;

public class TrueMatcher implements FtpFileMatcher {

	@Override
	public boolean accept(FtpLiteFile file) {
		return true;
	}

	@Override
	public String toString() {
		return " IS TRUE "; 
	}
}
