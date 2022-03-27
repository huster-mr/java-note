package com.example.spring.learn.qlexpress;

import com.example.spring.learn.qlexpress.operator.*;
import com.ql.util.express.ExpressRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Engine {
    private static final Logger logger = LoggerFactory.getLogger(Engine.class);

    private ExpressRunner runner = new ExpressRunner();

    @PostConstruct
    public void init() {
        try {
            // 自定义操作
            runner.addOperator(OperatorEnum.CONTAINS.getName(), new ContainsOperator());
            runner.addOperator(OperatorEnum.NOT_CONTAINS.getName(), new NotContainsOperator());
            // 多元操作
            runner.addOperator(OperatorEnum.NOT_IN.getName(), "in", new NotInOperator());
            runner.addOperator(OperatorEnum.START_WITH.getName(), new StartWithOperator());

            // 自定义操作覆盖默认操作
            runner.replaceOperator("+", new JoinOperator());

            // 自定义函数
            runner.addFunction("join", new JoinOperator());
            return;

        } catch (Exception e) {
            logger.error("Engine init error", e);
        }
    }

    public ExpressRunner getRunner() {
        return runner;
    }
}
