package ddc.ftp;

import java.nio.file.Path;

import ddc.support.util.DateUtil;

public class FtpLiteFile {
	public static final int TYPE_FILE = 0;
	public static final int TYPE_DIRECTORY = 1;
	public static final int TYPE_SYMBOLIC_LINK = 2;
	public static final int TYPE_UNKNOWN = 3;
	private Path path = null;
	private long timestamp = 0;
	private long size = 0;
	private int type = TYPE_UNKNOWN;

	//
	public Path getPath() {
		return path;
	}

	public String getFilename() {
		return path.getFileName().toString();
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isFile() {
		return type == TYPE_FILE;
	}

	public boolean isDirectory() {
		return type == TYPE_DIRECTORY;
	}

	@Override
	public String toString() {
		if (isFile()) {
			return "file:[" + path.toString() + "] size:[" + size + "] date:[" + DateUtil.formatISO(timestamp) + "]";
		} else  {
			return "dir:[" + path.toString() + "] date:[" + DateUtil.formatISO(timestamp) + "]";
		}

	}

}
