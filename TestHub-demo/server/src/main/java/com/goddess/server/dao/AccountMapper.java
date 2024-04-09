package com.goddess.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goddess.server.entity.Account;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AccountMapper extends BaseMapper<Account> {

}
