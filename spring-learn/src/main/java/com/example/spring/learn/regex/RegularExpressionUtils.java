package com.example.spring.learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionUtils {
    public static Matcher createMatcherWithTimeout(String stringToMatch, Pattern pattern, int timeoutMillis) {
        CharSequence charSequence = new TimeoutRegexCharSequence(stringToMatch, timeoutMillis, stringToMatch, pattern.pattern());
        return pattern.matcher(charSequence);
    }

    private static class TimeoutRegexCharSequence implements CharSequence {
        private final CharSequence inner;

        private final int timeoutMillis;

        private final long timeoutTime;

        private final String stringToMatch;

        private final String regularExpression;

        public TimeoutRegexCharSequence(CharSequence inner, int timeoutMillis, String stringToMatch, String regularExpression) {
            super();
            this.inner = inner;
            this.timeoutMillis = timeoutMillis;
            this.stringToMatch = stringToMatch;
            this.regularExpression = regularExpression;
            this.timeoutTime = System.currentTimeMillis() + timeoutMillis;
        }

        @Override
        public char charAt(int index) {
            if (System.currentTimeMillis() > timeoutTime) {
                throw new RegexTimeoutException(regularExpression, stringToMatch, timeoutMillis);
            }
            return inner.charAt(index);
        }

        @Override
        public int length() {
            return inner.length();
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return new TimeoutRegexCharSequence(inner.subSequence(start, end), timeoutMillis, stringToMatch, regularExpression);
        }

        @Override
        public String toString() {
            return inner.toString();
        }
    }
}
