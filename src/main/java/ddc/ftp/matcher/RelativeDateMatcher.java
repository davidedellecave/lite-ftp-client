package ddc.ftp.matcher;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import ddc.ftp.FtpFileMatcher;
import ddc.ftp.FtpLiteFile;
import ddc.support.util.DateRange;
import ddc.support.util.LRange;
import ddc.support.util.Timespan;

public class RelativeDateMatcher implements FtpFileMatcher {
	private LRange matcher;

//	public RelativeDateMatcher(LRange matcher) {
//		super();
//		this.matcher = matcher;
//	}
	
	public RelativeDateMatcher(String range) {
		super();
		String[] t = range.split(",");
		if (t.length==2) { 			
			long d1 = parseBound(t[0].trim());
			long d2 = parseBound(t[1].trim());
			matcher = new LRange(d1, d2);
		} else {
			System.err.println("Wrong format range:[" + range + "] Usage:[<integer> <unit>, <integer> <unit>]");
		}
	}
	
	//For example, to convert 10 minutes to milliseconds, use: TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES)
	private long parseBound(String bound) {
		long b = 0;
		String[] t = bound.split(" ");
		if (t.length==2) { 
			long d1 = Long.parseLong(t[0].trim());
			TimeUnit u1 = TimeUnit.valueOf(t[1].trim());
			b = TimeUnit.MILLISECONDS.convert(d1, u1);
		} else {
			System.err.println("Wrong format range:[" + bound + "] Usage:[<integer> <unit>, <integer> <unit>]");
		}
		return b;
	}


	private boolean isMatched(Date date) {
		Date now = new Date();
		LRange r = matcher.clone();
		r.add(now.getTime());
		DateRange range = new DateRange(r);
		return range.contains(date, true);
	}
	
	private boolean isMatched(long timestamp) {
		return isMatched(new Date(timestamp));
	}

	@Override
	public boolean accept(FtpLiteFile file) {
		return isMatched(file.getTimestamp());
	}
	
	@Override
	public String toString() {
		String hr1 = matcher.getLower() < 0 ? "-" + Timespan.getHumanReadable(matcher.getLower()*-1) : Timespan.getHumanReadable(matcher.getLower()); 
		String hr2 = matcher.getUpper() < 0 ? "-" + Timespan.getHumanReadable(matcher.getUpper()*-1) : Timespan.getHumanReadable(matcher.getUpper());
		String s = " x.ModifiedDate IN (" + hr1 + "," +  hr2 +")";
//		String s = " x.ModifiedDate IN (" + matcher.toString() +")";
		return s;
	}
}