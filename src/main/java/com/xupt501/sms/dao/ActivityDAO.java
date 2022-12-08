package com.xupt501.sms.dao;

import com.xupt501.sms.domain.Activity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ActivityDAO extends Mapper<Activity> {
}
