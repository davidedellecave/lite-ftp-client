package ddc.ftp.matcher;

import java.text.ParseException;
import java.util.Date;

import ddc.ftp.FtpFileMatcher;
import ddc.ftp.FtpLiteFile;
import ddc.support.util.DateRange;
import ddc.support.util.DateUtil;

public class DateMatcher implements FtpFileMatcher {
	private DateRange matcher;

	public DateMatcher(DateRange matcher) {
		super();
		this.matcher = matcher;
	}
	
	public DateMatcher(String absoluteRange) throws ParseException {
		String[] t = absoluteRange.split(",");
		if (t.length==2) {
			Date d1 = DateUtil.parseToDate(t[0].trim(), "yyyy/MM/dd HH:mm:ss");
			Date d2 = DateUtil.parseToDate(t[1].trim(), "yyyy/MM/dd HH:mm:ss");
			matcher = new DateRange(d1, d2);		
		}
	}
		
	@Override
	public boolean accept(FtpLiteFile file) {		
		return matcher.contains(file.getTimestamp(), true);
	}
	
	@Override
	public String toString() {
		return " x.ModifiedDate IN (" + matcher.toString() + ")"; 
	}
}