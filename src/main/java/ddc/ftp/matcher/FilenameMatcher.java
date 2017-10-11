package ddc.ftp.matcher;

import ddc.ftp.FtpFileMatcher;
import ddc.ftp.FtpLiteFile;

public class FilenameMatcher implements FtpFileMatcher {
	private String matcher;

	public FilenameMatcher(String matcher) {
		super();
		this.matcher = matcher;
	}

	@Override
	public boolean accept(FtpLiteFile file) {
		String source = file.getFilename();
		return source.contains(matcher);
	}
	
	@Override
	public String toString() {
		return " x.Filename contains ('" + matcher.toString() + "')"; 
	}
}
