package com.sirpho.blog.common.enums;

public enum ConstantEnum {
    PAGE_CURRENT("pageNum", "分页参数-当前页"),
    PAGE_SIZE("pageSize", "分页参数-每页容量"),
    ORDER_FIELD("orderBy", "分页参数-排序字段"),
    ORDER("order", "分页参数-排序方式"),
    ORDER_ASC("asc", "升序"),
    ORDER_DESC("desc", "降序"),

    RESULT_FIELD("data", "接口返回字段名");

    /**
     * 枚举信息 --中文名称
     */
    private final String name;
    /**
     * 枚举信息 --枚举值
     */
    private final String value;


    ConstantEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }


    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
