package org.kafka.www.kafkademo.test01;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Order {

    private int id;
    private int userId;
    private Double count;

    public Order(int i, int i1, double v) {
    }

    public Order() {
    }
}
