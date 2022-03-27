package com.example.spring.learn.regex;

public class RegexTimeoutException extends RuntimeException{

    private final String regularExpression;

    private final String stringToMatch;

    private final long timeoutMillis;

    public RegexTimeoutException() {
        super();
        this.regularExpression = null;
        this.stringToMatch = null;
        this.timeoutMillis = 0;
    }

    public RegexTimeoutException(String message) {
        super(message);
        this.regularExpression = null;
        this.stringToMatch = null;
        this.timeoutMillis = 0;
    }

    public RegexTimeoutException(String message, Throwable cause) {
        super(message, cause);
        this.regularExpression = null;
        this.stringToMatch = null;
        this.timeoutMillis = 0;
    }

    public RegexTimeoutException(Throwable cause) {
        super(cause);
        this.regularExpression = null;
        this.stringToMatch = null;
        this.timeoutMillis = 0;
    }

    public RegexTimeoutException(String regularExpression, String stringToMatch, long timeoutMillis) {
        super("Timeout occurred after " + timeoutMillis + "ms while processing regular expression '" + regularExpression + "' on input '" + stringToMatch + "'!");
        this.regularExpression = regularExpression;
        this.stringToMatch = stringToMatch;
        this.timeoutMillis = timeoutMillis;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public String getStringToMatch() {
        return stringToMatch;
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }
}
