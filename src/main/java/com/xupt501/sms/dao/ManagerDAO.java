package com.xupt501.sms.dao;

import com.xupt501.sms.domain.Manager;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ManagerDAO extends Mapper<Manager> {
}
