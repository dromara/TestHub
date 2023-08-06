package com.goddess.nsrule.core.eventbus;

import java.util.HashMap;
import java.util.Map;

public enum EventType {

    visit_flow("visit_flow", "进入Flow"), exit_flow("exit_flow", "结束Flow"),
    
    visit_exec("visit_exec", "进入Execute"), exit_exec("exit_exec", "结束Execute"),

    TEST("TEST", "测试");

    EventType(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


    private static final Map<String, EventType> CODE_MAP = new HashMap<>();

    static {
        for (EventType typeEnum : EventType.values()) {
            CODE_MAP.put(typeEnum.getCode(), typeEnum);
        }
    }

    public static EventType getEnumByCode(String code) {
        return CODE_MAP.get(code);
    }
}
