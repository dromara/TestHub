package com.goddess.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goddess.server.entity.Order;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
