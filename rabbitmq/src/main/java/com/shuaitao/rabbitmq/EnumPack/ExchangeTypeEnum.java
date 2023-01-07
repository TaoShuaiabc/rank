package com.shuaitao.rabbitmq.EnumPack;


/** 交换机类型枚举
 * @author ts
 */

public enum ExchangeTypeEnum {


    DIRECT("direct"),FANOUT("fanout"),TOPIC("topic");

    private final String code;

    ExchangeTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
