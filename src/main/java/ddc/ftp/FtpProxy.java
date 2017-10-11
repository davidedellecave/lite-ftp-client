package ddc.ftp;

public class FtpProxy {

	private String proxyHost;
	private int proxyPort;
	private String proxyUser;
	private String proxyPass;
	
	public String getProxyHost() {
		return proxyHost;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public String getProxyUser() {
		return proxyUser;
	}

	public String getProxyPass() {
		return proxyPass;
	}

	public FtpProxy(String proxyHost, int proxyPort, String proxyUser, String proxyPass) {
		super();
		this.proxyHost = proxyHost;
		this.proxyPort = proxyPort;
		this.proxyUser = proxyUser;
		this.proxyPass = proxyPass;
	}	
}
