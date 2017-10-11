package ddc.ftp.matcher;

import ddc.ftp.FtpFileMatcher;
import ddc.ftp.FtpLiteFile;

public class PathMatcher implements FtpFileMatcher {
	private String matcher;

	public PathMatcher(String matcher) {
		super();
		this.matcher = matcher;
	}

	@Override
	public boolean accept(FtpLiteFile file) {
		String source = file.getPath().getParent().toString();
		return source.contains(matcher);
	}
	
	@Override
	public String toString() {
		return " x.Path contains ('" + matcher.toString() + "')"; 
	}
}
