package com.bradmcevoy.http;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface Response {

    public final static String HTTP = "text/html";
    public final static String IMAGE_JPG = "image/jpg";
    public final static String MULTIPART = "multipart/form-data";
    public final static String XML = "text/xml; charset=UTF-8";

    
    
    public enum ContentType {
        HTTP,
        MULTIPART,
        IMAGE_JPG,
        XML;
    }
    
    public enum ContentEncoding {
        GZIP("gzip");
                
        public String code;
        
        ContentEncoding(String code) {
            this.code = code;
        }
    }
    
    enum Header {
        CACHE_CONTROL("Cache-Control"),
        WWW_AUTHENTICATE("WWW-Authenticate"),
        CONTENT_LENGTH("Content-Length"),
        CONTENT_TYPE("Content-Type"),
        CONTENT_ENCODING("Content-Encoding"),
        LOCATION("Location"),
        ALLOW("Allow"),
        DAV("DAV"),
        DATE("DATE"),
        LAST_MODIFIED("Last-Modified"),
        LOCK_TOKEN("Lock-Token"),
        EXPIRES("Expires"),
        ETAG("Etag"),
        CONTENT_RANGE("Content-Range");
        
            
        public String code;                

        Header( String code ) {
            this.code = code;
        }                
    }
    
    enum CacheControlResponse {
         PUBLIC("public"),
         PRIVATE("private"), // [ "=" <"> 1#field-name <"> ] ; Section 14.9.1
         NO_CACHE("no-cache"), // [ "=" <"> 1#field-name <"> ]; Section 14.9.1
         NO_STORE("no-store"), //                             ; Section 14.9.2
         NO_TRANSFORM("no-transform"),  //                         ; Section 14.9.5
         MUST_REVALIDATE("must-revalidate"),  //                     ; Section 14.9.4
         PROXY_REVALIDATE("proxy-revalidate"),  //                   ; Section 14.9.4
         MAX_AGE("max-age"), // "=" delta-seconds            ; Section 14.9.3
         S_MAX_AGE("s-maxage"), // "=" delta-seconds           ; Section 14.9.3
         CACHE_EXT("cache-extension");  //                       ; Section 14.9.6                
                 
        public String code;
        
        CacheControlResponse(String code) {
            this.code = code;
        }
    }    
    
    
    enum Status {        
        SC_OK( ResponseStatus.SC_OK ),
        SC_CREATED( ResponseStatus.SC_CREATED ),
        SC_ACCEPTED( ResponseStatus.SC_ACCEPTED),
        SC_NO_CONTENT( ResponseStatus.SC_NO_CONTENT),
        SC_MOVED_PERMANENTLY( ResponseStatus.SC_MOVED_PERMANENTLY),
        SC_MOVED_TEMPORARILY( ResponseStatus.SC_MOVED_TEMPORARILY),
        SC_NOT_MODIFIED( ResponseStatus.SC_NOT_MODIFIED),
        SC_BAD_REQUEST( ResponseStatus.SC_BAD_REQUEST),
        SC_UNAUTHORIZED( ResponseStatus.SC_UNAUTHORIZED),
        SC_FORBIDDEN( ResponseStatus.SC_FORBIDDEN),
        SC_NOT_FOUND( ResponseStatus.SC_NOT_FOUND),
        SC_INTERNAL_SERVER_ERROR( ResponseStatus.SC_INTERNAL_SERVER_ERROR),
        SC_NOT_IMPLEMENTED( ResponseStatus.SC_NOT_IMPLEMENTED),
        SC_BAD_GATEWAY( ResponseStatus.SC_BAD_GATEWAY),
        SC_SERVICE_UNAVAILABLE( ResponseStatus.SC_SERVICE_UNAVAILABLE),
        SC_PARTIAL_CONTENT( ResponseStatus.SC_PARTIAL_CONTENT),
        SC_CONTINUE( 100),
        SC_METHOD_NOT_ALLOWED( 405),
        SC_CONFLICT( 409),
        SC_PRECONDITION_FAILED( 412),
        SC_REQUEST_TOO_LONG( 413),
        SC_UNSUPPORTED_MEDIA_TYPE( 415),
        SC_MULTI_STATUS(207),
        SC_UNPROCESSABLE_ENTITY( 418),
        SC_INSUFFICIENT_SPACE_ON_RESOURCE( 419),
        SC_METHOD_FAILURE( 420),
        SC_LOCKED( 423);
        
        public int code;
        
        Status(int code) {
            this.code = code;
        }        

        @Override
        public String toString() {
            return "HTTP/1.1 " + code;
        }
    }

    public Response.Status getStatus();

    public void setContentEncodingHeader(ContentEncoding encoding);

    public void setExpiresHeader(Date expiresAt);

    public void setLockTokenHeader(String tokenId);

    void setAuthenticateHeader( String realm );
    
    void setStatus(Status status);
    
    void setEtag(String uniqueId);

    void setContentRangeHeader(long start, long finish, Long totalLength);

    void setContentLengthHeader(Long totalLength);
    
    void setContentTypeHeader(String string);
    
    String getContentTypeHeader();

    void setCacheControlMaxAgeHeader(Long deltaSeconds);
    
    void setCacheControlNoCacheHeader();
    
    void setLastModifiedHeader(Date date);

    void setDavHeader(String string);

    void setNonStandardHeader(String code, String value);

    String getNonStandardHeader(String code);
    
    void setAllowHeader(List<Request.Method> methodsAllowed);

    OutputStream getOutputStream();    

    void setLocationHeader(String redirectUrl);

    void setDateHeader(Date date);

    void close();
 
}
