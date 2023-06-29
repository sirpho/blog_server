package com.sirpho.blog.common.enums;

import java.util.*;
import java.util.stream.Collectors;

public enum CreationTypeEnum {

    ORIGINAL("original", "原创"),
    REPRODUCED("reproduced", "转载");
    /**
     * 枚举信息 --枚举值
     */
    private final String value;
    private final String name;

    CreationTypeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
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
    public static CreationTypeEnum getByValue(String value) {
        for (CreationTypeEnum cache : values()) {
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
        return Arrays.stream(CreationTypeEnum.values())
                .map(cache -> {
                    var map = new HashMap<String, String>();
                    map.put("name", cache.getName());
                    map.put("value", cache.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
