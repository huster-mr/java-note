package com.example.spring.learn.qlexpress;

public enum OperatorEnum {
    /**
     * 包含操作
     */
    CONTAINS("contains", "包含"),
    /**
     * 不包含操作
     */
    NOT_CONTAINS("not_contains", "不包含"),
    /**
     * 不在范围内
     */
    NOT_IN("not_in", "不在范围内"),
    /**
     * 以什么开头
     */
    START_WITH("startWith", "以什么开头")
    ;

    private String name;
    private String desc;

    OperatorEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}
