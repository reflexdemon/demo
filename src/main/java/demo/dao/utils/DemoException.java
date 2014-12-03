/**
 * 
 */
package demo.dao.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vvenkatraman
 *
 */
public class DemoException extends Exception {
    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

    public static String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    protected String errorId = null; // "UNKNOWN";
    protected String userMessage = null;

    protected String syslogMessage = null;

    static String DEF_USER_MESSAGE = "An unknown error (runtime) has occured.";

    protected List<String> context;

    /** */
    public DemoException() {
        super();
    }

    protected DemoException(String s) {
        super(s);
    }

    /** */
    public DemoException(String eid, String m) {
        this(eid, m, null, null);
    }

    /** */
    public DemoException(String eid, String message, Throwable t) {
        this(eid, message, t, null);
    }

    /** Full constructor */
    public DemoException(String eid, String m, Throwable t, String um) {
        super(m);

        if (eid != null)
            errorId = eid;

        if (t != null)
            initCause(t);

        if (errorId == null) {
            errorId = "UNK";
        }

        userMessage = um;
    }

    @Override
    public Throwable initCause(Throwable t) {
        super.initCause(t);
        if (t instanceof DemoException) {
            if (errorId != null)
                errorId = errorId + ":" + ((DemoException) t).getErrorId();
            else
                errorId = ((DemoException) t).getErrorId();
        } else if (t instanceof SecurityException) {
            if (errorId == null) {
                errorId = "DEMO.security-err";
            }
        }
        return this;
    }

    public String toString() {
        String s = super.toString();
        return errorId + ": " + s + ": " + getCauseString(this);
    }

    private String getCauseString(Throwable t) {
        String msg = "";
        Throwable cause = t.getCause();
        if (cause != null) {
            for (; cause.getCause() != null; cause = cause.getCause())
                ;
            msg = " Caused by: " + cause;
        }
        return msg;
    }

    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    public String getUserMessage() {
        return userMessage == null ? DEF_USER_MESSAGE : userMessage;
    }

    

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public synchronized void addContext(String s) {
        // this.userMessage = context + ": "+ userMessage;
        if (context == null)
            context = new ArrayList<String>();
        context.add(s);
    }

    public List<String> getContext() {
        return context;
    }

    /**
     * <code>findCause</code> seaches the chain of causes of this exception
     * looking for an Throwable of the given class.
     * 
     * @param c
     *            a Class that is Throwable or extends Throwable that represents
     *            the type of cause being searched for.
     * @return a Throwable of the given class
     */
    public <T extends Throwable> T findCause(Class<T> c) {
        T result = null;
        Throwable cause = this;
        for (; cause != null && cause.getClass() != c; cause = cause.getCause())
            ;
        if (cause != null)
            result = (T) cause;
        return result;
    }

    public String getSyslogMessage() {
        return syslogMessage;
    }

    public void setSyslogMessage(String syslogMessage) {
        this.syslogMessage = syslogMessage;
    }
}
