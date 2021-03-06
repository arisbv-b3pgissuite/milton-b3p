package com.bradmcevoy.http;

import com.bradmcevoy.http.Request.Method;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PutHandler extends NewEntityHandler {
    
    private Log log = LogFactory.getLog(PutHandler.class);
    
    public PutHandler(HttpManager manager) {
        super(manager);
    }
    
    @Override
    public Request.Method method() {
        return Method.PUT;
    }       
    
    @Override
    protected boolean isCompatible(Resource handler) {
        return (handler instanceof PutableResource);
    }        

    @Override
    protected void process(HttpManager milton, Request request, Response response, CollectionResource resource, String newName) {
        PutableResource r = (PutableResource) resource;        
        log.debug("process: putting to: " + r.getName() );
        try {
            Long l = request.getContentLengthHeader();
            String ct = request.getContentTypeHeader();
            log.debug("PutHandler: creating resource of type: " + ct);
            r.createNew(newName, request.getInputStream(), l, ct );
            log.debug("PutHandler: DONE creating resource");
        } catch (IOException ex) {
            log.warn("IOException reading input stream. Probably interrupted upload: " + ex.getMessage());
            return;
        }
        response.setStatus( Response.Status.SC_CREATED );
        
        log.debug("process: finished");
    }
}