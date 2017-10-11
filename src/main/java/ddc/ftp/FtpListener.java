package ddc.ftp;

import java.io.IOException;

public interface FtpListener {
	public FtpListenerAction preVisitDirectory(FtpLiteFile dir) throws IOException;
	public FtpListenerAction postVisitDirectory(FtpLiteFile dir) throws IOException;
	public FtpListenerAction visitFile(FtpLiteFile file) throws IOException;
//	public FtpListenerAction visitFileFailed(Path file, IOException exc) throws IOException;
}