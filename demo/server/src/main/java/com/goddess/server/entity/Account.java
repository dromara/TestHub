package com.goddess.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName(value = "test_account", resultMap = "poMap")
public class Account implements Serializable {
    private Long id;
    private BigDecimal usableAmt;
}
