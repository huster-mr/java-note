package com.example.spring.learn.qlexpress;

import com.ql.util.express.Operator;

import java.util.Collection;

/**
 * 自定义操作符，包含
 */
public class ContainsOperator extends Operator {

    @Override
    public Object executeInner(Object[] objects) throws Exception {
        Object obj1 = objects[0];
        Object obj2 = objects[1];
        if (obj1 == null || obj2 == null) {
            return false;
        }
        if(obj1 instanceof String) {
            return obj1.toString().toLowerCase().contains(obj2.toString().toLowerCase());
        } else if (obj1 instanceof Collection) {
            return ((Collection)obj1).contains(obj2);
        }
        return false;
    }
}
