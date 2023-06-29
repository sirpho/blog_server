package com.sirpho.blog.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum WhetherEnum {
    YES("Y", "yes", "是"),
    NO("N", "no", "否");
    /**
     * 枚举描述 --英文名称
     */
    private final String alias;
    /**
     * 枚举信息 --中文名称
     */
    private final String name;
    /**
     * 枚举信息 --枚举值
     */
    private final String value;

    WhetherEnum(String value, String alias, String name) {
        this.value = value;
        this.alias = alias;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    /**
     * 通过枚举 value 获得枚举
     *
     * @param value 枚举值
     * @return IntervalEnum
     */
    public static WhetherEnum getByValue(String value) {
        for (WhetherEnum cache : values()) {
            if (value.equals(cache.getValue())) {
                return cache;
            }
        }
        return null;
    }

    /**
     * 获取列表
     * @return list
     */
    public static List<Map<String, String>> getList() {
        return Arrays.stream(WhetherEnum.values())
                .map(cache -> {
                    var map = new HashMap<String, String>();
                    map.put("name", cache.getName());
                    map.put("value", cache.getValue());
                    map.put("alias", cache.getAlias());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
