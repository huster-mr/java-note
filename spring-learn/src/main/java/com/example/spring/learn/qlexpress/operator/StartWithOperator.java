package com.example.spring.learn.qlexpress.operator;

import com.ql.util.express.Operator;

/**
 *  判断字符串StartWith，忽略大小写
 */
public class StartWithOperator extends Operator {
    @Override
    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];
        if (opdata1 == null || opdata2 == null) {
            return null;
        }
        if (opdata1 instanceof String && opdata2 instanceof String) {
            return opdata1.toString().toLowerCase().startsWith(opdata2.toString().toLowerCase());
        }
        return false;
    }
}
