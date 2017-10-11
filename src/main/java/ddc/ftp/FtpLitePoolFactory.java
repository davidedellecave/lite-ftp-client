package ddc.ftp;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class FtpLitePoolFactory extends BasePooledObjectFactory<FtpLiteClient> {
	private FtpLiteConfig config = null;
	
    public FtpLitePoolFactory(FtpLiteConfig config) {
		super();
		this.config = config;
	}


	@Override
    public FtpLiteClient create() {
        return new FtpLiteClient(config);
    }

    /**
     * Use the default PooledObject implementation.
     */
    @Override
    public PooledObject<FtpLiteClient> wrap(FtpLiteClient p) {
//    	System.out.println("wrap");
        return new DefaultPooledObject<FtpLiteClient>(p);
    }
    
    @Override
    public void destroyObject(PooledObject<FtpLiteClient> p) throws Exception {
//    	System.out.println("destroyObject");
    	p.getObject().disconnect();
    }
    
    @Override
    public boolean validateObject(PooledObject<FtpLiteClient> p) {
//    	System.out.println("validateObject");
    	return true;
    }

    @Override
    public void activateObject(PooledObject<FtpLiteClient> p) throws FtpLiteException  {
//    	System.out.println("activateObject");
    	if (p.getObject().isConnected()) {
    		return;
    	} else {
    		p.getObject().connect();				
    	}
    }
    
    @Override
    public void passivateObject(PooledObject<FtpLiteClient> p)  {
//    	System.out.println("passivateObject");
    }

}
