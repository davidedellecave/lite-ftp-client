package ddc.ftp;

public class FtpLiteConfig {
	// Remote conf
	private FtpServer ftpServer = new FtpServer();
	// private int retryLogin=3;
	private boolean preserveRemoteTimestamp = true;
	private boolean binaryTransfer = true;
	private boolean passiveMode = true;
	private boolean overwriteLocal = false;
	private boolean overwriteRemote = false;
	private boolean deleteRemoteLogically = true;
	private boolean deleteRemoteAfterDownload = false;
	private String workingPath = "/remote/path";

	//
	public FtpServer getFtpServer() {
		return ftpServer;
	}

	public void setFtpServer(FtpServer ftpServer) {
		this.ftpServer = ftpServer;
	}

	public boolean isPreserveRemoteTimestamp() {
		return preserveRemoteTimestamp;
	}

	public void setPreserveRemoteTimestamp(boolean preserveRemoteTimestamp) {
		this.preserveRemoteTimestamp = preserveRemoteTimestamp;
	}

	public boolean isBinaryTransfer() {
		return binaryTransfer;
	}

	public void setBinaryTransfer(boolean binaryTransfer) {
		this.binaryTransfer = binaryTransfer;
	}

	public boolean isPassiveMode() {
		return passiveMode;
	}

	public void setPassiveMode(boolean passiveMode) {
		this.passiveMode = passiveMode;
	}

	public boolean isOverwriteLocal() {
		return overwriteLocal;
	}

	public void setOverwriteLocal(boolean overwriteLocal) {
		this.overwriteLocal = overwriteLocal;
	}

	public boolean isOverwriteRemote() {
		return overwriteRemote;
	}

	public void setOverwriteRemote(boolean overwriteRemote) {
		this.overwriteRemote = overwriteRemote;
	}

	public boolean isDeleteRemoteLogically() {
		return deleteRemoteLogically;
	}

	public void setDeleteRemoteLogically(boolean deleteRemoteLogically) {
		this.deleteRemoteLogically = deleteRemoteLogically;
	}

	public String getWorkingPath() {
		return workingPath;
	}

	public void setWorkingPath(String workingPath) {
		this.workingPath = workingPath;
	}

	public boolean isDeleteRemoteAfterDownload() {
		return deleteRemoteAfterDownload;
	}

	public void setDeleteRemoteAfterDownload(boolean deleteRemoteAfterDownload) {
		this.deleteRemoteAfterDownload = deleteRemoteAfterDownload;
	}
	
}
