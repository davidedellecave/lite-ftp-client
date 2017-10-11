package ddc.ftp;


public class FtpServer {
	public static final String ANONYMOUS="anonymous";
	private String host="localhost";		
	private int port=0;
	private String username="username";
	private String password="password";
	private FtpProxy ftpProxy = null;
	private FtpProxy httpProxy = null;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setHTTPTunnelProxy(FtpProxy proxy) {
		httpProxy = proxy;
	}
	
	public void setHTTPTunnelProxy(String proxyHost, int proxyPort, String proxyUser, String proxyPass) {
		httpProxy = new FtpProxy(proxyHost, proxyPort, proxyUser, proxyPass);
	}
	
	public FtpProxy getHTTPTunnelProxy() {
		return httpProxy;
	}

	public void setFtpProxy(FtpProxy proxy) {
		ftpProxy = proxy;
	}

	public void setFtpProxy(String proxyHost, int proxyPort, String proxyUser, String proxyPass) {
		ftpProxy = new FtpProxy(proxyHost, proxyPort, proxyUser, proxyPass);
	}
	
	public FtpProxy getFtpProxy() {
		return ftpProxy;
	}
	
	@Override
	public String toString() {
		return host + ":" + port + "@" + username;
	}
	
	
}
