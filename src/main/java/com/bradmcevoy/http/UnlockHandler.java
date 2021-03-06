
package com.bradmcevoy.http;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bradmcevoy.http.Request.Method;

public class UnlockHandler extends ExistingEntityHandler {

    private Log log = LogFactory.getLog(UnlockHandler.class);

    public UnlockHandler(HttpManager manager) {
        super(manager);
    }
    
    @Override
    protected void process(HttpManager milton, Request request, Response response, Resource resource) {
        LockableResource r = (LockableResource) resource;
        String sToken = request.getLockTokenHeader();        
        sToken = LockHandler.parseToken(sToken);
        log.debug("unlocking token: " + sToken);
        r.unlock(sToken);
    }
    
    @Override
    public Request.Method method() {
        return Method.UNLOCK;
    }   
    
    @Override
    protected boolean isCompatible(Resource handler) {
        return handler instanceof LockableResource;
    }

    
}
