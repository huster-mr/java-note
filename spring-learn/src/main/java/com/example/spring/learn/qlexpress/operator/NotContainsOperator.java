package com.example.spring.learn.qlexpress.operator;

import com.ql.util.express.Operator;

import java.util.Collection;
import java.util.Locale;

public class NotContainsOperator extends Operator {

    @Override
    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];

        if (opdata1 == null || opdata2 == null) {
            return null;
        }

        if (opdata1 instanceof String) {
            return !opdata1.toString().toLowerCase().contains(opdata2.toString().toLowerCase());
        }

        if (opdata1 instanceof Collection) {
            return !((Collection<?>) opdata1).contains(opdata2);
        }
        return null;
    }
}
