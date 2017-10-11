package ddc.ftp;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class FtpLiteClientPool {
	private ObjectPool<FtpLiteClient> pool = null;
	
    public FtpLiteClientPool(FtpLiteConfig config, int size) {
		super();
		GenericObjectPoolConfig c = new GenericObjectPoolConfig();
		c.setMaxTotal(size);
		c.setMaxIdle(size);
		c.setBlockWhenExhausted(true);
		pool = new GenericObjectPool<FtpLiteClient>(new FtpLitePoolFactory(config), c);
	}
    	
    public FtpLiteClient hold() throws FtpLiteException {
    	try {
			return pool.borrowObject();
		} catch (Exception e) {
			throw new FtpLiteException(e);
		}
    }
    
    public void release(FtpLiteClient c) throws FtpLiteException {
    	try {
			pool.returnObject(c);
		} catch (Exception e) {
			throw new FtpLiteException(e);
		}
    }
    
    public void close() throws FtpLiteException {
    	try {
			pool.clear();
		} catch (Exception e) {
			throw new FtpLiteException(e);
		}
    }

}
