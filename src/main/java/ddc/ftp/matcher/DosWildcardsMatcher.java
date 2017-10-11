package ddc.ftp.matcher;

import ddc.ftp.FtpFileMatcher;
import ddc.ftp.FtpLiteFile;
import ddc.support.util.StringMatcher;

public class DosWildcardsMatcher implements FtpFileMatcher {
	private String matcher;

	public DosWildcardsMatcher(String matcher) {
		super();
		this.matcher = matcher;
	}

	private boolean isMatched(String source) {
		String rx =  StringMatcher.wildcardToRegex(matcher);
		return source.matches(rx);
	}

	@Override
	public boolean accept(FtpLiteFile file) {
		return isMatched(file.getFilename());
	}
	
	@Override
	public String toString() {
		return " x.Name DosWildcardsMatch (" + matcher.toString() + ")"; 
	}
}
