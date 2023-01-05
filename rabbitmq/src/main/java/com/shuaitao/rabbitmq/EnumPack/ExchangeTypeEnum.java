package com.shuaitao.rabbitmq.EnumPack;


/** 交换机类型枚举
 * @author ASUS
 */

public enum ExchangeTypeEnum {


    DIRECT("direct"),FANOUT("fanout"),TOPIC("topic");

    String code;

    ExchangeTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
