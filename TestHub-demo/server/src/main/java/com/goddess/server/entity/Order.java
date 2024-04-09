package com.goddess.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "test_order", resultMap = "poMap")
public class Order {
    private Long id;
    private String code;
    private Long acctId;
    private Integer orderQty;
    private BigDecimal orderPrice;
    private BigDecimal orderAmt;



}
