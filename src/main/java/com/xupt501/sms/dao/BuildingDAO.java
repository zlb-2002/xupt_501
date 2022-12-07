package com.xupt501.sms.dao;

import com.xupt501.sms.domain.Building;
import com.xupt501.sms.domain.Community;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface BuildingDAO extends Mapper<Building> {
}
