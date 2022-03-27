package com.example.spring.learn.qlexpress.operator;

import com.google.common.collect.Lists;
import com.ql.util.express.Operator;

import java.util.List;

public class JoinOperator extends Operator {
    @Override
    public Object executeInner(Object[] list) throws Exception {
        Object opdata1 = list[0];
        Object opdata2 = list[1];
        if(opdata1 instanceof List) {
            ((List)opdata1).add(opdata2);
            return opdata1;
        } else {
            List res = Lists.newArrayList();
            for (Object opdata : list) {
                res.add(opdata);
            }
            return res;
        }
    }
}
