package ddc.ftp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import ddc.support.util.Chronometer;
import ddc.support.util.FilePair;
import ddc.support.util.FileUtils;

public class ParallelFtpClient {
	private Logger logger = Logger.getLogger(ParallelFtpClient.class.getName());
	
	private int maxConnection = 1;
	private FtpLiteClientPool pool = null;
	private SummaryStatistics sizeStats = new SummaryStatistics();

	public ParallelFtpClient(FtpLiteConfig config, int maxConnection) {
		this.maxConnection = maxConnection;
		pool = new FtpLiteClientPool(config, maxConnection);
	}

	synchronized private void addBytes(long bytes) {
		sizeStats.addValue(bytes);
	}
	
	public void download(List<FilePair> list) throws FtpLiteException {
		Chronometer chron = new Chronometer();
		try {
			doDownload(list);
		} catch (InterruptedException e) {
			throw new FtpLiteException(e);
		} finally {
			pool.close();
		}
		long bytesOverSecs = (long)(sizeStats.getSum()/chron.getElapsed()*1000);		
		logger.info("Download elapsed:[" + chron + "] #:[" + sizeStats.getN() +"] size:[" + FileUtils.formatBytes((long)sizeStats.getSum()) + "] throughput:[" + FileUtils.formatBytes(bytesOverSecs) + "/sec]");
	}

	public void upload(List<FilePair> list) throws FtpLiteException {
		Chronometer chron = new Chronometer();
		try {
			doUpload(list);
		} catch (InterruptedException | FtpLiteException e) {
			throw new FtpLiteException(e);
		}
		logger.info("Upload elapsed:[" + chron + "]");
	}

//	private void doDownload(List<FilePair> list) throws InterruptedException, FtpLiteException {
//		final ExecutorService executor = Executors.newFixedThreadPool(maxConnection);
//		for (FilePair p : list) {
//			Runnable r = new Runnable() {
//				public void run() {
//					FtpLiteClient client = null;
//					try {
//						client = pool.hold();
//						client.download(p.source, p.target);
//					} catch (FtpLiteException e) {
//					} finally {
//						try {
//							pool.release(client);
//						} catch (FtpLiteException e) {}
//					}
//				}
//			};
//			executor.execute(r);
//		}
//		;
//		executor.shutdown();
//		executor.awaitTermination(1, TimeUnit.DAYS);
//		pool.close();
//	}
	
	private void doDownload(List<FilePair> list) throws InterruptedException, FtpLiteException {
		List<Thread> threads = new ArrayList<>();
		for (final FilePair p : list) {
			final FtpLiteClient client = pool.hold();			
			Runnable r = new Runnable() {
				public void run() {
					try {						
						FtpLiteFile file = client.download(p.source, p.target);
						addBytes(file.getSize());
					} catch (FtpLiteException e) {
						logger.severe(e.getMessage());
					} finally {
						try {
							pool.release(client);
						} catch (FtpLiteException e) {}
					}
				}
			};
			Thread t = new Thread(r);
			threads.add(t);
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
	}

	private void doUpload(List<FilePair> list) throws InterruptedException, FtpLiteException {
		final ExecutorService executor = Executors.newFixedThreadPool(maxConnection);
		for (final FilePair p : list) {
			Runnable r = new Runnable() {
				public void run() {
					FtpLiteClient client = null;
					try {
						client = pool.hold();
						client.upload(p.source, p.target);
					} catch (FtpLiteException e) {
					} finally {
						try {
							pool.release(client);
						} catch (FtpLiteException e) {}
					}
				}
			};
			executor.execute(r);
		}
		;
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		pool.close();
	}

}
